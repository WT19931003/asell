package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Systemdictionarydetail;
import cn.itsource.aisell.domain.Systemdictionarytype;
import cn.itsource.aisell.service.ISystemdictionarydetailService;
import cn.itsource.aisell.repository.SystemdictionarydetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SystemdictionarydetailServiceImpl
        extends BaseServiceImpl<Systemdictionarydetail,Long> implements ISystemdictionarydetailService {

    @Autowired
    private SystemdictionarydetailRepository systemdictionarydetailRepository;

    @Override
    public List<Systemdictionarydetail> findAllUnit() {
        return systemdictionarydetailRepository.findAllBySn(Systemdictionarytype.PRODUCT_UNIT);
    }

    @Override
    public List<Systemdictionarydetail> findAllBrand() {
        return systemdictionarydetailRepository.findAllBySn(Systemdictionarytype.PRODUCT_BRAND);
    }
}