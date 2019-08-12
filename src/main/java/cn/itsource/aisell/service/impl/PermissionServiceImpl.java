package cn.itsource.aisell.service.impl;

import cn.itsource.aisell.domain.Permission;
import cn.itsource.aisell.service.IPermissionService;
import cn.itsource.aisell.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Service
public class PermissionServiceImpl
        extends BaseServiceImpl<Permission,Long> implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findByUser(Long empId) {
        return permissionRepository.findByUser(empId);
    }
}