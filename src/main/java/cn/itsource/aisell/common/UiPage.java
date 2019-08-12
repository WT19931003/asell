package cn.itsource.aisell.common;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 传给前台的分页对象
 */
public class UiPage {
    //装咱们当前页的数据
    private List rows = new ArrayList();
    //总条数
    private Long total;

    public UiPage(Page page) {
        this.rows = page.getContent();
        this.total = page.getTotalElements();
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
