package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.LogData;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.entity.PosType;
import com.newland.mes.system.entity.Station;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PosTypeMapper {

    @Select("select p.id,p.PosType,p.PNheader,p.SNheader from postype p " )
    List<PosType> getAllPosType();

    @Select("SELECT name FROM posstation")
    List<String> getAllStation();

    @Insert("INSERT INTO postype(PosType,PNheader,SNheader) value(#{PosType},#{PNheader},#{SNheader})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int addPosType(PosType posType);
    @Update("update postype set PosType=#{PosType},PNheader=#{PNheader},SNheader=#{SNheader} where id=#{id}")
    int updatePosType(PosType posType);

    @Select("select * from postype where PosType=#{name}")
    List<PosType> getPosTypeByName(String name);

    @Delete("delete from postype where id in (${ids}) ")
    public void deletePosByIds(String ids);

}
