package com.newland.mes.system.controller;

import com.newland.mes.system.entity.Menu;
import com.newland.mes.system.service.MenuService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sys")
public class MenuController {
    @Autowired
    MenuService menuService;
    @RequestMapping("/menu")
    ResponseEntity<Object> GetMenuList()
    {
        List<Menu> list= menuService.buildTrees();
        return Result.success(list);
    }
}
