package com.cybersoft.uniclub.Service;

import com.cybersoft.uniclub.Service.Imp.RoleServiceImp;
import com.cybersoft.uniclub.entity.RoleEntity;
import com.cybersoft.uniclub.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class RoleService implements RoleServiceImp {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<RoleEntity> getAllRole() {
        return rolesRepository.findAll();
    }

    @Override
    public boolean deletedRoleById(int id) {
        boolean isDelete = false;
        try {
            rolesRepository.deleteById(id);
            isDelete = true;
        } catch (Exception e) {
            System.out.println("Lỗi xóa role:" + e.getMessage());
        }
        return isDelete;
    }

}

