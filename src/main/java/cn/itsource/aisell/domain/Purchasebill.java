package cn.itsource.aisell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * (采购订单)实体类
     *1.这个属性是什么意思
      2.这个属性的值从哪里来(前台传过来的，还是自动生成的。。。)
     3.这个属性是否可以为空
 * @author makejava
 * @since 2018-12-04 09:27:20
 */
@Entity
@Table(name="purchasebill")
public class Purchasebill extends BaseDomain {

    //交易时间(前台传过来的,不能null)
    private Date vdate;
    //总金额(计算出来的,不能null)
    private BigDecimal totalamount;
    //总数量(计算出来的,不能null)
    private BigDecimal totalnum;
    //录入时间(自动生成,当前时间,不能null)
    private Date inputtime  = new Date();;
    //审核时间(自动生成,可以为null)
    private Date auditortime;
    //状态(自动生成,默认填写后就是待审)
    // 0待审,1已审，-1作废
    private Integer status = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;// 多对一，非空 供应商(需要选择)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;// 多对一，可以为空 审核人(当前登录用户)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;// 多对一，非空 录入人 -> 谁添加录入人就是谁(跟单文员)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buyer_id")
    private Employee buyer;// 多对一，非空 采购员 -> 需要
    // 一般组合关系使用List
    // 一个订单会有多个明细
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Purchasebillitem> items = new ArrayList<Purchasebillitem>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }
    
    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }
    
    public BigDecimal getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(BigDecimal totalnum) {
        this.totalnum = totalnum;
    }
    
    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }
    
    public Date getAuditortime() {
        return auditortime;
    }

    public void setAuditortime(Date auditortime) {
        this.auditortime = auditortime;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public List<Purchasebillitem> getItems() {
        return items;
    }

    public void setItems(List<Purchasebillitem> items) {
        this.items = items;
    }
}