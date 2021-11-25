package com.newland.mes.system.entity;

public class LogData {
    private Integer id;
    private String data;
    private String method;
    private String ip;
    private Integer num;
    private String createTime;
    @Override
    public String toString() {
        return "LogData{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", num=" + num +
                '}';
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

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
}
