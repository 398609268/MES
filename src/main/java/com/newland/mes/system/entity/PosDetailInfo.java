package com.newland.mes.system.entity;

public class PosDetailInfo {
    private PosType PosData;
    private PosCheckInfo CheckData;

    public PosType getPosData() {
        return PosData;
    }

    public void setPosData(PosType posData) {
        PosData = posData;
    }

    public PosCheckInfo getCheckData() {
        return CheckData;
    }

    public void setCheckData(PosCheckInfo checkData) {
        CheckData = checkData;
    }

    @Override
    public String toString() {
        return "PosDetailInfo{" +
                "PosData=" + PosData +
                ", CheckData=" + CheckData +
                '}';
    }
}
