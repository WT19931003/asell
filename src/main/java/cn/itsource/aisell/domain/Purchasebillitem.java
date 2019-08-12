package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * (Purchasebillitem)实体类
 *
 * @author makejava
 * @since 2018-12-04 09:27:22
 */
@Entity
@Table(name="purchasebillitem")
public class Purchasebillitem extends BaseDomain {

    //明细单价(填写,不能为null)
    // 它是否等于产品的成本价
    private BigDecimal price;
    //数量(填写,不能为null)
    private BigDecimal num;
    //小计(计算  price*num)
    private BigDecimal amount;
    //备注(填写,可以为null)
    private String descs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;// 多对一,非空 产品
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id")
    @JsonIgnore //生成json的时候忽略这个属性[双向关系，生成json的时候是相互调用，需要有一方忽略对方]
    private Purchasebill bill;// 组合关系,非空

    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchasebill getBill() {
        return bill;
    }

    public void setBill(Purchasebill bill) {
        this.bill = bill;
    }
}