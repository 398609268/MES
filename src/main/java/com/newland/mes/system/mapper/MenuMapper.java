package com.newland.mes.system.mapper;


import com.newland.mes.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from menu order by seq")
    List<Menu> findAll();

    @Select("SELECT * FROM menu WHERE id IN (${ids}) order by seq")
    List<Menu> getByMenuIds(String ids);

}
