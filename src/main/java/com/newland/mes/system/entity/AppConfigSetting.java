package com.newland.mes.system.entity;

public class AppConfigSetting {
    private Integer id;
    private String appName;
    private String posname;
    private String stationId;
    private String stationName;
    private String checkInfo;
    private String checkInfoId;
    private String checkMethod;
    private String checkMethodId;
    private String checkvalue;
    private String datasource;

    public AppConfigSetting() {
    }

    public AppConfigSetting(String appName, String posname, String stationId, String checkInfoId) {
        this.appName = appName;
        this.posname = posname;
        this.stationId = stationId;
        this.checkInfoId = checkInfoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPosname() {
        return posname;
    }

    public void setPosname(String posname) {
        this.posname = posname;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getCheckInfoId() {
        return checkInfoId;
    }

    public void setCheckInfoId(String checkInfoId) {
        this.checkInfoId = checkInfoId;
    }

    public String getCheckMethod() {
        return checkMethod;
    }

    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod;
    }

    public String getCheckMethodId() {
        return checkMethodId;
    }

    public void setCheckMethodId(String checkMethodId) {
        this.checkMethodId = checkMethodId;
    }

    public String getCheckvalue() {
        return checkvalue;
    }

    public void setCheckvalue(String checkvalue) {
        this.checkvalue = checkvalue;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    @Override
    public String toString() {
        return "AppConfigSetting{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", posname='" + posname + '\'' +
                ", stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", checkInfo='" + checkInfo + '\'' +
                ", checkInfoId='" + checkInfoId + '\'' +
                ", checkMethod='" + checkMethod + '\'' +
                ", checkMethodId='" + checkMethodId + '\'' +
                ", checkvalue='" + checkvalue + '\'' +
                ", datasource='" + datasource + '\'' +
                '}';
    }
}
