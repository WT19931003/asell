package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Permission:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface PermissionRepository extends BaseRepository<Permission,Long> {

    /**
     * JPQL的关连：1.自动消除笛卡尔积 2.关连的 别名.属性
     *   根据员工的id去获到相应的权限  Set<String>
     */
    @Query("select distinct p.sn from Employee e join e.roles r join r.permissions p where e.id = ?1")
    Set<String> findByUser(Long empId);
}