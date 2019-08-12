package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Supplier;
import cn.itsource.aisell.service.ISupplierService;
import cn.itsource.aisell.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SupplierServiceImpl
        extends BaseServiceImpl<Supplier,Long> implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
}