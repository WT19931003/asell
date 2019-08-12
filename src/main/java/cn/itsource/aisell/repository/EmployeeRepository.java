package cn.itsource.aisell.repository;

import cn.itsource.aisell.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Employee:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface EmployeeRepository extends BaseRepository<Employee,Long> {

    //根据用户名拿到对应的员工
    Employee findByUsername(String username);

    //根据部门的名称获取到对应的所有员工
    List<Employee> findByDepartmentName(String deptName);
}
