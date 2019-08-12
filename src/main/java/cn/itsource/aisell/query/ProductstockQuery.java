package cn.itsource.aisell.query;


import cn.itsource.aisell.domain.Productstock;
import java.util.List;

/**
 * @author 我很OK
 * @since 2018-12-07 11:29:59
 */
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProductstockQuery extends BaseQuery {
            private String name;
    //拿到查询的条件
    @Override
    public Specification createSpec() {
        Specification<Productstock> spec = Specifications.<Productstock>and()
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