package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Systemdictionarytype;
import cn.itsource.aisell.service.ISystemdictionarytypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class SystemdictionarytypeServiceTest extends BaseServiceTest {
    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

     @Test
      public void testFindAll() throws Exception{
         systemdictionarytypeService.findAll().forEach(e-> System.out.println(e));
      }
}