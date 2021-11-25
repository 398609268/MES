package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where name=#{username} and enabled=1")
    @Results({
            @Result(property = "username",column = "name"),

    })
    public User getUserByUsername(String username);

    @Select("select u.id,r.roleName,u.name,u.password from user u " +
            "join role r on u.roleId=r.id limit #{start},#{end}")
    @Results({
            @Result(property = "username",column = "name"),

    })
    public List<User> getAllUser(int start,int end);
}
