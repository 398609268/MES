package com.newland.mes.system.entity;

public class PosType {
    private Integer id;
    private String PosType;
    private String PNheader;
    private String SNheader;
    private Integer PNLen;
    private Integer SNLen;
    private String station;
    private Integer stationId;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosType() {
        return PosType;
    }

    public void setPosType(String posType) {
        PosType = posType;
    }

    public String getPNheader() {
        return PNheader;
    }

    public void setPNheader(String PNheader) {
        this.PNheader = PNheader;
    }

    public String getSNheader() {
        return SNheader;
    }

    public void setSNheader(String SNheader) {
        this.SNheader = SNheader;
    }

    public Integer getPNLen() {
        return PNLen;
    }

    public void setPNLen(Integer PNLen) {
        this.PNLen = PNLen;
    }

    public Integer getSNLen() {
        return SNLen;
    }

    public void setSNLen(Integer SNLen) {
        this.SNLen = SNLen;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "PosType{" +
                "id=" + id +
                ", PosType='" + PosType + '\'' +
                ", PNheader='" + PNheader + '\'' +
                ", SNheader='" + SNheader + '\'' +
                ", PNLen=" + PNLen +
                ", SNLen=" + SNLen +
                ", station='" + station + '\'' +
                ", stationId=" + stationId +
                '}';
    }
}
