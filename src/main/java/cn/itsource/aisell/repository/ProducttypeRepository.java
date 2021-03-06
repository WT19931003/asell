package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Producttype;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Producttype:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface ProducttypeRepository extends BaseRepository<Producttype,Long> {

    //拿到所有子类型
    @Query("select o from Producttype o where o.parent.id is not null")
    List<Producttype> findChildren();
}