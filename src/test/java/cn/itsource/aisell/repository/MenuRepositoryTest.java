package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.service.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuRepositoryTest extends BaseServiceTest {

    @Autowired
    private MenuRepository menuRepository;

     @Test
      public void test() throws Exception{
         List<Menu> menus = menuRepository.findByUser(3L);

         menus.forEach(m -> System.out.println(m));
     }
}
