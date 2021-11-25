package com.newland.mes.system.controller;

import com.newland.mes.system.entity.Operation;
import com.newland.mes.system.service.OperationService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/operation")
public class OperationController {
    @Autowired
    OperationService operationService;
    @RequestMapping("/ids")
    public ResponseEntity<Object> getOperationId(String id){
        System.out.println(id);
        List<Integer> ids=operationService.getIdByMenuId(id);
        if(ids!=null && ids.size()>0){
            return Result.success(ids);
        }else
            return Result.failure("fail");
    }
}
