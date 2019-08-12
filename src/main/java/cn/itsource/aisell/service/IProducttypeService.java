package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Producttype;

import java.util.List;

public interface IProducttypeService extends IBaseService<Producttype,Long>{

    List<Producttype> findChildren();;
}