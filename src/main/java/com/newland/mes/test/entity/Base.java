package com.newland.mes.test.entity;

public class Base {
    private Integer status;
    private String orderId;
    private Integer station;

    public Base() {
    }

    public Base(Integer status, String orderId, Integer station) {
        this.status = status;
        this.orderId = orderId;
        this.station = station;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Base{" +
                "status=" + status +
                ", orderId='" + orderId + '\'' +
                ", station=" + station +
                '}';
    }
}
