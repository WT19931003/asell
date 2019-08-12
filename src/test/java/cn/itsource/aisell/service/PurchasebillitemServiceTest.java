package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.query.PurchasebillitemQuery;
import cn.itsource.aisell.service.IPurchasebillitemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PurchasebillitemServiceTest extends BaseServiceTest {
    @Autowired
    private IPurchasebillitemService purchasebillitemService;

     @Test
      public void testFindAll() throws Exception{
         PurchasebillitemQuery query = new PurchasebillitemQuery();
         purchasebillitemService.findCharts(query);
      }
}