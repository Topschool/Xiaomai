package com.topschool.xm.dao.orderfood;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.BrandFood;
import org.springframework.stereotype.Component;

/**
 * @author 小强
 */
@Component
public interface BrandFoodDao {

    int insert(@Param("pojo") BrandFood pojo);

    int insertList(@Param("pojos") List< BrandFood> pojo);

    List<BrandFood> select(@Param("pojo") BrandFood pojo);

    BrandFood selectById(long id);

    int update(@Param("pojo") BrandFood pojo);

}
