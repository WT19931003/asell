package cn.itsource.aisell.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * 采购入库单
 *
 * @author makejava
 * @since 2018-12-07 11:20:55
 */
@Entity
@Table(name="stockincomebill")
public class Stockincomebill extends BaseDomain {

    private Date vdate;
    
    private BigDecimal totalamount;
    
    private BigDecimal totalnum;
    
    private Date inputtime;
    
    private Date auditortime;
    //0:待审  1：已审   -1：作废
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;// 多对一，非空
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;// 多对一，可以为空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;// 多对一，非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "keeper_id")
    private Employee keeper;// 多对一，非空(库管员)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depot_id")
    private Depot depot;// 多对一，非空 (仓库)
    // 一般组合关系使用List
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Stockincomebillitem> items = new ArrayList<Stockincomebillitem>();

    public Date getVdate() {
        return vdate;
    }

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

    public Employee getKeeper() {
        return keeper;
    }

    public void setKeeper(Employee keeper) {
        this.keeper = keeper;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public List<Stockincomebillitem> getItems() {
        return items;
    }

    public void setItems(List<Stockincomebillitem> items) {
        this.items = items;
    }
}