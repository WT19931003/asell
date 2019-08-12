package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Stockincomebill;
import cn.itsource.aisell.query.StockincomebillQuery;
import cn.itsource.aisell.service.IStockincomebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/stockincomebill")
public class StockincomebillController extends BaseController {

    @Autowired
    private IStockincomebillService stockincomebillService;

    @RequestMapping("/index")
    public String index(){
        return "stockincomebill/stockincomebill";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(StockincomebillQuery query){
        return new UiPage(stockincomebillService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editStockincomebill")
    public Stockincomebill beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Stockincomebill stockincomebill = stockincomebillService.findOne(id);
            //把对应的关连对象设置为null
            return stockincomebill;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Stockincomebill stockincomebill){
        return saveOrUpdate(stockincomebill);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editStockincomebill") Stockincomebill stockincomebill){
        return saveOrUpdate(stockincomebill);
    }

    private ResultJson saveOrUpdate(Stockincomebill stockincomebill){
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            stockincomebillService.save(stockincomebill);
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
            stockincomebillService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}