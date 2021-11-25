package com.newland.mes.system.controller;


import com.newland.mes.system.entity.LogData;
import com.newland.mes.system.mapper.LogMapper;
import com.newland.mes.system.util.IpUtil;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller

public class interactionController {
    volatile int num=0;
    @Autowired
    LogMapper logMapper;
    //将收到的数据转发回去
    @PostMapping("SendBack")
    ResponseEntity<Object> ReceiverAndSend(HttpServletRequest request ,@RequestBody Map map){
        String ip = IpUtil.getIpAddr(request);
        num++;
        LogData logData=new LogData();
        logData.setData((String) map.get("data"));
        logData.setIp(ip);
        logData.setMethod("POST");
        logData.setNum(num);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logData.setCreateTime(format.format(new Date()));
      //  System.out.println("POST收到数据为: "+map+"总共接收到: "+num+" 个数据,ip:"+ip);
        long startTime=System.currentTimeMillis();
        try {
            logMapper.addLogDataToTestLof(logData);
        }catch (DataAccessException e){
            return Result.failure(e.getMessage());
        }

        System.out.println(startTime-System.currentTimeMillis());
        return Result.success(map);
    }

    @GetMapping("SendBack")
    ResponseEntity<Object> ReceiverAndSendGet(HttpServletRequest request ,@RequestParam Map map){
        long startTime=System.currentTimeMillis();

        String ip = IpUtil.getIpAddr(request);
        num++;
        LogData logData=new LogData();
        logData.setData((String) map.get("data"));
        logData.setIp(ip);
        logData.setMethod("GET");
        logData.setNum(num);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logData.setCreateTime(format.format(new Date()));
       // System.out.println("GET收到数据为: "+map+"总共接收到: "+num+" 个数据,ip:"+ip);
        try {
            logMapper.addLogDataToTestLof(logData);
        }catch (DataAccessException e){
            return Result.failure(e.getMessage());
        }
        System.out.println(startTime-System.currentTimeMillis());
        return Result.success(map);
    }
    @RequestMapping("file")
    ResponseEntity<Object> SaveImg(MultipartFile file) {
        System.out.println("file");
        Map<String, Object> map = new HashMap<>();
        try {
            if (file != null) {
                String filepath = "D:\\SSM_Project\\MesServer\\src\\main\\resources\\static\\" + file.getOriginalFilename();
                File files = new File(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);

                map.put("code", 0);
                map.put("msg", "");
                map.put("src","http://localhost:8080/"+file.getOriginalFilename());
                return new ResponseEntity<>(map,HttpStatus.OK);
            }
        }
        catch (IOException e){
            map.put("code", 1);
            map.put("msg", "文件保存失败");
        }
        map.put("code", 1);
        map.put("msg", "文件为空");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
