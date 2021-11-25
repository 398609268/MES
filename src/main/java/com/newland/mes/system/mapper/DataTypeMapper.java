package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.DataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataTypeMapper {

    @Select("select * from datatype")
    public List<DataType> getAll();
}
