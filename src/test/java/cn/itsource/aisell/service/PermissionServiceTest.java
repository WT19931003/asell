package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.service.IPermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PermissionServiceTest extends BaseServiceTest {
    @Autowired
    private IPermissionService permissionService;


    @Test
    public void testFindByUser() throws Exception{
        permissionService.findByUser(3L).forEach(sn-> System.out.println(sn));
    }


     @Test
      public void testFindAll() throws Exception{
         permissionService.findAll().forEach(e-> System.out.println(e));
      }
}