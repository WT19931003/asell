package cn.itsource.aisell.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController extends BaseController {

    //跳到主页面
    @RequestMapping("/main")
    public String index(){
        return "main";
    }
}
