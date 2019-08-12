package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Productstock;
import cn.itsource.aisell.query.ProductstockQuery;
import cn.itsource.aisell.service.IProductstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/productstock")
public class ProductstockController extends BaseController {

    @Autowired
    private IProductstockService productstockService;

    @RequestMapping("/index")
    public String index(){
        return "productstock/productstock";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(ProductstockQuery query){
        return new UiPage(productstockService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editProductstock")
    public Productstock beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Productstock productstock = productstockService.findOne(id);
            //把对应的关连对象设置为null
            return productstock;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Productstock productstock){
        return saveOrUpdate(productstock);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editProductstock") Productstock productstock){
        return saveOrUpdate(productstock);
    }

    private ResultJson saveOrUpdate(Productstock productstock){
        ResultJson resultJson = new ResultJson();
        try {
           // int i = 1/0;
            productstockService.save(productstock);
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
            productstockService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}