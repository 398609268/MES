package com.newland.mes.system.service;

import com.newland.mes.system.entity.dto.TreeTypeDto;

import java.util.List;
import java.util.Map;

public interface StationService {
    public List<TreeTypeDto> getNameList();

    public List<TreeTypeDto> getDataTypeList();

    public int InsetStationInfo(Map map);

    public int UpdateStationInfo(Map map);
}
