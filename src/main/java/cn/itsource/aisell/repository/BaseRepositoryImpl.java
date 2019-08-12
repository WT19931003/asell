package cn.itsource.aisell.repository;

import cn.itsource.aisell.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
/**
 * 实现父类中的三个方法
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    private final EntityManager entityManager;

    //必需要实现父类的这个构造器
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    //根据Query对象拿到分页的对象
    @Override
    public Page findPageByQuery(BaseQuery baseQuery) {
       //1.拿到查询条件
        Specification spec = baseQuery.createSpec();
        //2.拿到排序条件
        Sort sort = baseQuery.createSort();
        //3.拿到分页对象
        Pageable page = new PageRequest(baseQuery.getJpaPage(),baseQuery.getPageSize(),sort);
        //4.进行查询返回
        return super.findAll(spec,page);
    }

    //根据查询的对象拿到相应的数据
    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        //第一步：拿到所有高级查询条件
        Specification spec = baseQuery.createSpec();
        //第二步:拿到排序的值
        Sort sort = baseQuery.createSort();
        //第三步:拿到数据返回
        return findAll(spec, sort);
    }

    /**
     * 根据传入的JPQL,查询到相应的数据
     *  jpql: select .. where a = ? and b=?   values:问号的值
     */
    @Override
    public List findByJpql(String jpql, Object... values) {
        //第一步:创建Query对象
        Query query = entityManager.createQuery(jpql);
        //第二步:把值设置到Query对象中去
        if (values!=null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        //第三步：返回数据
        return query.getResultList();
    }
}
