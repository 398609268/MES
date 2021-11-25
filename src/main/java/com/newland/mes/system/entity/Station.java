package com.newland.mes.system.entity;

public class Station {
    private Integer id;
    private String stationid;
    private String name;
    private String postype;
    private String checkInfo;
    private String checkInfoId;
    private String checkmethods;

    public Station(String stationid, String name, String postype, String checkInfo, String checkInfoId, String checkmethods) {
        this.stationid = stationid;
        this.name = name;
        this.postype = postype;
        this.checkInfo = checkInfo;
        this.checkInfoId = checkInfoId;
        this.checkmethods = checkmethods;
    }
    public Station(String stationid, String name, String checkInfo, String checkInfoId) {
        this.stationid = stationid;
        this.name = name;
        this.checkInfo = checkInfo;
        this.checkInfoId = checkInfoId;
    }
    public Station() {
    }

    public String getCheckInfoId() {
        return checkInfoId;
    }

    public void setCheckInfoId(String checkInfoId) {
        this.checkInfoId = checkInfoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostype() {
        return postype;
    }

    public void setPostype(String postype) {
        this.postype = postype;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getCheckmethods() {
        return checkmethods;
    }

    public void setCheckmethods(String checkmethods) {
        this.checkmethods = checkmethods;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", stationid='" + stationid + '\'' +
                ", name='" + name + '\'' +
                ", postype='" + postype + '\'' +
                ", checkInfo='" + checkInfo + '\'' +
                ", checkInfoId='" + checkInfoId + '\'' +
                ", checkmethods='" + checkmethods + '\'' +
                '}';
    }
}
