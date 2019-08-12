package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Purchasebillitem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Purchasebillitem:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface PurchasebillitemRepository extends BaseRepository<Purchasebillitem,Long> {

}