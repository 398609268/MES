package com.newland.mes.system.service;

import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.entity.PosType;
import com.newland.mes.system.entity.Station;
import com.newland.mes.system.entity.dto.TreeTypeDto;

import java.util.List;

public interface PosTypeService {
    public List<TreeTypeDto> getAllPosTypeTree();
    public List<PosType> getAllPosInfo();
    public boolean addPosType(PosType posType, List<PosCheckInfo> list);
    public boolean UpdatePosType(PosType posType, List<PosCheckInfo> list);
    public List<PosCheckInfo> getCheckInfoByStationInfo(String posname);
    public void deletePos(List<String> ids);
}
