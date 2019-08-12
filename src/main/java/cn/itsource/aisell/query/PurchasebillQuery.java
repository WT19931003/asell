package cn.itsource.aisell.query;


import cn.itsource.aisell.domain.Purchasebill;

import java.util.Date;
import java.util.List;

/**
 * @author 我很OK
 * @since 2018-12-04 09:27:20
 */
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

public class PurchasebillQuery extends BaseQuery {

    private Integer status;
    private Date beginDate; //开始时间
    private Date endDate; //结束时间
    //拿到查询的条件
    @Override
    public Specification createSpec() {
        //把结束时间加一天
        Date tempDate = null;
        if(endDate!=null){
            tempDate = DateUtils.addDays(endDate, 1);
        }

        // eq:等于   ge:大于等于  le:小于等于 lt:小于
        Specification<Purchasebill> spec = Specifications.<Purchasebill>and()
                .eq(status!=null,"status",status)
                .ge(beginDate!=null,"vdate" ,beginDate)
                .lt(tempDate!=null,"vdate" ,tempDate)
                .build();
        return spec;
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
}