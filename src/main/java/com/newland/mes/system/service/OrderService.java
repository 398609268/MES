package com.newland.mes.system.service;

import com.newland.mes.system.entity.CheckMethod;
import com.newland.mes.system.entity.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public void resloverCheck(String orderId);
    public Map<String,CheckMethod> getCheckMethod();
    public List<OrderItem> getAllOrderItem();
}
