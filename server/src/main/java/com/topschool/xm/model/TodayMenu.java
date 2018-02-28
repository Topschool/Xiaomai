package com.topschool.xm.model;

import com.topschool.xm.util.OrderFoodStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小强
 */
@Component
public class TodayMenu {

    private OrderFoodStatus status = OrderFoodStatus.UNINIT;

    private List<BrandFood> menu;

    private Brand todayBrand;

    public OrderFoodStatus getStatus() {
        return status;
    }

    public void setStatus(OrderFoodStatus status) {
        this.status = status;
    }

    public List<BrandFood> getMenu() {
        return menu;
    }

    public void setMenu(List<BrandFood> menu) {
        this.menu = menu;
    }

    public Brand getTodayBrand() {
        return todayBrand;
    }

    public void setTodayBrand(Brand todayBrand) {
        this.todayBrand = todayBrand;
    }
}
