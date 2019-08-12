package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Product;
import cn.itsource.aisell.service.IProductService;
import cn.itsource.aisell.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductServiceImpl
        extends BaseServiceImpl<Product,Long> implements IProductService {

    @Autowired
    private ProductRepository productRepository;
}