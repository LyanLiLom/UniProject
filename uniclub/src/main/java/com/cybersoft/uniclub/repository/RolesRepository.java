package com.cybersoft.uniclub.repository;

import com.cybersoft.uniclub.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RoleEntity,Integer> {
}
