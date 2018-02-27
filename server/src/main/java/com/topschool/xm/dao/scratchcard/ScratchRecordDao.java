package com.topschool.xm.dao.scratchcard;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.topschool.xm.model.weapp.scratchcard.ScratchRecord;
import org.springframework.stereotype.Component;

@Component
public interface ScratchRecordDao {

    int insert(@Param("pojo") ScratchRecord pojo);

    int insertList(@Param("pojos") List<ScratchRecord> pojo);

    List<ScratchRecord> select(@Param("pojo") ScratchRecord pojo);

    int update(@Param("pojo") ScratchRecord pojo);

    @MapKey("id")
    List<Map> getCurrentMouthTop(@Param("size")int size, @Param("currentTime")long now);
}
