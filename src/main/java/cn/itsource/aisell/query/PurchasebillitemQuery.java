package cn.itsource.aisell.query;


import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.domain.Purchasebillitem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 我很OK
 * @since 2018-12-04 09:27:22
 */
import com.github.wenhao.jpa.Specifications;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

public class PurchasebillitemQuery extends BaseQuery {

    private Integer status;
    private Date beginDate; //开始时间
    private Date endDate; //结束时间
    //分组条件类型:1:供应商  2.采购员 3.月份
    private Integer groupType = 1;

    //拿到查询的条件
    @Override
    public Specification createSpec() {
        //把结束时间加一天
        Date tempDate = null;
        if(endDate!=null){
            tempDate = DateUtils.addDays(endDate, 1);
        }
        // eq:等于   ge:大于等于  le:小于等于 lt:小于
        Specification<Purchasebillitem> spec = Specifications.<Purchasebillitem>and()
                .eq(status!=null,"bill.status",status)
                .ge(beginDate!=null,"bill.vdate" ,beginDate)
                .lt(tempDate!=null,"bill.vdate" ,tempDate)
                .build();
        return spec;
    }

    //准备一个接收参数的集合【才可以修改长度】
    List params = new ArrayList();

    //拼接咱们JPQL的where条件
    //  注意:有这个条件，才加这个参数
    public String createWhereJPQL(){
        StringBuilder whereJPQL = new StringBuilder();
        if(status!=null) {
            whereJPQL.append(" and o.bill.status = ? ");
            params.add(status);
        }
        if(beginDate!=null){
            whereJPQL.append(" and o.bill.vdate >= ? ");
            params.add(beginDate);
        }
        if(endDate!=null){
            Date tempDate = DateUtils.addDays(endDate, 1);
            whereJPQL.append(" and o.bill.vdate < ?");
            params.add(tempDate);
        }

        return whereJPQL.toString().replaceFirst("and", "where");
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public List getParams() {
        return params;
    }

    public String getGroupBy(){
        //String groupBy ="o.bill.supplier.name"; //groupType=1 供应商分组
        // String groupBy ="o.bill.buyer.username"; //groupType=2 采购员分组
        //String groupBy ="MONTH(o.bill.vdate)"; //groupType=3 月份分组
        if(groupType==1){
            return "o.bill.supplier.name";
        }else if(groupType==2){
            return "o.bill.buyer.username";
        }else{
            return "MONTH(o.bill.vdate)";
        }
    }
}