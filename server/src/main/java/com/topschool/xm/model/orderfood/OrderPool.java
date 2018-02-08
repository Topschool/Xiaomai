package com.topschool.xm.model.orderfood;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class OrderPool {
    private List<Food> foodList;
    private boolean status;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean inFoodList(Integer foodId){
        for (Food food : foodList) {
            if (food.getId().equals(foodId)) {
                return true;
            }
        }
        return false;
    }
}
