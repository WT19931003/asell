package cn.itsource.aisell.service;

import cn.itsource.aisell.common.MD5Util;
import cn.itsource.aisell.domain.Employee;
import org.hibernate.annotations.SourceType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class EmployeeServiceTest extends BaseServiceTest {
    @Autowired
    private IEmployeeService employeeService;

     @Test
      public void testFindAll() throws Exception{
         employeeService.findAll().forEach(e-> System.out.println(e));
      }
       @Test
        public void testSave() throws Exception{
           Employee employee = new Employee();
           employee.setUsername("xxx");
           employee.setPassword("xxxx");
           employee.setEmail("xxx");
           employee.setAge(3);
           employeeService.save(employee);
        }


         @Test
          public void testUpdatePwd() throws Exception{
             List<Employee> list = employeeService.findAll();
             list.forEach((e) -> {
                 e.setPassword(MD5Util.createMD5Str(e.getPassword()));
                 employeeService.save(e);
             });
//             for (Employee employee : list) {
//                 employee.setPassword(MD5Util.createMD5Str(employee.getPassword()));
//                 employeeService.save(employee);
//             }
         }
}
