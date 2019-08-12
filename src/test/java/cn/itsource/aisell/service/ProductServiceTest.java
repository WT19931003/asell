package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.service.IProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductServiceTest extends BaseServiceTest {
    @Autowired
    private IProductService productService;

     @Test
      public void testFindAll() throws Exception{
         productService.findAll().forEach(e-> System.out.println(e));
      }
}