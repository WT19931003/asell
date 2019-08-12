package cn.itsource.aisell.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee extends BaseDomain {
    @Excel(name="用户名")
    @NotNull(message = "用户名不为空")
    private String username;
    private String password;
    @Excel(name="邮件",width = 25)
    private String email;
    @Excel(name="年纪")
    @Max(value = 80,message = "max 最大值不能超过80")
    private Integer age;

    @Excel(name = "头像",type = 2,savePath = "/images/head",height = 23)
    private String headImage; //头像

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    //Json Ignore(忽略) Properties(属性)
    //@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    @ExcelEntity
    private Department department;


    //JoinTable:确定中间表的值
    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name="employee_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles = new ArrayList<>();

    public Employee() {}
    public Employee(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
