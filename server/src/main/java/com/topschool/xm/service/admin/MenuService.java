package com.topschool.xm.service.admin;

import com.topschool.xm.exception.SystemException;
import com.topschool.xm.model.scratchcard.Brand;
import com.topschool.xm.model.orderfood.BrandFood;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 小强
 */
public interface MenuService {
    /**
     * 获取所有的菜单
     *
     * @return 所有的菜单列表
     */
    List<Brand> getAllMenu();

    /**
     * 获取菜单中的所有菜的信息
     *
     * @param id 菜单id
     * @return 菜单中对应的菜
     */
    List<BrandFood> getMenuItem(Integer id);


    /**
     * 更具菜单id更新菜单信息
     *
     * @param id          菜单id
     * @param name        菜单名
     * @param logo        菜单logo文件
     * @param description 菜单介绍
     * @throws SystemException 对应的菜单不存在
     * @throws IOException            logo文件不存在
     */
    void updateMenu(Integer id, String name, MultipartFile logo, String description) throws SystemException, IOException;

    /**
     * 删除菜单，包括菜单中的所有菜
     *
     * @param brandId 所需删除的菜单id
     * @throws SystemException 对应的菜单的平拍不存在
     */
    void deleteMenu(long brandId) throws SystemException;

    /**
     * 添加新的菜单
     *
     * @param name        菜单名
     * @param logo        菜单logo文件
     * @param description 菜单介绍
     * @throws IOException logo文件不存在
     */
    void addMenu(String name, MultipartFile logo, String description) throws IOException;
}
