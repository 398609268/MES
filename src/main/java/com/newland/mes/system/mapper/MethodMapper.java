package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.MethodItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MethodMapper {

    @Select("select * from checkmethod")
    public List<MethodItem> getAllMethod();
}
