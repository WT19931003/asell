package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.common.UserContext;
import cn.itsource.aisell.domain.Purchasebill;
import cn.itsource.aisell.service.IPurchasebillService;
import cn.itsource.aisell.repository.PurchasebillRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchasebillServiceImpl
        extends BaseServiceImpl<Purchasebill,Long> implements IPurchasebillService {

    @Autowired
    private PurchasebillRepository purchasebillRepository;

    @Override
    @Transactional
    public void save(Purchasebill purchasebill) {
       if(purchasebill.getId()==null){
        //添加 -> 把登录用户放到对象中去
           purchasebill.setInputUser(UserContext.getUser());
       }
       purchasebillRepository.save(purchasebill);
    }
}