package com.newland.mes.system.mapper;

import com.newland.mes.system.entity.LogData;
import com.newland.mes.system.entity.LogRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogMapper {

    @Insert("INSERT INTO log(data,method,ip,num,createTime) value(#{data},#{method},#{ip},#{num},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int addLogData(LogData logData);

    @Insert("INSERT INTO testlog(data,method,ip,num,createTime) value(#{data},#{method},#{ip},#{num},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int addLogDataToTestLof(LogData logData);

    @Select("select ip,count(*) as count from log group by ip")
    @Results({
            @Result(property = "ip",column = "ip"),
            @Result(property = "num",column = "count"),

    })
    public List<LogRecord> getLogCount();

    @Select("select * from log order by id desc limit 5 ")
    public List<LogData> getRecentLog();
}
