package com.newland.mes.system.controller;

import com.newland.mes.system.entity.Order;
import com.newland.mes.system.service.OrderService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @RequestMapping("/orderIndex")
    public String Index()
    {
        return "order/orderIndex.html";
    }

    @RequestMapping("/info")
    @PreAuthorize("@el.check('order:list')")
    public ResponseEntity<Object> OrderInfo(int page,int limit){
        List<Order> logCount=new ArrayList<>();
        Map<String,Object> out=new HashMap<>();
        out.put("code",0);
        out.put("msg","SUCC");
        out.put("count",0);
        out.put("data",logCount);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/iteminfo")
    @PreAuthorize("@el.check('order:list')")
    public ResponseEntity<Object> GetOrderItem(){
        return Result.success(orderService.getAllOrderItem());
    }
}


