package com.newland.mes.system.controller;

import com.newland.mes.system.entity.Factory;
import com.newland.mes.system.service.FactoryService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    FactoryService factoryService;
    @RequestMapping("/info")
    public String index(){
        return "factory/PosInfoIndex.html";
    }

    @RequestMapping("/factoryname")
    public ResponseEntity<Object> getAllFactoryName(){
        List<Factory> allFactoryName = factoryService.getAllFactoryName();
        return Result.success(allFactoryName);
    }
}
