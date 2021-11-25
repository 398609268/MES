package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.Station;
import com.newland.mes.system.entity.StationName;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StationMapper {

    @Select("select * from stationinfo")
    public List<Station> getAllStation();

    @Select({"<script>","select * from stationinfo","where 1=1",
            "<if test='postype!=null and postype!=\"\"'>","and postype = #{postype}","</if>",
            "<if test='name!=null and name != \"\"'>","and name = #{name}","</if>",
            "</script>"})
    public Station getAllStationByPosTypeAndName(@Param("postype")String postype ,@Param("name") String name);

    @Insert("insert into stationinfo(name,stationId,postype) value(#{name},#{stationid},#{postype})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int InsertStation(Station station);

    @Select("select * from stationname")
    public List<StationName> getAllStationName();

    @Select("select count(1) from stationinfo where name=#{stationname} AND postype=#{PosType}")
    public int checkStationRepeat(String stationname,String PosType);

    @Select("update stationinfo set stationId=#{stationid},name=#{name} where postype=#{postype}")
    public void updateStation(Station station);

    @Delete("delete from stationinfo where postype in (${ids})")
    public void deleteStationByposId(String ids);
}
