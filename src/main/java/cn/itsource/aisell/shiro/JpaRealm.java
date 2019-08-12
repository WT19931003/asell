package cn.itsource.aisell.shiro;

import cn.itsource.aisell.common.MD5Util;
import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.service.IEmployeeService;
import cn.itsource.aisell.service.IPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class JpaRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public String getName() {
        return "jpaRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //拿到用户的名称
        Employee employee = UserContext.getUser();
        System.out.println(employee);

        //根据用户名拿到对应的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //二.当前用户有的权限也应该从数据库中获取
        Set<String> perms =  permissionService.findByUser(employee.getId());
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }

    private Set<String> findPermsByName(String username) {
        Set<String> perms = new HashSet<>();
        perms.add("employee:index");
        perms.add("employee:page");
        perms.add("role:index");
        //perms.add("employee:*");
        return perms;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        //根据用户名拿到对应的员工
        Employee employee = employeeService.findByUsername(username);
        //员工不存在，代表这个用户名是没有的
        if(employee==null){
            return null;
        }
        //拿到相应的密码
        String password = employee.getPassword();
        //准备盐值
        ByteSource salt = ByteSource.Util.bytes(MD5Util.SALT);
        //  第一个参数:principal(登录之后，你把什么对象当作主要的对象)
        //    如果登录成功，主体就是咱们的当前登录用户
        SimpleAuthenticationInfo authenticationInfo =  new SimpleAuthenticationInfo(employee,password,salt,getName());
        return authenticationInfo;
    }

    private String findByName(String username) {
        if("admin".equals(username)){
            /**
             * 123456: 加密        e10adc3949ba59abbe56e057f20f883e
             * 123456: 加密10次 ： 4a95737b032e98a50c056c41f2fa9ec6
             * 123456: 加密10次,itsource盐值 ： 831d092d59f6e305ebcfa77e05135eac
             *
             */
            return "831d092d59f6e305ebcfa77e05135eac";
        }
        return null;
    }
}
