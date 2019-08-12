package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.query.BaseQuery;
import cn.itsource.aisell.repository.BaseRepository;
import cn.itsource.aisell.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T,ID extends Serializable> implements IBaseService<T,ID> {

    /**
     * 注入的是一个BaseRepository类型的对象
     *      BaseRepository类型的对象有很多很多很多
     *           EmployeeRepository extends BaseRepository<Employee,Long>
     *           DepartmentRepository extends BaseRepository<Department,Long>
     *           RoleRepository extends BaseRepository<Role,Long>
     *           ...
     *   举例(父类是不创建对象的):
     *          EmployeeService extends BaseServiceImpl<Employee,Long>
     *    employeeService =  new EmployeeService()
     *    根据它的类型以及泛型的类型结合进行注入
     */
    @Autowired
    private BaseRepository<T,ID> baseRepository;

    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Override
    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page findPageByQuery(BaseQuery baseQuery) {
        return baseRepository.findPageByQuery(baseQuery);
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        return baseRepository.findByQuery(baseQuery);
    }

    @Override
    public List findByJpql(String jpql, Object... values) {
        return baseRepository.findByJpql(jpql, values);
    }
}
