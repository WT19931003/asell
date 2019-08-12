package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Supplier;
import cn.itsource.aisell.service.ISupplierService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class SupplierServiceTest extends BaseServiceTest {
    @Autowired
    private ISupplierService supplierService;

     @Test
      public void testFindAll() throws Exception{
         supplierService.findAll().forEach(e-> System.out.println(e));
      }
}