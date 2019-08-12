package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Dept;
import cn.itsource.aisell.query.DeptQuery;
import cn.itsource.aisell.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @RequestMapping("/index")
    public String index(){
        return "dept/dept";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(DeptQuery query){
        return new UiPage(deptService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editDept")
    public Dept beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Dept dept = deptService.findOne(id);
            //把对应的关连对象设置为null
            return dept;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Dept dept){
        return saveOrUpdate(dept);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editDept") Dept dept){
        return saveOrUpdate(dept);
    }

    private ResultJson saveOrUpdate(Dept dept){
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            deptService.save(dept);
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
            deptService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}