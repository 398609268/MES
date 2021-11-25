package com.newland.mes.system.service;

import com.newland.mes.system.entity.Operation;

import java.util.List;

public interface OperationService {

    List<Integer> getIdByMenuId(String menuId);
}
