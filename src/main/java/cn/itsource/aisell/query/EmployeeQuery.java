package cn.itsource.aisell.query;

import cn.itsource.aisell.domain.Employee;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeQuery extends BaseQuery {

    private String username;
    private String email;
    private Integer age;
    private Long departmentId; //部门类型接收

    //拿到查询的条件
    @Override
    public Specification createSpec() {
        Specification<Employee> spec = Specifications.<Employee>and()
                .like(StringUtils.isNotBlank(username), "username", "%" + username + "%")
                .like(StringUtils.isNotBlank(email), "email", "%" + email + "%")
                .lt(age != null, "age", age)
                .eq(departmentId!=null,"department.id",departmentId)
                .build();
        return spec;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "EmployeeQuery{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }


}
