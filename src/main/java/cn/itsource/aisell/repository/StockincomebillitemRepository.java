package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Stockincomebillitem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Stockincomebillitem:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface StockincomebillitemRepository extends BaseRepository<Stockincomebillitem,Long> {

}