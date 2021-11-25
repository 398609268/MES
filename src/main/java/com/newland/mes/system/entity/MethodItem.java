package com.newland.mes.system.entity;

public class MethodItem {
    private Integer id;
    private String name;
    private Integer style;
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

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MethodItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", style=" + style +
                ", description='" + description + '\'' +
                '}';
    }
}
