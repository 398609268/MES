package com.newland.mes.system.entity;

public class OrderItem {
    private Integer id;
    private String name;
    private Short optional;
    private String description;

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

    public Short getOptional() {
        return optional;
    }

    public void setOptional(Short optional) {
        this.optional = optional;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", optional=" + optional +
                ", description='" + description + '\'' +
                '}';
    }
}
