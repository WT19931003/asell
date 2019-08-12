package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Systemdictionarytype;
import cn.itsource.aisell.service.ISystemdictionarytypeService;
import cn.itsource.aisell.repository.SystemdictionarytypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SystemdictionarytypeServiceImpl
        extends BaseServiceImpl<Systemdictionarytype,Long> implements ISystemdictionarytypeService {

    @Autowired
    private SystemdictionarytypeRepository systemdictionarytypeRepository;
}