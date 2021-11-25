package com.newland.mes.system.entity;

public class PinHaoSetting {
    private Integer id;
    private String pinhao;
    private String posname;
    private String stationId;
    private String stationName;
    private String checkInfo;
    private String checkInfoId;
    private String checkMethod;
    private String checkMethodId;
    private String checkvalue;
    private String datasource;

    public PinHaoSetting() {
    }

    public PinHaoSetting(String pinhao,String posname, String stationId, String checkInfoId) {
        this.pinhao = pinhao;
        this.posname=posname;
        this.stationId = stationId;
        this.checkInfoId = checkInfoId;
    }

    public String getPosname() {
        return posname;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public void setPosname(String posname) {
        this.posname = posname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPinhao() {
        return pinhao;
    }

    public void setPinhao(String pinhao) {
        this.pinhao = pinhao;
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

    @Override
    public String toString() {
        return "PinHaoSetting{" +
                "id=" + id +
                ", pinhao='" + pinhao + '\'' +
                ", posname='" + posname + '\'' +
                ", stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", checkInfo='" + checkInfo + '\'' +
                ", checkInfoId='" + checkInfoId + '\'' +
                ", checkMethod='" + checkMethod + '\'' +
                ", checkMethodId='" + checkMethodId + '\'' +
                ", checkvalue='" + checkvalue + '\'' +
                '}';
    }
}
