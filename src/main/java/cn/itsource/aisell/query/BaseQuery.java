package cn.itsource.aisell.query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * 父类的作用:
 *      1.公共的代码  2.规范子类
 */
public abstract class BaseQuery {

    //当前页
    private int currentPage = 1;
    //每页条数
    private int pageSize = 10;
    //排序名称
    private String orderName;
    //排序的类型 DESC/ASC
    private String orderType = "ASC";

    //返回一个查询规则
    public abstract Specification createSpec();

    //抽取排序方法
    public Sort createSort(){
        //创建排序对象
        Sort sort = null;
        //如果排序条件不会空，我们才创建Sort这个对象
        if(StringUtils.isNotBlank(orderName)){
            Sort.Direction sortType = Sort.Direction.ASC;
            if(orderType.toUpperCase().equals("DESC")){
                sortType = Sort.Direction.DESC;
            }
            sort = new Sort(sortType,orderName);
        }
        return sort;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    //JPA分页的时候使用这个方法
    public int getJpaPage() {
        return currentPage-1;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    //兼容咱们easyui的分页传参
    public void setPage(int page) {
        this.currentPage = page;
    }
    //兼容咱们easyui的分页传参
    public void setRows(int rows) {
        this.pageSize = rows;
    }
}
