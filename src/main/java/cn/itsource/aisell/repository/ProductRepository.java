package cn.itsource.aisell.repository;


import cn.itsource.aisell.domain.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Product:代表你CRUD要操作的domain的类型
 * Long：代表这个类的主键类型
 */
public interface ProductRepository extends BaseRepository<Product,Long> {

}