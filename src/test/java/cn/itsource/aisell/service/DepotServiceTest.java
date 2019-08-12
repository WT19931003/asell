package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Depot;
import cn.itsource.aisell.service.IDepotService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DepotServiceTest extends BaseServiceTest {
    @Autowired
    private IDepotService depotService;

     @Test
      public void testFindAll() throws Exception{
         depotService.findAll().forEach(e-> System.out.println(e));
      }
}