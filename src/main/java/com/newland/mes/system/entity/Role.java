package com.newland.mes.system.entity;

import java.util.Objects;

public class Role {
    private Integer id;
    private String roleName;
    private String menuIds;
    private String operationIds;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public String getOperationIds() {
        return operationIds;
    }

    public void setOperationIds(String operationIds) {
        this.operationIds = operationIds;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", menuIds='" + menuIds + '\'' +
                ", operationIds='" + operationIds + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(menuIds, role.menuIds) &&
                Objects.equals(operationIds, role.operationIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, menuIds, operationIds);
    }
}
