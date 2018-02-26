package com.topschool.xm.controller.admin;


import com.topschool.xm.exception.BrandNotFoundException;
import com.topschool.xm.exception.FoodNotExistException;
import com.topschool.xm.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/food_manage")
public class FoodManageController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/menu_item/update")
    public Object update(@RequestParam("id") Integer id,
                         @RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "price", required = false) float price) throws FoodNotExistException {
        menuItemService.updateItem(id, name, price, null);
        return null;
    }

    @PostMapping("/menu_item/new")
    public Object addMenuItem(@RequestParam("id") Integer brandId,
                              @RequestParam("name") String name,
                              @RequestParam("price") float price) throws BrandNotFoundException {
        menuItemService.addItem(name, price, null, brandId);
        return null;
    }

    @PostMapping("/menu_item/delete")
    public Object deleteMenuItem(@RequestParam("id") Integer itemId) throws FoodNotExistException {
        menuItemService.deleteItem(itemId);
        return null;
    }

}
