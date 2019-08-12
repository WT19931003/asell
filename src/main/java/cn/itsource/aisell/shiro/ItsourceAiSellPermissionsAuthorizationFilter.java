package cn.itsource.aisell.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItsourceAiSellPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            //System.out.println("我就是新拦截器，来打我啊！！！");
            //获取到请求头的：X-Requested-With:XMLHttpRequest
            //1.转成http的请求和响应
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse)response;
            //2.获到到请求头的值
            String header = req.getHeader("X-Requested-With");
           //3.进行判断，用不同的方式解决问题
            if(header!=null && "XMLHttpRequest".equals(header)){
                //代表是一个ajax请求 (返回json到前面)
                resp.setContentType("text/json;charset=UTF-8");
                resp.getWriter().print("{\"success\":false,\"msg\":\"没有权限\"}");
            }else{
                //正常请求，和以前一样处理
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }

        return false;
    }
}
