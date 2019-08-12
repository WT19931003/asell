package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Menu;
import cn.itsource.aisell.service.IMenuService;
import cn.itsource.aisell.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl
        extends BaseServiceImpl<Menu,Long> implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findTreeByLoginUser() {
        //1.拿到当前登录用户
        Employee loginUser = UserContext.getUser();
        //2.根据当前登录用户，拿到他所有的菜单(子菜单)
        List<Menu> childrenMenus = menuRepository.findByUser(loginUser.getId());
        //3.准备一个空的父菜单
        List<Menu> parentMenus = new ArrayList<>();
        //4.遍历子菜单(去进行父菜单的设置)
        for (Menu children : childrenMenus) {
            //①.通过子菜单拿到父菜单
            Menu parent = children.getParent();
            //②.判断父菜单集合中是否已经存在这个父菜单了
            if(!parentMenus.contains(parent)){
                //不包含，主把它放到集合中
                parentMenus.add(parent);
            }
            //③.把当前遍历的这个子菜单放到父菜单中去
            parent.getChildren().add(children);
        }
        return parentMenus;
    }
}