package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.domain.vo.PurchaseBillItemVO;
import cn.itsource.aisell.query.PurchasebillitemQuery;
import cn.itsource.aisell.service.IPurchasebillitemService;
import cn.itsource.aisell.repository.PurchasebillitemRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采购明细的业务
 */
@Service
public class PurchasebillitemServiceImpl
        extends BaseServiceImpl<Purchasebillitem,Long> implements IPurchasebillitemService {

    @Autowired
    private PurchasebillitemRepository purchasebillitemRepository;


    @Override
    public List<PurchaseBillItemVO> findItems(PurchasebillitemQuery query) {
        //1.根据条件拿到所有的采购明细
        List<Purchasebillitem> items = purchasebillitemRepository.findByQuery(query);
        //2.准备返回的明细报表集合(空的)
        List<PurchaseBillItemVO> vos = new ArrayList<>();
        //3.把items的数据交给vos
        for (Purchasebillitem item : items) {
            PurchaseBillItemVO vo = new PurchaseBillItemVO(item,query.getGroupType());
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public List<Map> findCharts(PurchasebillitemQuery query){
        List<Map> listMap = new ArrayList<>();
        //写咱们的JPQL  where status = ?
        //   select o.bill.supplier.name,sum(o.amount) from Purchasebillitem o  where o.bill.status = ?   group by o.bill.supplier.name
        String whereJPQL = query.createWhereJPQL();
        //  根据供应商的名称分组 -> group by o.bill.supplier.name
        //如果 query中的groupType值变化了,分组就要变化
        String groupBy = query.getGroupBy();
        String jpql = "select "+groupBy+",sum(o.amount) from Purchasebillitem o "+whereJPQL+"  group by "+groupBy;

        System.out.println(jpql);

        // 执行这条jpql
        List<Object[]> objs = super.findByJpql(jpql,query.getParams().toArray());
        for (Object[] obj : objs) {
            Map map = new HashMap();
            map.put("name", obj[0]);
            map.put("y", obj[1]);
            listMap.add(map);
        }

        return listMap;
    }
}