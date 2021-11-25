package com.newland.mes.system.controller;

import com.newland.mes.system.entity.Order;
import com.newland.mes.system.entity.Station;
import com.newland.mes.system.mapper.StationMapper;
import com.newland.mes.system.service.StationService;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/station")
public class StationController {

    @Autowired
    StationMapper stationMapper;

    @Autowired
    StationService stationService;
    @RequestMapping("/stationIndex")
    public String Index(){
        return "station/stationIndex.html";
    }

    @RequestMapping("/info")
    @PreAuthorize("@el.check('station:list')")
    public ResponseEntity<Object> getAllStation(){
        List<Station> logCount=stationMapper.getAllStation();
        Map<String,Object> out=new HashMap<>();
        out.put("code",0);
        out.put("msg","SUCC");
        out.put("count",logCount.size());
        out.put("data",logCount);

        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @RequestMapping("/name")
    public ResponseEntity<Object> getNameList(){
        return Result.success(stationService.getNameList());
    }
    @RequestMapping("/dataType")
    public ResponseEntity<Object> getDataTypeList(){
        return Result.success(stationService.getDataTypeList());
    }

    @RequestMapping("/insert")
    @PreAuthorize("@el.check('station:add')")
    public ResponseEntity<Object> InSertStationInfo(@RequestBody Map map){
        if(stationMapper.checkStationRepeat(map.get("stationname").toString(),map.get("PosType").toString())==0) {
            try {
                if (stationService.InsetStationInfo(map) == 1)
                    return Result.success();
                else
                    return Result.failure("添加失败");
            } catch (Exception e) {
                return Result.failure("添加失败");
            }

        }
        else
            return Result.failure("站位已设置");
    }
    @RequestMapping("/update")
    @PreAuthorize("@el.check('station:change')")
    public ResponseEntity<Object> UpdateStationInfo(@RequestBody Map map){

            try {
                if (stationService.UpdateStationInfo(map) == 1)
                    return Result.success();
                else
                    return Result.failure("更新失败");
            } catch (Exception e) {
                System.out.println(e);
                return Result.failure("更新失败");
            }

    }
}
