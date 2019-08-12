package cn.itsource.aisell.query;


import cn.itsource.aisell.domain.Menu;
import java.util.List;

/**
 * @author 我很OK
 * @since 2018-11-30 14:29:34
 */
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class MenuQuery extends BaseQuery {
            private String name;
    //拿到查询的条件
    @Override
    public Specification createSpec() {
        Specification<Menu> spec = Specifications.<Menu>and()
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