package com.newland.mes.system.entity;

public class DataType {
    private Integer id;
    private String name;
    private String checkItem;
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

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    @Override
    public String toString() {
        return "DataType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", checkItem='" + checkItem + '\'' +
                '}';
    }
}
