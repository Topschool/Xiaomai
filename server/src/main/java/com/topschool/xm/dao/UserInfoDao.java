package com.topschool.xm.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.user.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author 小强
 */
@Component
public interface UserInfoDao {

    int insert(@Param("pojo") UserInfo pojo);

    int insertList(@Param("pojos") List< UserInfo> pojo);

    List<UserInfo> select(@Param("pojo") UserInfo pojo);

    int update(@Param("pojo") UserInfo pojo);

    UserInfo selectById(long id);

    UserInfo selectByUnionId(String id);
}
