package com.newland.mes.system.impl;

import com.newland.mes.system.entity.CheckMethod;
import com.newland.mes.system.entity.Order;
import com.newland.mes.system.entity.OrderItem;
import com.newland.mes.system.mapper.OrderMapper;
import com.newland.mes.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    Map<String,CheckMethod> checkMethodMap=new HashMap<>();

     @Override
    public Map<String,CheckMethod> getCheckMethod() {
        return checkMethodMap;
    }

    @Override
    public List<OrderItem> getAllOrderItem() {
        return orderMapper.getAllOrderItems();
    }

    @Override
    public void resloverCheck(String orderId) {
        Order order=orderMapper.getOrderByOderId(orderId);
        if(order==null) return;
        Map<String,Integer> checkmethodmap=new HashMap<>();
        Map<String,String> checkMap=new HashMap<>();
        CheckMethod checkMethod=new CheckMethod();
        List<String> checkmethod= Arrays.asList(order.getCheckmethod().split(";"));
        if(checkmethod.size()>0) {
            for (String s : checkmethod) {
                String[] sin=s.split(":");
                checkmethodmap.put(sin[0],Integer.parseInt(sin[1]));
            }
            checkMethod.setMethod(checkmethodmap);
            List<String> checkmethodInfo= Arrays.asList(order.getCheck().split(";"));
            if(checkmethodInfo.size()>0){
                for(String s:checkmethodInfo){
                    String[] check=s.split(":");
                    checkMap.put(check[0],check[1]);
                }
            }
            if(order.getPN().length()>0)
                checkMap.put("PN",order.getPN());
            if(order.getSN().length()>0)
                checkMap.put("SN",order.getSN());
            checkMethod.setCheckInfo(checkMap);
            checkMethod.setOrder(order);
            checkMethodMap.put("ZS13-21060349",checkMethod);
        }
    }
}
