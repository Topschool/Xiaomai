package com.topschool.xm.dao.scratchcard;

import com.topschool.xm.model.scratchcard.ScratchLog;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 小强
 */
public interface ScratchLogMapper {

    Boolean insert(ScratchLog scratchLog);

    /**
     * wxId and scratchDate are not null
     *
     * @param scratchLog
     * @return
     */
    ScratchLog getOnesScratchResult(ScratchLog scratchLog);

    List<ScratchLog> getCurrentMouthScratchListByWxId(String id);

    List<ScratchLog> getTodayScratchResultList();

    Integer getMouthCountByWxId(String id);

    @MapKey("id")
    List<Map> getCurrentMouthTop(Integer size);

    Boolean isExist(@Param("id") String id, @Param("scratchDate") Date scratchDate);
}
