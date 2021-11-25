package com.newland.mes.system.util;

import com.newland.mes.system.entity.DataType;
import com.newland.mes.system.entity.MethodItem;
import com.newland.mes.system.entity.StationName;
import com.newland.mes.system.mapper.DataTypeMapper;
import com.newland.mes.system.mapper.MethodMapper;
import com.newland.mes.system.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class BeanFactory {

    @Autowired
    StationMapper stationMapper;
    @Autowired
    DataTypeMapper dataTypeMapper;
    @Autowired
    MethodMapper methodMapper;
    @Bean
    public Map<Integer,String> stationIdToName(){
        Map<Integer,String> stationList=new HashMap<>();
        List<StationName> allStation = stationMapper.getAllStationName();
        for (StationName s:allStation){
            stationList.put(s.getId(),s.getName());
        }
        return stationList;
    }
    @Bean
    public Map<Integer,String> dataTypeList(){
        Map<Integer,String> dataTypeList=new HashMap<>();
        List<DataType> all = dataTypeMapper.getAll();
        for (DataType s:all){
            dataTypeList.put(s.getId(),s.getName());
        }
        return dataTypeList;
    }
    @Bean
    public Map<Integer,String> methodList(){
        Map<Integer,String> MethodItemList=new HashMap<>();
        List<MethodItem> allMethod = methodMapper.getAllMethod();
        for (MethodItem s:allMethod){
            MethodItemList.put(s.getId(),s.getName());
        }
        return MethodItemList;
    }

}
