package com.newland.mes.system.impl;

import com.newland.mes.system.entity.AppConfigSetting;
import com.newland.mes.system.entity.CheckItemShow;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.mapper.AppConfigMapper;
import com.newland.mes.system.mapper.MethodMapper;
import com.newland.mes.system.mapper.PinHaoMapper;
import com.newland.mes.system.mapper.StationMapper;
import com.newland.mes.system.service.AppConfigService;
import com.newland.mes.system.util.CheckInfoUtil;
import com.newland.mes.system.util.PinHaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppConfigServiceImpl implements AppConfigService {
    @Autowired
    AppConfigMapper appConfigMapper;
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
    public boolean addPosType(String name, String posname, List<PosCheckInfo> list) {
        int ret=0;
        AppConfigSetting p = appConfigMapper.getAppConfigSettingByName(name);
        Map<String,String> map= CheckInfoUtil.dealPosCheckInfo(list);
        AppConfigSetting appConfigSetting=new AppConfigSetting(name,posname,map.get("StationId"),map.get("checkInfoId"));
        if(p==null)
        {
            ret=appConfigMapper.addAppConfigSetting(appConfigSetting);
        }else{
            ret=0;
        }
        return ret==1;
    }

    @Override
    public boolean updatePosType(String name, String posname, List<PosCheckInfo> list) {
        int ret=0;
        AppConfigSetting p = appConfigMapper.getAppConfigSettingByName(name);
        Map<String,String> map= PinHaoUtil.setothersByChange(list,p);
        AppConfigSetting appConfigSetting=new AppConfigSetting(name,posname,map.get("stationId"),map.get("checkInfoId"));
        appConfigSetting.setCheckMethodId(map.get("checkMethodId"));
        appConfigSetting.setCheckvalue(map.get("checkvalue"));
        appConfigSetting.setDatasource(map.get("datasource"));
        if(p==null)
        {
            ret=0;
        }else{
            appConfigSetting.setId(p.getId());
            ret=appConfigMapper.updateAppConfigSetting(appConfigSetting);
        }
        return ret==1;
    }
    /**
     * 获取数据库中的信息
     * @param name
     * @return
     */
    public AppConfigSetting GetPinHaoCheckItem(String name){
        AppConfigSetting p = appConfigMapper.getAppConfigSettingByName(name);
        return p;
    }

    @Override
    public Map<String, Object> getCheckMethod(String pinhao) {
        AppConfigSetting p=GetPinHaoCheckItem(pinhao);
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
    public boolean addMethodAndValue(String name, String method, String value, String datasource) {
        AppConfigSetting p=GetPinHaoCheckItem(name);
        if(p==null)
            return false;
        int i = appConfigMapper.updateMethodAndValue(p.getId(), method, value,datasource);
        if(i==1) return true;
        else return false;
    }

    @Override
    public List<AppConfigSetting> getAllAppConfigInfo(int page, int limit) {
        return appConfigMapper.getAllAppConfig((page-1)*limit,page*limit);
    }

    @Override
    public List<CheckItemShow> getItem(String pinhao) {
        Map<String, Object> map=new HashMap<>();
        List<CheckItemShow> show=new ArrayList<>();
        AppConfigSetting p=GetPinHaoCheckItem(pinhao);
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
    public int delete(List<String> ids) {
        StringBuilder idList=new StringBuilder();
        for(int i=0;i<ids.size();i++){
            idList.append(ids.get(i));
            if(i<ids.size()-1)
                idList.append(",");
        }
        return appConfigMapper.deleteAppConfigByIds(idList.toString());
    }
}
