package com.topschool.xm.dao.orderfood;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.OrderItem;
import org.springframework.stereotype.Component;

/**
 * @author 小强
 */
@Component
public interface OrderItemDao {

    int insert(@Param("pojo") OrderItem pojo);

    int insertList(@Param("pojos") List<OrderItem> pojo);

    List<OrderItem> select(@Param("pojo") OrderItem pojo);

    int update(@Param("pojo") OrderItem pojo);

    int delete(@Param("orderId") long orderId, @Param("foodId") long foodId);

    int deleteByOrderId(long id);

    List<OrderItem> getOrderItemByOrderId(Long id);
}
