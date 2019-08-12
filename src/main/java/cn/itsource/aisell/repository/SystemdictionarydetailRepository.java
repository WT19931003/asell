package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Systemdictionarydetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Systemdictionarydetail:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface SystemdictionarydetailRepository extends BaseRepository<Systemdictionarydetail,Long> {

    //根据类型拿到相应的数据
    @Query("select o from Systemdictionarydetail o where o.types.sn = ?1")
    List<Systemdictionarydetail> findAllBySn(String sn);
}