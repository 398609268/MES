package com.newland.mes.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTLoginController {
    @RequestMapping("/dashboard")
    public String Loginin()
    {
        return "dashboard.html";
    }
    @GetMapping("/Mylogin")
    public String index() throws InterruptedException {
        return "MyLogin.html";
    }
}
