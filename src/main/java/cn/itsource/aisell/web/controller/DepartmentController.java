package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Department;
import cn.itsource.aisell.query.DepartmentQuery;
import cn.itsource.aisell.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/index")
    public String index(){
        return "department/department";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(DepartmentQuery query){
        return new UiPage(departmentService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editDepartment")
    public Department beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Department department = departmentService.findOne(id);
            //把对应的关连对象设置为null
            return department;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Department department){
        return saveOrUpdate(department);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editDepartment") Department department){
        return saveOrUpdate(department);
    }

    private ResultJson saveOrUpdate(Department department){
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            departmentService.save(department);
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
            departmentService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}