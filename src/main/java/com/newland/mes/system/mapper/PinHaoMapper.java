package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.Station;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PinHaoMapper {
    @Select("select * from pinhao where 1=1 and pinhao = #{pinhao}")
    public PinHaoSetting getPinhaoSettingByPinhao(@Param("pinhao")String pinhao );

    @Update("insert into pinhao(pinhao,posname,stationId,checkInfoId,checkMethodId,checkvalue,datasource) " +
            "value(#{pinhao},#{posname},#{stationId},#{checkInfoId},#{checkMethodId},#{checkvalue},#{datasource})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int addPinhaoSetting(PinHaoSetting pinHaoSetting);

    @Update("update pinhao set pinhao=#{pinhao},stationId=#{stationId},datasource=#{datasource}," +
            "posname=#{posname} ,checkInfoId=#{checkInfoId},checkMethodId=#{checkMethodId},checkvalue=#{checkvalue} " +
            "where id=#{id}")
    public int updatePinhaoSetting(PinHaoSetting pinHaoSetting);

    @Update("update pinhao set checkMethodId=#{checkMethodId},checkvalue=#{checkvalue},datasource=#{datasource}" +
            "where id=#{id}")
    public int updateMethodAndValue(Integer id,String checkMethodId,String checkvalue,String datasource);

    @Select("select * from pinhao limit #{start},#{end}")
    public List<PinHaoSetting> getAllPinHao(int start,int end);
    @Delete("delete from pinhao where id in (${ids}) ")
    public int deletePinHaoByIds(String ids);
}
