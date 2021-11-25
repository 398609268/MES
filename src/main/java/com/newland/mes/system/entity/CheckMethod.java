package com.newland.mes.system.entity;

import java.util.List;
import java.util.Map;


public class CheckMethod {
    private Map<String,Integer> method;
    private Map<String,String> checkInfo;
    private Map<String, List<String>> station;
    private Order order;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Map<String, Integer> getMethod() {
        return method;
    }

    public void setMethod(Map<String, Integer> method) {
        this.method = method;
    }

    public Map<String, String> getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(Map<String, String> checkInfo) {
        this.checkInfo = checkInfo;
    }

    @Override
    public String toString() {
        return "CheckMethod{" +
                "method=" + method +
                ", checkInfo=" + checkInfo +
                ", order=" + order +
                '}';
    }
}
