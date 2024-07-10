package com.cybersoft.uniclub.entity;

import com.cybersoft.uniclub.entity.key.ProductDetailId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "productdetail")
public class ProductDetailEntity {
    @EmbeddedId
    private ProductDetailId id;

    @Column(name = "soluong")
    private int soLuong;

    public ProductDetailId getId() {
        return id;
    }

    public void setId(ProductDetailId id) {
        this.id = id;
    }

    public int getSoluong() {
        return soLuong;
    }

    public void setSoluong(int soluong) {
        this.soLuong = soluong;
    }
}
