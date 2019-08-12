package cn.itsource.aisell.domain;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 采购入库单明细
 *
 * @author makejava
 * @since 2018-12-07 11:20:56
 */
@Entity
@Table(name="stockincomebillitem")
public class Stockincomebillitem extends BaseDomain {
   

    private BigDecimal price;
    
    private BigDecimal num;
    
    private BigDecimal amount;
    
    private String descs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;// 多对一,非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id")
    private Stockincomebill bill;// 组合关系,非空

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

    public Stockincomebill getBill() {
        return bill;
    }

    public void setBill(Stockincomebill bill) {
        this.bill = bill;
    }
}