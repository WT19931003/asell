package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu,Long>{
    //根据当前登录用户拿到他对应的菜单
    List<Menu>  findTreeByLoginUser();
}