package com.topschool.xm.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.topschool.xm.model.UserStatus;
import org.springframework.stereotype.Component;

@Component
public interface UserStatusDao {

    int insert(@Param("pojo") UserStatus pojo);

    int insertList(@Param("pojos") List< UserStatus> pojo);

    List<UserStatus> select(@Param("pojo") UserStatus pojo);

    UserStatus selectById(Long id);

    int update(@Param("pojo") UserStatus pojo);

}
