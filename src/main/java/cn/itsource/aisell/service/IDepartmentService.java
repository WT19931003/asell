package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Department;

public interface IDepartmentService extends IBaseService<Department,Long>{

    Department findByName(String name);
}