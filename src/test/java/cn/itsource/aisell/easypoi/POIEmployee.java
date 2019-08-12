package cn.itsource.aisell.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

@ExcelTarget("emp")
public class POIEmployee {

    //用户名
    @Excel(name = "用户名")
    private String name;
    //邮件
    @Excel(name = "邮件",width = 20)
    private String email;

    @Excel(name="年龄")
    private Integer age ;
    @Excel(name="性别",replace ={"男_true","女_false"})
    private Boolean sex;

    @Excel(name = "出生日期",format="yyyy-MM-dd")
    private Date bronDate = new Date();

    @ExcelEntity
    private POIDepartment department;

    @Excel(name="头像",type = 2,height = 30,savePath = "5/img")
    private String headImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBronDate() {
        return bronDate;
    }

    public void setBronDate(Date bronDate) {
        this.bronDate = bronDate;
    }

    public POIDepartment getDepartment() {
        return department;
    }

    public void setDepartment(POIDepartment department) {
        this.department = department;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "POIEmployee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", bronDate=" + bronDate +
                ", department=" + department +
                ", headImage='" + headImage + '\'' +
                '}';
    }
}
