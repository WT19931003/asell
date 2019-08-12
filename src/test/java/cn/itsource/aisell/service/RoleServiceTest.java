package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Role;
import cn.itsource.aisell.service.IRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class RoleServiceTest extends BaseServiceTest {
    @Autowired
    private IRoleService roleService;

     @Test
      public void testFindAll() throws Exception{
         roleService.findAll().forEach(e-> System.out.println(e));
      }
}