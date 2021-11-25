package com.newland.mes.system.controller;

import com.alibaba.fastjson.JSON;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.entity.PosDetailInfo;
import com.newland.mes.system.entity.PosType;
import com.newland.mes.system.service.PosTypeService;
import com.newland.mes.system.util.CheckInfoUtil;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pos")
public class PosTypeController {

    @Autowired
    PosTypeService posTypeService;

    /**
     * 获取POS信息用于构建POS树
     * @return
     */
    @RequestMapping("/postype")
    ResponseEntity<Object> getAllPosTypeTree(){
        return Result.success(posTypeService.getAllPosTypeTree());
    }

    @RequestMapping("/posIndex")
    public String index(){
        return "pos/posIndex.html";
    }
    @RequestMapping("/SetStation")
    public String SetStation(){
        return "/pos/SetStation.html";
    }
    /**
     * 获取POS信息用于构建POS表
     * @return
     */
    @RequestMapping("/posInfo")
    public ResponseEntity<Object> getAllPosInfo(){
        return Result.success(posTypeService.getAllPosInfo());
    }

    /**
     * 新增POS机型
     * @param map
     * @return
     */
    @RequestMapping("/add")
    public ResponseEntity<Object> AddPosInfo(@RequestBody Map map){
        Map<String,Object> PosData= (Map<String, Object>) map.get("PosData");
        List<Object> CheckData = (List<Object>) map.get("CheckData");
        List<PosCheckInfo> posCheckInfoList=new ArrayList<>();
        PosType posType = JSON.parseObject(JSON.toJSONString(PosData), PosType.class);
        for(int i=0;i<CheckData.size();i++){
            PosCheckInfo posCheckInfo=JSON.parseObject(JSON.toJSONString(CheckData.get(i)),PosCheckInfo.class);
            posCheckInfoList.add(posCheckInfo);
        }

        Boolean ret=posTypeService.addPosType(posType,posCheckInfoList);
        if(ret)
            return Result.success();
        else
            return Result.failure("新增失败");

    }
    @RequestMapping("/update")
    public ResponseEntity<Object> UpdatePosInfo(@RequestBody Map map){
        Map<String,Object> PosData= (Map<String, Object>) map.get("PosData");
        List<Object> CheckData = (List<Object>) map.get("CheckData");
        List<PosCheckInfo> posCheckInfoList= CheckInfoUtil.dealData(map);
        PosType posType = JSON.parseObject(JSON.toJSONString(PosData), PosType.class);
        boolean ret=posTypeService.UpdatePosType(posType,posCheckInfoList);
        if(ret)
        return Result.success();
        else
            return Result.failure("更新失败");
    }
    @GetMapping("checkInfo")
    public ResponseEntity<Object> getCheckInfoByname(String postype){
        List<PosCheckInfo> checkInfoByStationInfo = posTypeService.getCheckInfoByStationInfo(postype);
        return Result.success(checkInfoByStationInfo);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> DelRole(@RequestBody List<String> Ids)
    {
       posTypeService.deletePos(Ids);
        return Result.success();
    }
}
