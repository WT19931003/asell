package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.query.PurchasebillQuery;
import cn.itsource.aisell.service.IPurchasebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/purchasebill")
public class PurchasebillController extends BaseController {

    @Autowired
    private IPurchasebillService purchasebillService;

    @RequestMapping("/index")
    public String index(){
        return "purchasebill/purchasebill";
    }

    @RequestMapping("/page")
    @ResponseBody
    public UiPage page(PurchasebillQuery query){
        return new UiPage(purchasebillService.findPageByQuery(query));
    }

    /**
     * 每次一个路径过来访问这个Controller中的内容，都会先执行这个方法
     *
     */
    @ModelAttribute("editPurchasebill")
    public Purchasebill beforeEdit(Long id,String cmd){
        //只有修改才会传update这个值过来，以前只有修改会传这个数据
        if(id!=null && "update".equals(cmd)){
            //根据id拿到这个对象
            Purchasebill purchasebill = purchasebillService.findOne(id);
            //把对应的关连对象设置为null
            purchasebill.setSupplier(null);
            purchasebill.setBuyer(null);
            purchasebill.getItems().clear();
            return purchasebill;
        }
        return null;
    }

    //添加功能
    @RequestMapping("/save")
    @ResponseBody
    public ResultJson save(Purchasebill purchasebill){
        return saveOrUpdate(purchasebill);
    }
    //修改功能
    @RequestMapping("/update")
    @ResponseBody
    public ResultJson update(@ModelAttribute("editPurchasebill") Purchasebill purchasebill){
        return saveOrUpdate(purchasebill);
    }

    private ResultJson saveOrUpdate(Purchasebill purchasebill){
        ResultJson resultJson = new ResultJson();
        try {
            //①.准备总金额与总数量
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal totalNum = new BigDecimal(0);
            //一方(purchasebill:采购订单)查找多方(purchasebillitem:采购订单明细)
            //System.out.println("一方找多方:"+purchasebill.getItems());
            //拿到所有多方
            List<Purchasebillitem> items = purchasebill.getItems();
            for (Purchasebillitem item : items) {
               //把一方设置给多方 【把订单设置到明细中去】
                item.setBill(purchasebill);
                //计算每个明细的小计
                item.setAmount(item.getNum().multiply(item.getPrice()));
                //②.把明细的数据与金额放进去
                totalAmount = totalAmount.add(item.getAmount());
                totalNum = totalNum.add(item.getNum());
            }
            //把总金额与总数量设置到采购订单中
            purchasebill.setTotalamount(totalAmount);
            purchasebill.setTotalnum(totalNum);
            purchasebillService.save(purchasebill);
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
            purchasebillService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setSuccess(false);
            resultJson.setMsg(e.getMessage());
        }
        return resultJson;
    }

}