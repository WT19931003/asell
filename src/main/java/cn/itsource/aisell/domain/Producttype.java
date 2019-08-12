package cn.itsource.aisell.domain;

import javax.persistence.*;

/**
 * (Producttype)实体类
 *
 * @author makejava
 * @since 2018-12-03 11:27:00
 */
@Entity
@Table(name = "producttype")
public class Producttype extends BaseDomain {

    private String name;

    private String descs;


    //类型分两级，有一个自关联
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Producttype parent;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Producttype getParent() {
        return parent;
    }

    public void setParent(Producttype parent) {
        this.parent = parent;
    }

    //用于完成分组功能
    public String getGroup() {
        if (parent != null) {
            return parent.getName();
        }
        return "";
    }
}