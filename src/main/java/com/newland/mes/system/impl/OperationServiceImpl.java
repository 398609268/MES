package com.newland.mes.system.impl;

import com.newland.mes.system.entity.Operation;
import com.newland.mes.system.entity.User;
import com.newland.mes.system.mapper.OperationMapper;
import com.newland.mes.system.mapper.RoleMapper;
import com.newland.mes.system.service.OperationService;
import com.newland.mes.system.service.RoleService;
import com.newland.mes.system.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    OperationMapper operationMapper;

    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<Integer> getIdByMenuId(String menuId) {
        User user= SecurityUtils.getCurrentUser();
        List<Integer> operation=new ArrayList<>();
        String operationIds=roleMapper.getOperationIds(user.getRoleId());
        List<Integer> list=operationMapper.getIdByMenuId(menuId);
        if(operationIds.contains("admin")){
            return list;
        }
        for (Integer i:list) {
            if(operationIds.contains(i.toString()))
                operation.add(i);
        }
        System.out.println(operation);
        return operation;
    }
}
