package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Stockincomebillitem;
import cn.itsource.aisell.service.IStockincomebillitemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class StockincomebillitemServiceTest extends BaseServiceTest {
    @Autowired
    private IStockincomebillitemService stockincomebillitemService;

     @Test
      public void testFindAll() throws Exception{
         stockincomebillitemService.findAll().forEach(e-> System.out.println(e));
      }
}