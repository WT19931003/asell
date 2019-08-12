package cn.itsource.aisell.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("dept")
public class POIDepartment {

    @Excel(name = "部门名称_emp,名称_dept")
    private String name;

    @Excel(name="地址_dept")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "POIDepartment{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
