package com.newland.mes.system.controller;

import com.newland.mes.system.service.UserService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/userIndex")
    public String Index(){
        return "user/UserIndex.html";
    }

    @GetMapping("/info")
    public ResponseEntity<Object> GetAllUser(int page,int limit){
        System.out.println(page+" "+limit);
        int start=(page-1)*limit;
       return Result.success(userService.getAllUser(start,start+limit-1));
    }

}
