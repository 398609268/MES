package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {

    @Select("SELECT r.* FROM role r,USER u WHERE r.id=u.`roleId` AND u.id=#{id}")
    Set<Role> findByUserId(Integer id);

    @Select("SELECT operationIds FROM role WHERE id=#{id}")
    String getOperationIds(Integer id);
}
