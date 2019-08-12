package cn.itsource.aisell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Dept)实体类
 *
 * @author makejava
 * @since 2018-11-25 15:13:48
 */
@Entity
@Table(name="dept")
public class Dept extends BaseDomain {
   
                                               
                


    
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}