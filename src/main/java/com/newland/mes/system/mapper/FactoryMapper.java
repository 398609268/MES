package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.Factory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FactoryMapper {

    @Select("select * from factory")
    List<Factory> getAllFactoryName();
}
