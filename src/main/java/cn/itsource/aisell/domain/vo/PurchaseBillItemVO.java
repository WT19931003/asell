package cn.itsource.aisell.domain.vo;

import cn.itsource.aisell.domain.Purchasebillitem;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 一个普通的类:里面装咱们采购订单明细报表的数据
 */
public class PurchaseBillItemVO {
   //编号
    private Long id;
    //供应商
    private String supplierName;
    //采购员
    private String buyerName;
    //产品
    private String productName;
    //产品类型
    private String productTypeName;
    //采购日期
    private Date vdate;
    //明细数量
    private BigDecimal num;
    //明细价格
    private BigDecimal price;
    //明细小计
    private BigDecimal amount;
    //状态
    private Integer status;

    //分组字段(默认供应商)
    //如果你想根据什么东西分组,那么这个值就和那个东西的值一致
    private String groupField ;

    /**
     * 准备一个构造器，这个构造器中传入了一个采购单明细
     * @param item
     */
    public PurchaseBillItemVO(Purchasebillitem item,Integer groupType) {
        this.id = item.getId();
        this.supplierName = item.getBill().getSupplier().getName();
        this.buyerName = item.getBill().getBuyer().getUsername();
        this.productName = item.getProduct().getName();
        this.productTypeName = item.getProduct().getTypes().getName();
        this.vdate = item.getBill().getVdate();
        this.num = item.getNum();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.status = item.getBill().getStatus();

        //分组条件类型:1:供应商  2.采购员 3.月份
        if(groupType==1){
            groupField = this.supplierName;
        }else if(groupType==2){
            groupField =this.buyerName;
        }else if(groupType==3){
            //vdate是一个日期【只需要它的月份而已】
            groupField = (DateUtils.toCalendar(this.vdate).get(Calendar.MONTH)+1) +"月" ;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}
