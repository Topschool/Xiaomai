package com.topschool.xm.controller.admin;

import com.topschool.xm.exception.BrandNotFoundException;
import com.topschool.xm.service.MenuService;
import com.topschool.xm.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 小强
 */
@RestController
@RequestMapping("/admin/brand_manage")
public class MenuManageController {

    @Autowired
    private OrderFoodService orderFoodService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/today_menu/select_brand")
    public ResponseEntity<?> selectBrand(Integer id) {
        orderFoodService.initOrderFoodSystem(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/brands")
    public Object restaurants() {
        return menuService.getAllMenu();
    }

    @GetMapping("/brand/{id}")
    public Object foodList(@PathVariable(value = "id") Integer brandId) {
        List list = menuService.getMenuItem(brandId);
        return list;
    }

    @PostMapping("/brand/new")
    public Object addBrand(@RequestParam(value = "name") String name,
                           @RequestParam(value = "logo", required = false) MultipartFile logo,
                           @RequestParam(value = "description", required = false) String description) throws IOException {
        menuService.addMenu(name, logo, description);
        return null;
    }

    @PostMapping("/brand/update")
    public Object updateBrand(@RequestParam(value = "brandId") Integer brandId,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "logo", required = false) MultipartFile logo,
                              @RequestParam(value = "description", required = false) String description) throws IOException, BrandNotFoundException {
        menuService.updateMenu(brandId, name, logo, description);
        return null;
    }

    @PostMapping("/brand/delete")
    public Object deleteBrand(Integer id) throws BrandNotFoundException {
        menuService.deleteMenu(id);
        return null;
    }

}
