package com.newland.mes.system.controller;

import com.newland.mes.system.entity.User;
import com.newland.mes.system.entity.UserLoginParam;
import com.newland.mes.system.service.AdminService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    AdminService adminService;


    @GetMapping("/info")
    public User getUserInfo(Principal principal){
        if(null==principal)
            return null;
        String username=principal.getName();
        User user=adminService.getUserByUserName(username);
        user.setPassword(null);
        return user;
    }
    @RequestMapping("/logout")
    public ResponseEntity<Object> logout(){
        return Result.success("注销成功!");
    }
}
