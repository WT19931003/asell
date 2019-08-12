package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Stockincomebillitem;
import cn.itsource.aisell.service.IStockincomebillitemService;
import cn.itsource.aisell.repository.StockincomebillitemRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StockincomebillitemServiceImpl
        extends BaseServiceImpl<Stockincomebillitem,Long> implements IStockincomebillitemService {

    @Autowired
    private StockincomebillitemRepository stockincomebillitemRepository;
}