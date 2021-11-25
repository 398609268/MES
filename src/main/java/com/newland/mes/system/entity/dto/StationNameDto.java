package com.newland.mes.system.entity.dto;

import com.newland.mes.system.entity.StationName;

import java.util.List;

public class StationNameDto {
    private Integer id;
    private String Title;
    private List<StationNameDto> children;

    public StationNameDto() {
    }

    public StationNameDto(Integer id, String title, List<StationNameDto> children) {
        this.id = id;
        Title = title;
        this.children = children;
    }

    @Override
    public String toString() {
        return "StationNameDto{" +
                "id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<StationNameDto> getChildren() {
        return children;
    }

    public void setChildren(List<StationNameDto> children) {
        this.children = children;
    }

}
