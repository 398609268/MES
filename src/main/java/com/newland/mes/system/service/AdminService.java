package com.newland.mes.system.service;

import com.newland.mes.system.entity.User;
import com.newland.mes.system.entity.UserLoginParam;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;


public interface AdminService {

    public ResponseEntity<Object> login(String username, String password, HttpServletRequest request);

    public User getUserByUserName(String username);
}
