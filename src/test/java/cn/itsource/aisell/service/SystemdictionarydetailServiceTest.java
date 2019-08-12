package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Systemdictionarydetail;
import cn.itsource.aisell.service.ISystemdictionarydetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class SystemdictionarydetailServiceTest extends BaseServiceTest {
    @Autowired
    private ISystemdictionarydetailService systemdictionarydetailService;

     @Test
      public void testFindAll() throws Exception{
         systemdictionarydetailService.findAll().forEach(e-> System.out.println(e));
      }
}