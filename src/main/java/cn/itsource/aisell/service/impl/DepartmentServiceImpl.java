package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.service.IDepartmentService;
import cn.itsource.aisell.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DepartmentServiceImpl
        extends BaseServiceImpl<Department,Long> implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }
}