package com.newland.mes.system.impl;

import com.newland.mes.system.entity.Factory;
import com.newland.mes.system.mapper.FactoryMapper;
import com.newland.mes.system.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    FactoryMapper factoryMapper;

    @Override
    public List<Factory> getAllFactoryName() {
        return factoryMapper.getAllFactoryName();
    }
}
