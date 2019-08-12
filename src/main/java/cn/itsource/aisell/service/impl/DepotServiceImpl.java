package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Depot;
import cn.itsource.aisell.service.IDepotService;
import cn.itsource.aisell.repository.DepotRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DepotServiceImpl
        extends BaseServiceImpl<Depot,Long> implements IDepotService {

    @Autowired
    private DepotRepository depotRepository;
}