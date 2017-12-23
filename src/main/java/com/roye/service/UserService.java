package com.roye.service;

import com.roye.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User getUserById(int userid);

}
