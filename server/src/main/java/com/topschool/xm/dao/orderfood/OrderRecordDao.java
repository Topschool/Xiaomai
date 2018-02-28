package com.topschool.xm.dao.orderfood;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.OrderRecord;
import org.springframework.stereotype.Component;

/**
 * @author 小强
 */
@Component
public interface OrderRecordDao {

    int insert(@Param("pojo") OrderRecord pojo);

    int insertList(@Param("pojos") List< OrderRecord> pojo);

    List<OrderRecord> select(@Param("pojo") OrderRecord pojo);

    int update(@Param("pojo") OrderRecord pojo);

    int delete(@Param("uid") long uid, @Param("foodId") long foodId, @Param("currentTime") long now);

    int deleteTodayAll(@Param("uid") long uid, @Param("currentTime") long now);

    List<OrderRecord> getTodayOrderByUserId(@Param("uid") long uid, @Param("currentTime") long now);
}
