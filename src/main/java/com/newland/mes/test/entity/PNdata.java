package com.newland.mes.test.entity;

public class PNdata extends Base {
    private Integer id;
    private String PN;

    public PNdata() {
    }

    public PNdata(String PN,Integer status, String orderId, Integer station) {
        super(status, orderId, station);
        this.PN = PN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    @Override
    public String toString() {
        return "PNdata{" +
                "id=" + id +
                ", PN='" + PN + '\'' +
                "} " + super.toString();
    }
}
