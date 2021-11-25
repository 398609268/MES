package com.newland.mes.system.impl;

import com.newland.mes.system.entity.DataType;
import com.newland.mes.system.entity.Station;
import com.newland.mes.system.entity.StationName;
import com.newland.mes.system.entity.dto.TreeTypeDto;
import com.newland.mes.system.mapper.DataTypeMapper;
import com.newland.mes.system.mapper.StationMapper;
import com.newland.mes.system.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    StationMapper stationMapper;
    @Autowired
    DataTypeMapper dataTypeMapper;


    @Override
    public List<TreeTypeDto> getNameList() {

        List<StationName> list=stationMapper.getAllStationName();
        List<TreeTypeDto> result=new ArrayList<>();
        for (StationName stationName: list) {
            result.add(new TreeTypeDto(stationName.getId(),stationName.getName(),1000,false));
        }

        result.add(new TreeTypeDto(1000,"请选择站位",0,true,true));
        return result;
    }

    @Override
    public List<TreeTypeDto> getDataTypeList() {
        List<DataType> list = dataTypeMapper.getAll();
        List<TreeTypeDto> result=new ArrayList<>();
        for (DataType dataType: list) {
            result.add(new TreeTypeDto(dataType.getId(),dataType.getName(),1000,false));
        }

        result.add(new TreeTypeDto(1000,"请选择要卡控的数据",0,true,true));
        return result;
    }

    @Override
    public int InsetStationInfo(Map map) {
        Station station=new Station(map.get("stationid").toString(),map.get("stationname").toString(),map.get("PosType").toString(),map.get("DataType").toString(),map.get("DataTypeId").toString(),map.get("checkMethod").toString());
        return stationMapper.InsertStation(station);
    }

    @Override
    public int UpdateStationInfo(Map map) {
        Station station = new Station(map.get("stationid").toString(), map.get("stationname").toString(), map.get("PosType").toString(), map.get("DataType").toString(), map.get("DataTypeId").toString(), map.get("checkMethod").toString());
        stationMapper.updateStation(station);
        return 1;
    }
}
