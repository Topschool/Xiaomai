package com.topschool.xm.dao.orderfood;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.orderfood.Order;
import org.springframework.stereotype.Component;

@Component
public interface OrderDao {

    int insert(@Param("pojo") Order pojo);

    int insertList(@Param("pojos") List< Order> pojo);

    List<Order> select(@Param("pojo") Order pojo);

    Order selectByUid(@Param("uid")Long uid, @Param("now")Long now);

    int update(@Param("pojo") Order pojo);

    int deleteByOrderId(Long id);
}
