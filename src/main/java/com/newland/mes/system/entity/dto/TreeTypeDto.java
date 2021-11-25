package com.newland.mes.system.entity.dto;

import com.newland.mes.system.entity.PosType;

import java.util.List;

public class TreeTypeDto {

    private Integer id;
    private String name;
    private Integer pid;
    private boolean parent;
    private boolean open;

    public TreeTypeDto() {
    }

    public TreeTypeDto(Integer id, String name, Integer pid, boolean parent) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.parent = parent;
    }

    public TreeTypeDto(Integer id, String name, Integer pid, boolean parent, boolean open) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.parent = parent;
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", parent=" + parent +
                '}';
    }
}
