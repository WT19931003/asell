package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Producttype;
import cn.itsource.aisell.service.IProducttypeService;
import cn.itsource.aisell.repository.ProducttypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProducttypeServiceImpl
        extends BaseServiceImpl<Producttype,Long> implements IProducttypeService {

    @Autowired
    private ProducttypeRepository producttypeRepository;

    @Override
    public List<Producttype> findChildren() {
        return producttypeRepository.findChildren();
    }
}