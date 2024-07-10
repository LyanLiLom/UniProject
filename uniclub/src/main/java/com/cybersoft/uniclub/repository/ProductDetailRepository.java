package com.cybersoft.uniclub.repository;

import com.cybersoft.uniclub.entity.ProductDetailEntity;
import com.cybersoft.uniclub.entity.key.ProductDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, ProductDetailId> {

}
