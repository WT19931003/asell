package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.service.IPurchasebillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PurchasebillServiceTest extends BaseServiceTest {
    @Autowired
    private IPurchasebillService purchasebillService;

     @Test
      public void testFindAll() throws Exception{
         purchasebillService.findAll().forEach(e-> System.out.println(e));
      }
}