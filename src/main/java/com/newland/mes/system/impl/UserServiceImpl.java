package com.newland.mes.system.impl;

import com.newland.mes.system.entity.User;
import com.newland.mes.system.mapper.UserMapper;
import com.newland.mes.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getAllUser(int start,int end) {
        return userMapper.getAllUser(start,end);
    }
}
