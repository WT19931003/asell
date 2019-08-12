package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.MD5Util;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.repository.EmployeeRepository;
import cn.itsource.aisell.service.IBaseService;
import cn.itsource.aisell.service.IEmployeeService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl
        extends BaseServiceImpl<Employee,Long> implements IEmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findBuyers() {
        return employeeRepository.findByDepartmentName("采购部");
    }
    @Override
    @Transactional
    public void save(Employee employee) {
        //如果用户没有id,代表现在是添加功能(密码加密)
        if(employee.getId()==null){
            employee.setPassword(MD5Util.createMD5Str(employee.getPassword()));
        }
        employeeRepository.save(employee);
    }


    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    /**
     * 返回的值等于null,代表不存在，就代表它是可以使用的
     * @param username
     * @return
     */
    @Override
    public boolean checkUsername(String username) {
        return employeeRepository.findByUsername(username)==null;
    }
}
