package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Dept;
import cn.itsource.aisell.service.IDeptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DeptServiceTest extends BaseServiceTest {
    @Autowired
    private IDeptService deptService;

     @Test
      public void testFindAll() throws Exception{
         deptService.findAll().forEach(e-> System.out.println(e));
      }
}