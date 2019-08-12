package cn.itsource.aisell.query;


import cn.itsource.aisell.domain.Supplier;
import java.util.List;

/**
 * @author 我很OK
 * @since 2018-12-04 09:27:24
 */
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class SupplierQuery extends BaseQuery {
            private String name;
    //拿到查询的条件
    @Override
    public Specification createSpec() {
        Specification<Supplier> spec = Specifications.<Supplier>and()
                 .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                .build();
        return spec;
    }
    
        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}