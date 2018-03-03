package com.topschool.xm.model;

import javax.validation.constraints.NotNull;

/**
 * @author 小强
 * @date 2018/2/27 22:57
 */
public class OrderItem {
    private Long id;
    private Long orderId;
    @NotNull
    private Long foodId;
    private Integer count;
    private Long createTime;

    public OrderItem() {
    }

    public OrderItem(Long foodId) {
        this(foodId, 1);
    }

    private OrderItem(Long foodId, Integer count){
        this.foodId = foodId;
        this.count = count;
        this.createTime = System.currentTimeMillis();
    }

    public OrderItem(Long foodId, Integer count, Long orderId){
        this.foodId = foodId;
        this.count = count;
        this.orderId = orderId;
        this.createTime = System.currentTimeMillis();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
