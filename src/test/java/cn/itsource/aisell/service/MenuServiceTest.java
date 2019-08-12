package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.service.IMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MenuServiceTest extends BaseServiceTest {
    @Autowired
    private IMenuService menuService;

     @Test
      public void testFindAll() throws Exception{
         menuService.findAll().forEach(e-> System.out.println(e));
      }
}