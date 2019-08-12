package cn.itsource.aisell.service;

import cn.itsource.aisell.domain.Permission;

import java.util.Set;

public interface IPermissionService extends IBaseService<Permission,Long>{

    Set<String> findByUser(Long empId);
}