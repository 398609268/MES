package com.newland.mes.system.impl;

import com.newland.mes.system.entity.CheckItemShow;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.mapper.MethodMapper;
import com.newland.mes.system.mapper.PinHaoMapper;
import com.newland.mes.system.mapper.StationMapper;
import com.newland.mes.system.service.PinHaoService;
import com.newland.mes.system.util.CheckInfoUtil;
import com.newland.mes.system.util.PinHaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PinHaoServiceImpl implements PinHaoService {
    @Autowired
    PinHaoMapper pinHaoMapper;
    @Autowired
    MethodMapper methodMapper;
    @Autowired
    StationMapper stationMapper;
    @Autowired
    Map<Integer,String> stationIdToName;
    @Autowired
    Map<Integer,String> dataTypeList;
    @Autowired
    Map<Integer,String> methodList;
    @Override
    public boolean addPosType(String pinhao, String posname,List<PosCheckInfo> list) {
        int ret=0;
        PinHaoSetting p = pinHaoMapper.getPinhaoSettingByPinhao(pinhao);
        Map<String,String> map= CheckInfoUtil.dealPosCheckInfo(list);
        PinHaoSetting pinHaoSetting=new PinHaoSetting(pinhao,posname,map.get("StationId"),map.get("checkInfoId"));
        if(p==null)
        {
           ret=pinHaoMapper.addPinhaoSetting(pinHaoSetting);
        }else{
            ret=0;
        }
        return ret==1;

    }
    @Override
    public boolean updatePosType(String pinhao, String posname,List<PosCheckInfo> list){
        int ret=0;
        PinHaoSetting p = pinHaoMapper.getPinhaoSettingByPinhao(pinhao);
        Map<String,String> map= PinHaoUtil.setothersByChange(list,p);
        PinHaoSetting pinHaoSetting=new PinHaoSetting(pinhao,posname,map.get("stationId"),map.get("checkInfoId"));
        pinHaoSetting.setCheckMethodId(map.get("checkMethodId"));
        pinHaoSetting.setCheckvalue(map.get("checkvalue"));
        pinHaoSetting.setDatasource(map.get("datasource"));
        if(p==null)
        {
            ret=0;
        }else{
            pinHaoSetting.setId(p.getId());
            ret=pinHaoMapper.updatePinhaoSetting(pinHaoSetting);
        }
        return ret==1;
    }
    /**
     * 获取数据库中的信息
     * @param pinhao
     * @return
     */
    public PinHaoSetting GetPinHaoCheckItem(String pinhao){
        PinHaoSetting p = pinHaoMapper.getPinhaoSettingByPinhao(pinhao);
        return p;
    }

    /**
     * 用于设置号段时显示的站位名称和卡控项名称以及卡控方法列表
     * @param pinhao
     * @return
     */
    @Override
    public Map<String,Object> getCheckMethod(String pinhao){
        PinHaoSetting p=GetPinHaoCheckItem(pinhao);
        Map<String,Object> result=new HashMap<>();
        if(p==null) return result;
        List<String> stationIdList= Arrays.asList(p.getStationId().split("/"));
        List<String> stationName = PinHaoUtil.getNameByidsWithMap(stationIdToName, stationIdList);
        List<String> checkInfo=Arrays.asList(p.getCheckInfoId().split("/"));
        List<List<String>> checkInfoByids = PinHaoUtil.getNameByidsWithMapFormultiple(dataTypeList, checkInfo);
        result.put("stationId",stationName);
        result.put("checkInfo",checkInfoByids);
        result.put("method",methodMapper.getAllMethod());
        return result;
    }

    @Override
    public boolean addMethodAndValue(String pinhao, String method, String value,String datasource) {
        PinHaoSetting p=GetPinHaoCheckItem(pinhao);
        if(p==null)
        return false;
        int i = pinHaoMapper.updateMethodAndValue(p.getId(), method, value,datasource);
        if(i==1) return true;
        else return false;
    }

    @Override
    public List<PinHaoSetting> getAllPinhaoInfo(int page,int limit) {
        return pinHaoMapper.getAllPinHao((page-1)*limit,page*limit);
    }

    @Override
    public List<CheckItemShow> getPinHaoItem(String pinhao) {
        Map<String, Object> map=new HashMap<>();
        List<CheckItemShow> show=new ArrayList<>();
        PinHaoSetting p=GetPinHaoCheckItem(pinhao);
        if(p==null) return new ArrayList<>();
        //获取站位名称以及卡控项名称
        map=getCheckMethod(pinhao);
        //获取卡控方法对应的名称
        List<List<String>> methodName=new ArrayList<>();
        if(p.getCheckMethodId()!=null)
        methodName =PinHaoUtil.getNameByidsWithMapFormultiple(methodList,
                Arrays.asList(p.getCheckMethodId().split(PinHaoUtil.methodSplit)));
        List<List<String>> checkValueList = PinHaoUtil.dealCheckValue(p.getCheckvalue());
        List<List<String>> checkId=PinHaoUtil.getIds(p.getCheckInfoId(),PinHaoUtil.checkInfoSplit,PinHaoUtil.checkInfoitemSplit);
        List<List<String>> datasource=PinHaoUtil.getIds(p.getDatasource(),PinHaoUtil.datasourceSplit,PinHaoUtil.datasourceitemSplit);
        List<String> StationName= (List<String>) map.get("stationId");
        List<String> StationId= Arrays.asList(p.getStationId().split(PinHaoUtil.stationSplit));
        List<List<String>> checkInfoByids= (List<List<String>>) map.get("checkInfo");
        if(StationName.size()>0){
            for(int i=0;i<StationName.size();i++){
                CheckItemShow checkItemShow=new CheckItemShow();
                checkItemShow.setStationName(StationName.get(i));
                checkItemShow.setId(StationId.get(i));
                List<PinHaoSetting> list=new ArrayList<>();
                //设置站位的卡控内容
                if(checkInfoByids.size()>i){
                    int stationCheckItemLen=checkInfoByids.get(i).size();
                    for(int j=0;j<stationCheckItemLen;j++){
                        PinHaoSetting pinHaoSetting=new PinHaoSetting();
                        pinHaoSetting.setCheckInfo(checkInfoByids.get(i).get(j));
                        if(checkValueList.size()>i&& checkValueList.get(i).size()>j){
                            pinHaoSetting.setCheckvalue(checkValueList.get(i).get(j));
                        }
                        if(methodName.size()>i&& methodName.get(i).size()>j){
                            pinHaoSetting.setCheckMethod(methodName.get(i).get(j));
                        }
                        if(checkId.size()>i && checkId.get(i).size()>j){
                            pinHaoSetting.setId(Integer.parseInt(checkId.get(i).get(j)));
                        }
                        if(datasource.size()>i && datasource.get(i).size()>j)
                            pinHaoSetting.setDatasource(datasource.get(i).get(j));
                        list.add(pinHaoSetting);
                    }
                }
                checkItemShow.setSettings(list);
                show.add(checkItemShow);
            }
        }
        return show;
    }

    @Override
    public int deletePinhao(List<String> ids) {
        StringBuilder idList=new StringBuilder();
        for(int i=0;i<ids.size();i++){
            idList.append(ids.get(i));
            if(i<ids.size()-1)
                idList.append(",");
        }
        return pinHaoMapper.deletePinHaoByIds(idList.toString());

    }
}
