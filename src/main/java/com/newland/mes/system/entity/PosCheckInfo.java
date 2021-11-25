package com.newland.mes.system.entity;

import java.util.List;

public class PosCheckInfo {
    private Integer id;
    private String name;
    private Boolean parent;
    private Integer pid;
    private List<PosCheckInfo> children;

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

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<PosCheckInfo> getChildren() {
        return children;
    }

    public void setChildren(List<PosCheckInfo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PosCheckInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", pid=" + pid +
                ", children=" + children +
                '}';
    }
}
