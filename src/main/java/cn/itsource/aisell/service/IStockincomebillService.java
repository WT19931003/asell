package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Employee;
import cn.itsource.aisell.domain.Stockincomebill;

public interface IStockincomebillService extends IBaseService<Stockincomebill,Long>{

    /**
     * 审核功能:
     *  Employee auditor:审核人
     *  Long billId:审核单据的id
     * @param auditor
     */
    void auding(Employee auditor,Long billId);
}