package com.topschool.xm.dao.scratchcard;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.weapp.scratchcard.Card;
import org.springframework.stereotype.Component;

@Component
public interface CardDao {

    int insert(@Param("pojo") Card pojo);

    int insertList(@Param("pojos") List< Card> pojo);

    List<Card> select(@Param("pojo") Card pojo);

    int update(@Param("pojo") Card pojo);

    List<Card> selectAll();
}
