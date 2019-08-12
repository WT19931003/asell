package cn.itsource.aisell.common;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 准备一个自定义验证
 */
@Component
public class EmployeeExcelVerifyHandler implements IExcelVerifyHandler<Employee> {

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult(true);
        //如果存在，返回false
        if(!employeeService.checkUsername(employee.getUsername())){
            //代表用户名重复
            result.setMsg("用户名重复");
            result.setSuccess(false);
        }
        return result;
    }
}
