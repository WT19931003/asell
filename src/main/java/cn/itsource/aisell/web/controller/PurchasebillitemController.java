package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UiPage;
import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.domain.vo.PurchaseBillItemVO;
import cn.itsource.aisell.query.PurchasebillitemQuery;
import cn.itsource.aisell.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/purchasebillitem")
public class PurchasebillitemController extends BaseController {

    @Autowired
    private IPurchasebillitemService purchasebillitemService;

    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/purchasebillitem";
    }

    @RequestMapping("/findItems")
    @ResponseBody
    public List<PurchaseBillItemVO> findItems(PurchasebillitemQuery query){
        return purchasebillitemService.findItems(query);
    }

    @RequestMapping("/findCharts")
    @ResponseBody
    public List<Map> findCharts(PurchasebillitemQuery query){
        return purchasebillitemService.findCharts(query);
    }


}