package cn.itsource.aisell.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 及时库存
 * @author makejava
 * @since 2018-12-07 11:29:59
 */
@Entity
@Table(name="productstock")
public class Productstock extends BaseDomain {
   
    private BigDecimal num; //数量

    private BigDecimal price; //单价
    
    private BigDecimal amount; //总金额

    private Date incomedate; //入库时间
    
    private Boolean warning; //是否报警
    private BigDecimal topnum; //多于某个数量报警
    private BigDecimal bottomnum; //少于某个数量的时候报警

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;// 多对一,非空 产品
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depot_id")
    private Depot depot;// 多对一,非空 仓库

    
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
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Date getIncomedate() {
        return incomedate;
    }

    public void setIncomedate(Date incomedate) {
        this.incomedate = incomedate;
    }
    
    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }
    
    public BigDecimal getTopnum() {
        return topnum;
    }

    public void setTopnum(BigDecimal topnum) {
        this.topnum = topnum;
    }
    
    public BigDecimal getBottomnum() {
        return bottomnum;
    }

    public void setBottomnum(BigDecimal bottomnum) {
        this.bottomnum = bottomnum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
}