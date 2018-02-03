package com.topschool.xm.dao;

import com.topschool.xm.model.scratchcard.ScratchLog;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface ScratchLogMapper {

    Boolean insert(ScratchLog scratchLog);

    /**
     * wxId and scratchDate are not null
     * @param scratchLog
     * @return
     */
    ScratchLog getOnesScratchResult(ScratchLog scratchLog);

    List<ScratchLog> getCurrentMouthScratchListByWxId(String id);

    List<ScratchLog> getTodayScratchResultList();

    Integer getMouthCountByWxId(String id);

    @MapKey("id")
    List<Map> getCurrentMouthTop(Integer size);
}
