package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Productstock;
import cn.itsource.aisell.service.IProductstockService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductstockServiceTest extends BaseServiceTest {
    @Autowired
    private IProductstockService productstockService;

     @Test
      public void testFindAll() throws Exception{
         productstockService.findAll().forEach(e-> System.out.println(e));
      }
}