package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2018-11-30 14:29:34
 */
@Entity
@Table(name="menu")
public class Menu extends BaseDomain {
   
    //菜单名称
    private String name;
    //菜单路径
    private String url;
    //图标
    private String icon;
    
    //private Long parentId;
    //配置多对一:多个子菜单对应一个父菜单
    //只要出生相互关系 -> 把这个对象解析成json后，都会造成死循环 (返回JSON是SpringMVC的功能)
    //   解决方案:让我们两边的其中一个不生成json
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    @JsonIgnore
    private Menu parent;

    /**
     * 是否需要配置一对多呢？
     *      要配:因为我们需要通过父亲（一方）找到儿子菜单（多方）
     * 这个儿子菜单需要交给JPA管理嘛？
     *      这个集合不能交给JPA管理,因为它会拿到所有的子菜单(权限中不允许的)
     *  @Transient:临时属性(不给这个属性交给JPA管理) -> JPA的注解
     *     * @return
     */
    @Transient
    private List<Menu> children = new ArrayList<>();

    
    public String getName() {
        return name;
    }
    public String getText() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                '}';
    }
}