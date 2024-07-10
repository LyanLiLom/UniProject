package com.cybersoft.uniclub.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductDetailId implements Serializable {
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "id_tag")
    private int idTag;

    @Column(name = "id_category")
    private int idCategory;
    @Column(name = "id_color")
    private int idColor;
    @Column(name = "id_size")
    private int idSize;

    public ProductDetailId(){}

    public ProductDetailId(int idProduct, int idTag, int idCategory, int idColor, int idSize) {
        this.idProduct = idProduct;
        this.idTag = idTag;
        this.idCategory = idCategory;
        this.idColor = idColor;
        this.idSize = idSize;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }
}
