package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Employee;

import java.util.List;

/**
 * 这里以后应该会有它自己一些特殊的业务
 */
public interface IEmployeeService extends IBaseService<Employee,Long>{

    Employee findByUsername(String username);
    /**
     * 检查用户名是否存在
     * @param username
     * @return 存在的话返回->false【不可用】
     */
    boolean checkUsername(String username);

    //拿到所有的采购员
    List<Employee> findBuyers();
}
