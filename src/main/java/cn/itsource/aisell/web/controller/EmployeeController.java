package cn.itsource.aisell.web.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.query.EmployeeQuery;
import cn.itsource.aisell.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/index")
    public String index(){
        return "employee/employee";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(EmployeeQuery query){
        return new UiPage(employeeService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editEmployee")
    public Employee beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Employee employee = employeeService.findOne(id);
            //把对应的关连对象设置为null
            employee.setDepartment(null);
            return employee;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Employee employee){
        return saveOrUpdate(employee);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editEmployee") Employee employee){
        return saveOrUpdate(employee);
    }

    private ResultJson saveOrUpdate(Employee employee){
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            employeeService.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }


    /**
     * 我们需要返回一个结构:{success:true,msg:xxxx}
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultJson delete(Long id){
        ResultJson resultJson = new ResultJson();
        try {
            employeeService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public boolean checkName(Long id,String username){
        if(id!=null){
            //1.代表是修改功能
            Employee dbEmployee = employeeService.findOne(id);
            //2.判断传过来的名称是否和输入的名称相等
            if(dbEmployee.getUsername().equals(username)){
                //3.相等时返回true
                return true;
            }
        }
        return employeeService.checkUsername(username);
    }

    /**
     * 导出功能:下载
     * @param map
     * @return
     */
    @RequestMapping("/download")
    public String download(ModelMap map,EmployeeQuery query, HttpServletRequest request) {
        //拿到所有数据
        List<Employee> list = employeeService.findByQuery(query);

        //获取到真实路径
        String realPath = request.getServletContext().getRealPath("");

        list.forEach(e -> {
            e.setHeadImage(realPath+e.getHeadImage());
            System.out.println(e.getHeadImage());
        });

        //设置一些属性
        ExportParams params = new ExportParams("员工管理", "明细", ExcelType.XSSF);
        //params.setFreezeCol(3);
        map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "employee");//文件名称
        //返回的名称 :easypoiExcelView -> 并没有找我的bean，而且当做一个路径去进行访问
        //  现在默认去找的视图解析器，而没有找我的那一个bean
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;//View名称
    }

}
