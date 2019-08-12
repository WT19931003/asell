package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.service.IDepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DepartmentServiceTest extends BaseServiceTest {
    @Autowired
    private IDepartmentService departmentService;

     @Test
      public void testFindAll() throws Exception{
         departmentService.findAll().forEach(e-> System.out.println(e));
      }
}