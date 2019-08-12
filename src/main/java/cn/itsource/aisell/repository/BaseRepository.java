package cn.itsource.aisell.repository;

import cn.itsource.aisell.query.BaseQuery;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * NoRepositoryBean:扫描到了也不要自动创建子类(由我自己实现)
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>  extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{

    //根据Query拿到分页对象(分页)
    Page findPageByQuery(BaseQuery baseQuery);

    //根据Query拿到对应的所有数据(不分页)
    List<T> findByQuery(BaseQuery baseQuery);

    //根据jpql与对应的参数拿到数据
    // jpql: select .. where a = ? and b=?   values:问号的值
    List findByJpql(String jpql,Object... values);
}
