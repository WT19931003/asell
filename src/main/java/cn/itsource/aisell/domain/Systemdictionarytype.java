package cn.itsource.aisell.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (Systemdictionarytype)实体类
 *
 * @author makejava
 * @since 2018-12-03 11:18:14
 */
@Entity
@Table(name="systemdictionarytype")
public class Systemdictionarytype extends BaseDomain {

    public static final String PRODUCT_BRAND="productBrand";//品牌sn
    public static final String PRODUCT_UNIT="productUnit";//单位sn
    @Column(updatable = false)
    private String sn;
    
    private String name;

    
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}