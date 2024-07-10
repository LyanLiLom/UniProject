package com.cybersoft.uniclub.Service.Imp;

import com.cybersoft.uniclub.dto.ProductDTO;
import com.cybersoft.uniclub.entity.ProductEntity;
import com.cybersoft.uniclub.payload.request.InsertProductRequest;

import java.util.List;

public interface ProductServiceImp {
    boolean insertProduct(InsertProductRequest productRequest);
    List<ProductDTO> getAllProduct();
}
