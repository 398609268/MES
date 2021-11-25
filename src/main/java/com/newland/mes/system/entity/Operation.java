package com.newland.mes.system.entity;

public class Operation {
    private Integer operationId;
    private String operationName;
    private Integer type;
    private Integer menuId;
    private String permission;

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId=" + operationId +
                ", operationName='" + operationName + '\'' +
                ", type=" + type +
                ", menuId=" + menuId +
                ", permission='" + permission + '\'' +
                '}';
    }
}
