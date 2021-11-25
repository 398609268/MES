package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationMapper {
    @Select("select * from operation where menuId=#{menuId}")
    public List<Integer> getIdByMenuId(String menuId);
}
