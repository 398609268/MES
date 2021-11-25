package com.newland.mes.system.service;

import com.newland.mes.system.entity.AppConfigSetting;
import com.newland.mes.system.entity.CheckItemShow;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;

import java.util.List;
import java.util.Map;

public interface AppConfigService {
    public boolean addPosType(String pinhao, String posname , List<PosCheckInfo> list);
    public boolean updatePosType(String pinhao, String posname,List<PosCheckInfo> list);
    public Map<String,Object> getCheckMethod(String pinhao);
    public boolean addMethodAndValue(String pinhao,String method,String value,String datasource);
    public List<AppConfigSetting> getAllAppConfigInfo(int page, int limit);
    public List<CheckItemShow> getItem(String pinhao);
    public int delete(List<String> ids);
}
