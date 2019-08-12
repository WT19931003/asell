package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Department:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface DepartmentRepository extends BaseRepository<Department,Long> {

    Department findByName(String name);
}