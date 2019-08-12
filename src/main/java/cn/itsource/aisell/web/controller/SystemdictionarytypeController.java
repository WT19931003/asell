package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Systemdictionarytype;
import cn.itsource.aisell.query.SystemdictionarytypeQuery;
import cn.itsource.aisell.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemdictionarytype")
public class SystemdictionarytypeController extends BaseController {

    @Autowired
    private ISystemdictionarytypeService systemdictionarytypeService;

    @RequestMapping("/index")
    public String index(){
        return "systemdictionarytype/systemdictionarytype";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(SystemdictionarytypeQuery query){
        return new UiPage(systemdictionarytypeService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editSystemdictionarytype")
    public Systemdictionarytype beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Systemdictionarytype systemdictionarytype = systemdictionarytypeService.findOne(id);
            //把对应的关连对象设置为null
            return systemdictionarytype;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Systemdictionarytype systemdictionarytype){
        return saveOrUpdate(systemdictionarytype);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editSystemdictionarytype") Systemdictionarytype systemdictionarytype){
        return saveOrUpdate(systemdictionarytype);
    }

    private ResultJson saveOrUpdate(Systemdictionarytype systemdictionarytype){
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            systemdictionarytypeService.save(systemdictionarytype);
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
            systemdictionarytypeService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}