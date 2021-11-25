package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.AppConfigSetting;
import com.newland.mes.system.entity.PinHaoSetting;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppConfigMapper {
    @Select("select * from appconfig where 1=1 and appName = #{appName}")
    public AppConfigSetting getAppConfigSettingByName(@Param("appName")String appName );

    @Update("insert into appconfig(appName,posname,stationId,checkInfoId,checkMethodId,checkvalue,datasource) " +
            "value(#{appName},#{posname},#{stationId},#{checkInfoId},#{checkMethodId},#{checkvalue},#{datasource})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int addAppConfigSetting(AppConfigSetting appConfigSetting);

    @Update("update appconfig set appName=#{appName},stationId=#{stationId},datasource=#{datasource}," +
            "posname=#{posname} ,checkInfoId=#{checkInfoId},checkMethodId=#{checkMethodId},checkvalue=#{checkvalue} " +
            "where id=#{id}")
    public int updateAppConfigSetting(AppConfigSetting AppConfigSetting);

    @Update("update appconfig set checkMethodId=#{checkMethodId},checkvalue=#{checkvalue},datasource=#{datasource}" +
            "where id=#{id}")
    public int updateMethodAndValue(Integer id,String checkMethodId,String checkvalue,String datasource);

    @Select("select * from appconfig limit #{start},#{end}")
    public List<AppConfigSetting> getAllAppConfig(int start, int end);
    @Delete("delete from appconfig where id in (${ids}) ")
    public int deleteAppConfigByIds(String ids);
}
