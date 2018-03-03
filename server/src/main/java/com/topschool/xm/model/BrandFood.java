package com.topschool.xm.model;

import java.math.BigDecimal;

/**
 * @author 小强
 * @date 2018/2/27 22:47
 */
public class BrandFood {
    private Long id;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Long brandId;
    private Boolean selected;

    public BrandFood() {
    }

    public BrandFood(String name, BigDecimal price, Long brandId) {
        this.name = name;
        this.price = price;
        this.brandId = brandId;
    }

    public BrandFood(Long brandId, boolean selected) {
        this.brandId = brandId;
        this.selected = selected;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
