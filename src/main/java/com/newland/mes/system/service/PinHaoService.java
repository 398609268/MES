package com.newland.mes.system.service;

import com.newland.mes.system.entity.CheckItemShow;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.entity.PosType;

import java.util.List;
import java.util.Map;

public interface PinHaoService {
    public boolean addPosType(String pinhao,String posname ,List<PosCheckInfo> list);
    public boolean updatePosType(String pinhao, String posname,List<PosCheckInfo> list);
    public Map<String,Object> getCheckMethod(String pinhao);
    public boolean addMethodAndValue(String pinhao,String method,String value,String datasource);
    public List<PinHaoSetting> getAllPinhaoInfo(int page,int limit);
    public List<CheckItemShow> getPinHaoItem(String pinhao);
    public int deletePinhao(List<String> ids);
}
