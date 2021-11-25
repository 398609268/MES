package com.newland.mes.system.entity;

public class LogRecord {
    private String ip;
    private Integer num;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "ip='" + ip + '\'' +
                ", num=" + num +
                '}';
    }
}
