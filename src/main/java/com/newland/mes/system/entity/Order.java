package com.newland.mes.system.entity;

public class Order {
    private Integer id;
    private String orderId;
    private String OSVersion;
    private String PosType;
    private String ProductId;
    private String appVersion;
    private String PN;
    private String SN;
    private String StationInfo;
    private String check;
    private String checkmethod;
    private Integer status;
    private String factory;
    private String createTime;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getPN() {
        return PN;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public String getStationInfo() {
        return StationInfo;
    }

    public void setStationInfo(String stationInfo) {
        StationInfo = stationInfo;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCheckmethod() {
        return checkmethod;
    }

    public void setCheckmethod(String checkmethod) {
        this.checkmethod = checkmethod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }


    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String OSVersion) {
        this.OSVersion = OSVersion;
    }

    public String getPosType() {
        return PosType;
    }

    public void setPosType(String posType) {
        PosType = posType;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", OSVersion='" + OSVersion + '\'' +
                ", PosType='" + PosType + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", PN='" + PN + '\'' +
                ", SN='" + SN + '\'' +
                ", StationInfo='" + StationInfo + '\'' +
                ", check='" + check + '\'' +
                ", checkmethod='" + checkmethod + '\'' +
                ", status=" + status +
                ", factory='" + factory + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
