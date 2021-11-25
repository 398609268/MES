package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.CheckMethod;
import com.newland.mes.system.entity.Order;
import com.newland.mes.system.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from orders where orderId=#{orderId}")
    public Order getOrderByOderId(String orderId);

    @Select("select * from orderitem")
    public List<OrderItem> getAllOrderItems();
}
