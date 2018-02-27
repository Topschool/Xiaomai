package com.topschool.xm.model;

/**
 * @author 小强
 * @date 2018/2/27 22:57
 */
public class OrderRecord {
    private Long id;
    private Long foodId;
    private Long userId;
    private Long createTime;

    public OrderRecord() {
    }

    public OrderRecord(Long foodId, Long userId) {
        this.foodId = foodId;
        this.userId = userId;
        this.createTime = System.currentTimeMillis();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
