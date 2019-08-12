package cn.itsource.aisell.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Department)实体类
 *
 * @author makejava
 * @since 2018-11-25 15:13:32
 */
@Entity
@Table(name="department")
public class Department extends BaseDomain {

    @Excel(name = "部门名称")
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}