package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StockincomebillServiceTest extends BaseServiceTest {
    @Autowired
    private IStockincomebillService stockincomebillService;


     @Test
      public void testSave() throws Exception{
         Stockincomebill bill = new Stockincomebill();
         bill.setDepot(new Depot(1L));
         bill.setInputUser(new Employee(1L));
         bill.setKeeper(new Employee(2L));
         bill.setSupplier(new Supplier(2L));
         bill.setVdate(new Date());

         // 2个入库明细
         List<Stockincomebillitem> items = new ArrayList<Stockincomebillitem>();
         Stockincomebillitem billItem = new Stockincomebillitem();
         billItem.setDescs("备注1");
         billItem.setNum(new BigDecimal(1));
         billItem.setPrice(new BigDecimal(1));
         billItem.setProduct(new Product(1L));
         items.add(billItem);

         Stockincomebillitem billItem2 = new Stockincomebillitem();
         billItem2.setDescs("备注2");
         billItem2.setNum(new BigDecimal(2));
         billItem2.setPrice(new BigDecimal(2));
         billItem2.setProduct(new Product(2L));
         items.add(billItem2);

         BigDecimal totalAmount = new BigDecimal(0);// 总金额
         BigDecimal totalNum = new BigDecimal(0);// 总数量
         for (Stockincomebillitem item : items) {
             // 设置多方到一方的关系
             item.setBill(bill);
             // 计算小计
             item.setAmount(item.getPrice().multiply(item.getNum()));
             // 累加
             totalAmount = totalAmount.add(item.getAmount());
             totalNum = totalNum.add(item.getNum());
         }
         // 设置总金额,总数量
         bill.setTotalamount(totalAmount);
         bill.setTotalnum(totalNum);
         // 设置一方到多方的关系
         bill.setItems(items);


         System.out.println(bill.getItems());
         // 级联保存
         stockincomebillService.save(bill);
      }


     @Test
      public void testFindAll() throws Exception{
         stockincomebillService.findAll().forEach(e-> System.out.println(e));
      }
}