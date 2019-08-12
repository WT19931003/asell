package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Purchasebillitem;
import cn.itsource.aisell.domain.vo.PurchaseBillItemVO;
import cn.itsource.aisell.query.PurchasebillitemQuery;

import java.util.List;
import java.util.Map;

public interface IPurchasebillitemService extends IBaseService<Purchasebillitem,Long>{

    /**
     * 根据条件拿到明细的报表数据
     * @param query
     * @return
     */
    List<PurchaseBillItemVO> findItems(PurchasebillitemQuery query);

    /**
     * 根据条件拿到对应的图表信息
     * @param query
     * @return
     */
    List<Map> findCharts(PurchasebillitemQuery query);

}