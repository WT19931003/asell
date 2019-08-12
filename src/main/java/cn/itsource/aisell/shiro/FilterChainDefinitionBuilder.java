package cn.itsource.aisell.shiro;

import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilterChainDefinitionBuilder {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 返回的就是咱们的权限过滤的Map
     *  在代码中以后就可以操作数据库了
     *   /s/login.jsp = anon
        /login = anon
        /s/permission.jsp = perms[user:index]
        /** = authc
     * @return
     */
    public Map<String,String> builderFilterChainDefinitionMap(){
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //anon：不需要登录也可以访问
        filterChainDefinitionMap.put("/login", "anon");
        //对所有静态资源进行放实
        filterChainDefinitionMap.put("*.js","anon");
        filterChainDefinitionMap.put("*.css","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/easyui/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/json/**","anon");

        //对应的权限拦截(以后要从数据库中拿到)
        //一.所有权限拦截从数据库中获取
       // filterChainDefinitionMap.put("/s/permission.jsp", "perms[user:index]");
        //拿到数据库中所有的权限
        List<Permission> permissions = permissionService.findAll();
        permissions.forEach(p -> {
            filterChainDefinitionMap.put(p.getUrl(), "aiPerms["+p.getSn()+"]");
        });

        //所有的访问都需要登录
        filterChainDefinitionMap.put("/**", "authc");

        return filterChainDefinitionMap;
    }
}
