package cn.itsource.aisell.query;


import cn.itsource.aisell.domain.Producttype;
import java.util.List;

/**
 * @author 我很OK
 * @since 2018-12-03 11:27:00
 */
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ProducttypeQuery extends BaseQuery {
            private String name;
    //拿到查询的条件
    @Override
    public Specification createSpec() {
        Specification<Producttype> spec = Specifications.<Producttype>and()
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