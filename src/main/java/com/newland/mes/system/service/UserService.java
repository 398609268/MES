package com.newland.mes.system.service;

import com.newland.mes.system.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser(int start,int end);
}
