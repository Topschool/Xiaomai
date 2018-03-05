package com.topschool.xm.model.vo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 小强
 */
public class FoodOrderForm {
    @NotNull
    private long uid;
    @NotNull
    private List<Long> foods;
    @NotNull
    private String remark;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<Long> getFoods() {
        return foods;
    }

    public void setFoods(List<Long> foods) {
        this.foods = foods;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
