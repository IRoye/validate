package com.roye.mapper;

import com.roye.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getUserlist();

    User getUserById(@Param("userid") int userid);
}
