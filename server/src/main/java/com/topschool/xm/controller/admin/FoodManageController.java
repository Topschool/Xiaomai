package com.topschool.xm.controller.admin;


import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.ResultBody;
import com.topschool.xm.service.admin.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小强
 */
@RestController
@RequestMapping("/admin/food_manage")
public class FoodManageController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/menu_item/update")
    public ResultBody<?> update(@RequestParam("id") Integer id,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "price", required = false) float price) throws SystemException {
        menuItemService.updateItem(id, name, price);
        return null;
    }

    @PostMapping("/menu_item/new")
    public ResultBody<?> addMenuItem(@RequestParam("id") Integer brandId,
                              @RequestParam("name") String name,
                              @RequestParam("price") float price) throws SystemException {
        menuItemService.addItem(name, price, brandId);
        return null;
    }

    @PostMapping("/menu_item/delete")
    public ResultBody<?> deleteMenuItem(@RequestParam("id") Integer itemId) throws SystemException {
        menuItemService.deleteItem(itemId);
        return null;
    }

}
