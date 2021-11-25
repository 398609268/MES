package com.newland.mes.test.entity;

public class BHdata {
    private String id;
    private String banhao;
    private Integer status;
    private String orderId;
    private Integer station;

    public BHdata(){

    }
    public BHdata(String banhao, Integer status, String orderId, Integer station) {
        this.banhao = banhao;
        this.status = status;
        this.orderId = orderId;
        this.station = station;

        if (banhao == null || status == null || orderId ==null || station==null )

        {
            throw new IllegalArgumentException("Parameters can't be null");

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public String getBanhao() {
        return banhao;
    }

    public void setBanhao(String banhao) {
        this.banhao = banhao;
    }


    public Integer getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "BHdata{" +
                "banhao='" + banhao + '\'' +
                ", status=" + status +
                ", orderId='" + orderId + '\'' +
                ", station=" + station +
                '}';
    }
}
