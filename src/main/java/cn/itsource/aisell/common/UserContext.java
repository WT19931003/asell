package cn.itsource.aisell.common;

import cn.itsource.aisell.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 当前登录用户的一些操作
 */
public class UserContext {

    public static final String LOGIN_USER_IN_SESSION = "loginUser";

    //传入一个员工，把它放到Session中去
    public static void setUser(Employee employee){
        //1.拿到subject
        Subject subject = SecurityUtils.getSubject();
        //2.subject中拿到session
        Session session = subject.getSession();
        //3.把用户放到session中
        session.setAttribute(LOGIN_USER_IN_SESSION, employee);
    }

    //从session中获取到一个员工
    public static Employee getUser(){
        //1.拿到subject
        Subject subject = SecurityUtils.getSubject();
        //2.subject中拿到session
        Session session = subject.getSession();
        //3.获到到员工
        return  (Employee) session.getAttribute(LOGIN_USER_IN_SESSION);
    }
}
