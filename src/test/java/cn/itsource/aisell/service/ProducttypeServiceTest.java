package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Producttype;
import cn.itsource.aisell.service.IProducttypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ProducttypeServiceTest extends BaseServiceTest {
    @Autowired
    private IProducttypeService producttypeService;

     @Test
      public void testFindAll() throws Exception{
         producttypeService.findAll().forEach(e-> System.out.println(e));
      }
}