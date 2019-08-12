package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Systemdictionarydetail;
import cn.itsource.aisell.domain.Systemdictionarytype;

import java.util.List;

public interface ISystemdictionarydetailService extends IBaseService<Systemdictionarydetail,Long>{

    public List<Systemdictionarydetail> findAllUnit();

    public List<Systemdictionarydetail> findAllBrand();
}