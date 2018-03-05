package com.topschool.xm.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.topschool.xm.model.user.TokenInfo;
import org.springframework.stereotype.Component;

/**
 * @author 小强
 */
@Component
public interface TokenInfoDao {

    /**
     * 插入新记录
     *
     * @param pojo 新的token
     * @return 操作生效行数
     */
    int insert(@Param("pojo") TokenInfo pojo);

    /**
     * 插入所有
     *
     * @param pojo token集合
     * @return 操作生效行数
     */
    int insertList(@Param("pojos") List<TokenInfo> pojo);

    /**
     * 获取完整token
     *
     * @param pojo 不完整的token
     * @return 所有满足的token集合
     */
    List<TokenInfo> select(@Param("pojo") TokenInfo pojo);

    /**
     * 通过unionId获取token
     *
     * @param sessionId sessionId
     * @return 获取到的token记录
     */
    TokenInfo getBySessionId(@Param("sessionId") String sessionId);

    /**
     * 通过unionId获取token
     *
     * @param unionId unionId
     * @return 获取到的token记录
     */
    TokenInfo getByUnionId(@Param("unionId") String unionId);

    /**
     * 通过uid获取token
     *
     * @param uid unionId
     * @return 获取到的token记录
     */
    TokenInfo getByUid(@Param("uid") long uid);

    /**
     * 更新token记录
     *
     * @param pojo 新的token记录
     * @return 操作生效行数
     */
    int update(@Param("pojo") TokenInfo pojo);

}
