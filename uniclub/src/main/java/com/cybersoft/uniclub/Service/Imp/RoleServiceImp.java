package com.cybersoft.uniclub.Service.Imp;

import com.cybersoft.uniclub.entity.RoleEntity;

import java.util.List;

public interface RoleServiceImp {
    List<RoleEntity> getAllRole();

    boolean deletedRoleById(int id);

}
