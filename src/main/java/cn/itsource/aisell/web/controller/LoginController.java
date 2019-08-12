package cn.itsource.aisell.web.controller;

import cn.itsource.aisell.common.ResultJson;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    //RESTful -> 每一个路径(请求方式)都是一个资源
    //用于跳转到登录页面
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    //登录的功能
    //返回json：代表成功还是失败
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultJson login(String username, String password){
        //1.拿到当前用户(可能还是游客，没有登录)
        Subject currentUser = SecurityUtils.getSubject();
        //2.如果这个用户没有登录,进行登录功能
        if(!currentUser.isAuthenticated()){
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(username,password);
                currentUser.login(token);
            }catch (UnknownAccountException e) {
                e.printStackTrace();
                System.out.println("用户名出现了错误！");
                return new ResultJson(false,"用户名不存在！");
            }catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                System.out.println("密码出现了错误！");
                return new ResultJson(false,"用户名或者密码出错！");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("出现了神秘错误！");
                return new ResultJson(false,"出现了神秘错误！");
            }
        }
        //登录成功后，需要把当前登录用户放到Session中去
        //  传统的方式，都是放到HttpSession中的
        //  咱们现在用了Shiro,而Shiro中是有session管理的(HttpSession里也有这个值)
        Employee employee = (Employee) currentUser.getPrincipal();
        UserContext.setUser(employee);

        //如果成功，返回正确的结果
        return new ResultJson();
    }

    @RequestMapping("/logout")
    public String logout(){
        //1.拿到当前用户(可能还是游客，没有登录)
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/s/login.jsp";
    }
}
