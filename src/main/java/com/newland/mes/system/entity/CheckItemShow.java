package com.newland.mes.system.entity;

import java.util.List;

public class CheckItemShow {
    private String id;
    private String StationName;
    private List<PinHaoSetting> settings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return StationName;
    }

    public void setStationName(String stationName) {
        StationName = stationName;
    }

    public List<PinHaoSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<PinHaoSetting> settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "CheckItemShow{" +
                "StationName='" + StationName + '\'' +
                ", settings=" + settings +
                '}';
    }
}
