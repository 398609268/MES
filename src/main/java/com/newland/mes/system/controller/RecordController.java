package com.newland.mes.system.controller;

import com.newland.mes.system.entity.LogData;
import com.newland.mes.system.entity.LogRecord;
import com.newland.mes.system.mapper.LogMapper;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    LogMapper logMapper;
    @RequestMapping("/recordIndex")
    public String Index()
    {
        return "record/recordIndex.html";
    }

    @RequestMapping("/count")
    public ResponseEntity<Object> getLogRecord(){
        long start=System.currentTimeMillis();
        List<LogRecord> logCount = logMapper.getLogCount();
        System.out.println(start-System.currentTimeMillis());
        Map<String,Object> out=new HashMap<>();
        out.put("code",0);
        out.put("msg","SUCC");
        out.put("count",logCount.size());
        out.put("data",logCount);
        return new ResponseEntity<>(out,HttpStatus.OK);
    }
    @RequestMapping("/recentrecord")
    public ResponseEntity<Object> getRecnetRecod(){
        List<LogData> list=logMapper.getRecentLog();
        return Result.success(list);
    }
}
