package com.roye.serviceImpl;

import com.roye.domain.User;
import com.roye.mapper.UserMapper;
import com.roye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserById(int userid) {
        return userMapper.getUserById(userid);
    }
}
