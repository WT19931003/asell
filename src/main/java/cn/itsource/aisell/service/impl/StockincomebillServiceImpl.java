package cn.itsource.aisell.service.impl;
import java.math.BigDecimal;
import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.domain.Depot;

import cn.itsource.aisell.domain.*;
import cn.itsource.aisell.service.IDepotService;
import cn.itsource.aisell.service.IProductstockService;
import cn.itsource.aisell.service.IStockincomebillService;
import cn.itsource.aisell.repository.StockincomebillRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StockincomebillServiceImpl
        extends BaseServiceImpl<Stockincomebill,Long> implements IStockincomebillService {

    @Autowired
    private StockincomebillRepository stockincomebillRepository;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IProductstockService productstockService;

    /**
     *  采购入库单
     *  1.判断这个单据是否可以审核
     *  2.修改采购入库单
     *  3.修改仓库的数据
     *  4.修改库存数据
     */
    @Override
    @Transactional
    public void auding(Employee auditor, Long billId) {
        /**
         * 一.判断这个单据是否可以审核
         *    1.如果单据不存在，则不能审核
         *    2.如果单据作废，则不能审核
         *    3.如果单据已审，则不能审核
         *    不能审核，就抛一个异常出去
         */
        Stockincomebill bill = findOne(billId);
        if(bill==null){
            throw new RuntimeException("该采购入库单不存在！");
        }
        if(bill.getStatus()==1){
            throw new RuntimeException("该采购入库单已经审核！");
        }
        if(bill.getStatus()==-1){
            throw new RuntimeException("该采购入库单已经作废！");
        }

        /**
         * 二.修改采购入库单
         *    添加审核时间:当前时间
         *    添加审核人:传过来的当前用户
         *    修改状态:1(代表已审)
         */
        bill.setAuditortime(new Date());
        bill.setAuditor(auditor);
        bill.setStatus(1);
        //建议写上:给那些初学者看得明白一点
        stockincomebillRepository.save(bill); //添加或者修改

        /**
         * 三.修改仓库的数据(修改当前数量与当前仓库的总价值)
         */
        Depot depot = bill.getDepot();
        depot.setCurrentCapacity(depot.getCurrentCapacity().add(bill.getTotalnum()));
        depot.setTotalAmount(depot.getTotalAmount().add(bill.getTotalamount()));
        depotService.save(depot);

        /**
         * 四.修改库存数据
         *     需要判断这条数据是否要和其它的数据进行合并
         *     什么情况下才合并:同一个产品，同一个仓库
         *     注意:每个明细都要单独判断(需要遍历所有明细)
         */
        String jpql = "select o from Productstock o where o.product = ? and o.depot = ?";
        List<Stockincomebillitem> items = bill.getItems();
        for (Stockincomebillitem item : items) {
            //1.执行JPQL去库存中找相应的值
            List<Productstock> pss = findByJpql(jpql, item.getProduct(), depot);
            //2.进行数据的判断
            if(pss.size()==0){
                //代表没有相应的数据，新加一条库存(数据从明细中来)
                Productstock ps = new Productstock();
                ps.setNum(item.getNum());
                ps.setAmount(item.getAmount());
                ps.setPrice(item.getPrice());
                ps.setIncomedate(new Date());
                ps.setProduct(item.getProduct());
                ps.setDepot(depot);
                productstockService.save(ps);
            }else if(pss.size()==1){
                //代表有一条相应的数据，要进行累加合并
                Productstock ps = pss.get(0);
                ps.setIncomedate(new Date()); //最新时间是入库时间
                ps.setNum(ps.getNum().add(item.getNum())); //数量累加
                ps.setAmount(ps.getAmount().add(item.getAmount())); //总金额累加
                //单价：加权平均法:总金额/总数量
                //  BigDecimal中除不尽是会报错的！ -> 保留两次小数(四舍五入)
                ps.setPrice(ps.getAmount().divide(ps.getNum(),2, BigDecimal.ROUND_HALF_EVEN));

                productstockService.save(ps);
            }else{
                //代表数据出问题了，让他联系管理进行修复
                throw new RuntimeException("数据出问题了，请联系程序员进行修复！");
            }
        }

    }
}