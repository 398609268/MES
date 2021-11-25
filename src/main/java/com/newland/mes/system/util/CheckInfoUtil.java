package com.newland.mes.system.util;

import com.alibaba.fastjson.JSON;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckInfoUtil {
    public static List<PosCheckInfo> dealData(Map map){
        List<Object> CheckData = (List<Object>) map.get("CheckData");
        List<PosCheckInfo> posCheckInfoList=new ArrayList<>();
        if(CheckData==null) return posCheckInfoList;
        for(int i=0;i<CheckData.size();i++){
            PosCheckInfo posCheckInfo= JSON.parseObject(JSON.toJSONString(CheckData.get(i)),PosCheckInfo.class);
            posCheckInfoList.add(posCheckInfo);
        }
        return posCheckInfoList;
    }
    public static Map<String,String> dealPosCheckInfo( List<PosCheckInfo> list){
        StringBuilder StationId= new StringBuilder(),checkInfoId=new StringBuilder();
        StringBuilder StationName= new StringBuilder(),checkInfo=new StringBuilder();
        Map<String,String> result=new HashMap<>();
        for(PosCheckInfo posCheckInfo:list){
            StationId.append(posCheckInfo.getId());
            StationId.append("/");
            StationName.append(posCheckInfo.getName());
            StationName.append("/");
            if(posCheckInfo.getChildren()==null){
                checkInfoId.append("0").append("/");
                checkInfo.append("NULL").append("/");
            }else {
                for (PosCheckInfo child : posCheckInfo.getChildren()) {
                    checkInfoId.append(child.getId()).append("-");
                    checkInfo.append(child.getName()).append("-");
                }
                checkInfoId.append("/");
                checkInfo.append("/");
            }
        }
        result.put("StationId",StationId.toString());
        result.put("checkInfoId",checkInfoId.toString());
        result.put("StationName",StationName.toString());
        result.put("checkInfo",checkInfo.toString());
        return result;
    }

}
