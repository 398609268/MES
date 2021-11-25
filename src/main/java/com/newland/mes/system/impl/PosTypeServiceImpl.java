package com.newland.mes.system.impl;

import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.entity.PosType;
import com.newland.mes.system.entity.Station;
import com.newland.mes.system.entity.dto.TreeTypeDto;
import com.newland.mes.system.mapper.PosTypeMapper;
import com.newland.mes.system.mapper.StationMapper;
import com.newland.mes.system.service.PosTypeService;
import com.newland.mes.system.util.CheckInfoUtil;
import com.newland.mes.system.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PosTypeServiceImpl implements PosTypeService {

    @Autowired
    PosTypeMapper posTypeMapper;
    @Autowired
    StationMapper stationMapper;
    @Override
    /**
     * 获取Pos机型的树状数据
     */
    public List<TreeTypeDto> getAllPosTypeTree() {
        Map<String,String> map=new HashMap<>();
        List<PosType> list=posTypeMapper.getAllPosType();

        List<TreeTypeDto> result=new ArrayList<>();
        for (PosType posType: list) {
            if(map.get(posType.getStation())==null){
                map.put(posType.getStation(),"exit");
                result.add(new TreeTypeDto(posType.getStationId(),posType.getStation(),0,true));
            }
            result.add(new TreeTypeDto(posType.getId(),posType.getPosType(),posType.getStationId(),false));

        }

        return result;
    }
    public List<PosType> getAllPosInfo(){
        return posTypeMapper.getAllPosType();
    }
    Station integrateStationData(List<PosCheckInfo> list){
        StringBuilder StationId= new StringBuilder(),checkInfoId=new StringBuilder();
        StringBuilder StationName= new StringBuilder(),checkInfo=new StringBuilder();

        Map<String,String> map= CheckInfoUtil.dealPosCheckInfo(list);
        return new Station(map.get("StationId"),map.get("StationName"),map.get("checkInfo"),map.get("checkInfoId"));
    }
    @Override
    public boolean addPosType(PosType posType, List<PosCheckInfo> list) {
        List<PosType> posTypeByName = posTypeMapper.getPosTypeByName(posType.getPosType());
        if(posTypeByName!=null && posTypeByName.size()!=0) return false;
        if(posTypeMapper.addPosType(posType)!=1)
            return false;
         posTypeByName = posTypeMapper.getPosTypeByName(posType.getPosType());
         Integer PosName=posTypeByName.get(0).getId();
        if(list==null) return true;
        else
        {
            Station station = integrateStationData(list);
            station.setPostype(PosName.toString());
            stationMapper.InsertStation(station);
            return true;
        }

    }

    @Override
    public boolean UpdatePosType(PosType posType, List<PosCheckInfo> list) {
        if(posType.getId()==null) return false;
        posTypeMapper.updatePosType(posType);
        Integer PosName=posType.getId();

            Station station = integrateStationData(list);
            station.setPostype(PosName.toString());
            stationMapper.updateStation(station);
            return true;

    }

    @Override
    public List<PosCheckInfo> getCheckInfoByStationInfo(String posname) {
        Station allStationByPosTypeAndName = stationMapper.getAllStationByPosTypeAndName(posname, null);
        List<PosCheckInfo> list=new ArrayList<>();
        if(allStationByPosTypeAndName!=null) {
            String[] split = allStationByPosTypeAndName.getName().split("/");
            String[] splitid = allStationByPosTypeAndName.getStationid().split("/");
            for(int i=0;i<split.length;i++){
                PosCheckInfo posCheckInfo=new PosCheckInfo();
                posCheckInfo.setName(split[i]);
                posCheckInfo.setParent(true);
                posCheckInfo.setId(Integer.parseInt(splitid[i]));
                list.add(posCheckInfo);
            }
        }
        return list;
    }

    @Override
    public void deletePos(List<String> ids) {
        StringBuilder idList=new StringBuilder();
        for(int i=0;i<ids.size();i++){
            idList.append(ids.get(i));
            if(i<ids.size()-1)
                idList.append(",");
        }
        System.out.println(idList);
        posTypeMapper.deletePosByIds(idList.toString());
        stationMapper.deleteStationByposId(idList.toString());
    }


}
