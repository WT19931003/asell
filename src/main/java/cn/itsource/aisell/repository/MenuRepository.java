package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Menu:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface MenuRepository extends BaseRepository<Menu,Long> {

    @Query("select distinct p.menu from Employee e join e.roles r join r.permissions p where e.id = ?1")
    List<Menu> findByUser(Long empId);
}