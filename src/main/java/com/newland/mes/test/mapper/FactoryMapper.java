package com.newland.mes.test.mapper;

import com.newland.mes.test.entity.BHdata;
import com.newland.mes.test.entity.PNdata;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.servlet.http.PushBuilder;

@Mapper()
@Repository("FactoryMapperTest")
public interface FactoryMapper {

    @Insert("INSERT INTO factoryinfo(banhao,tableId,station,status) value(#{banhao},#{orderId},#{station},#{status})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int InsertBH(BHdata bHdata);

    @Update("update factoryinfo set PN=#{PN},MN=#{MN},station=#{station} where banhao=#{Banhao}")
    public int updatePn(String PN,String MN,String Banhao,int station);

    @Update("update factoryinfo set SN=#{SN},station=#{station} where MN=#{MN}")
    public int updateSn(String SN,String MN,int station);

    @Select("select station from factoryinfo where MN=#{MN}")
    public int getStationByMN(String MN );

    @Select("select station from factoryinfo where banhao=#{banhao}")
    public int getStationBybanhao(String banhao );

    @Select("select * from factoryinfo where PN=#{pn}")
    public int test(String pn);
}
