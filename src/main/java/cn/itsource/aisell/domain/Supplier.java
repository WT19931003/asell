package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (供应商)实体类
 * @author makejava
 * @since 2018-12-04 09:27:24
 */
@Entity
@Table(name="supplier")
public class Supplier extends BaseDomain {
   
    private String name;

    public Supplier() {}
    public Supplier(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}