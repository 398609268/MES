package com.newland.mes.test.controller;

import com.newland.mes.system.entity.CheckMethod;
import com.newland.mes.system.entity.LogData;
import com.newland.mes.system.mapper.LogMapper;
import com.newland.mes.system.service.OrderService;
import com.newland.mes.system.util.CheckUtil;
import com.newland.mes.system.util.IpUtil;
import com.newland.mes.system.util.Result;
import com.newland.mes.test.entity.BHdata;
import com.newland.mes.test.entity.PNdata;
import com.newland.mes.test.mapper.FactoryMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    OrderService orderService;
    @Autowired
    FactoryMapper factoryMapper;
    @Autowired
    LogMapper logMapper;
    private int num;
    @RequestMapping("/pn")
    public ResponseEntity<Object> test(String pn){
        long startTime=System.currentTimeMillis();
        factoryMapper.test(pn);
        System.out.println(System.currentTimeMillis()-startTime);
        return Result.success();
    }
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
    ResponseEntity<Object> ReceiverAndSendGet(HttpServletRequest request , @RequestParam Map map){

        String ip = IpUtil.getIpAddr(request);
        num++;
        LogData logData=new LogData();
        logData.setData((String) map.get("data"));
        logData.setIp(ip);
        logData.setMethod("GET");
        logData.setNum(num);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logData.setCreateTime(format.format(new Date()));
        long startTime;
        // System.out.println("GET收到数据为: "+map+"总共接收到: "+num+" 个数据,ip:"+ip);
        try {
            startTime=System.currentTimeMillis();
            logMapper.addLogDataToTestLof(logData);
        }catch (DataAccessException e){
            return Result.failure(e.getMessage());
        }
        System.out.println(System.currentTimeMillis()-startTime);
        return Result.success(map);
    }
    //测试流程，第一步传入
    @PostMapping("/start")
    public ResponseEntity<Object> start( ){
        orderService.resloverCheck("ZS13-21060349");
      //  System.out.println(orderService.getCheckMethod());
        return Result.success();
        //int result=factoryMapper.InsertBH(bHdata);
      //  if(result==1) return Result.success();
        //else return Result.failure("插入失败");
    }
    public boolean DataCheck(Map map){
        String orderId= (String) map.get("orderId");
        Map<String, CheckMethod> checkMethod = orderService.getCheckMethod();
        if(checkMethod.get(orderId)==null){
            orderService.resloverCheck(orderId);
            checkMethod = orderService.getCheckMethod();
        }

        return CheckUtil.checkData(map, checkMethod.get(orderId));
    }
    @PostMapping("/BH")
    public ResponseEntity<Object> BhDown(@RequestBody Map map){
        BHdata bHdata;
        String orderId= (String) map.get("orderId");
        boolean b=DataCheck(map);
        int nowStation=factoryMapper.getStationBybanhao(map.get("banhao").toString());
        if(b==false)
            return Result.failure("数据校验失败");
        if(CheckUtil.checkStation(orderService.getCheckMethod().get(orderId),nowStation,Integer.parseInt(map.get("station").toString()))==false)
            return Result.failure("跳站");
        try {
             bHdata=new BHdata(map.get("banhao").toString(),Integer.parseInt(map.get("status").toString()),map.get("orderId").toString(),Integer.parseInt(map.get("station").toString()));
        }
        catch (Exception e){
            return Result.failure("参数异常");
        }
        factoryMapper.InsertBH(bHdata);
        return Result.success();
    }
    @PostMapping("/PN")
    public ResponseEntity<Object> PnDown(@RequestBody Map map){
        String orderId= (String) map.get("orderId");
        PNdata pn;
        int nowStation=factoryMapper.getStationBybanhao(map.get("banhao").toString());
        if(!DataCheck(map))
            return Result.failure("数据校验失败");
        if(!CheckUtil.checkStation(orderService.getCheckMethod().get(orderId), nowStation, Integer.parseInt(map.get("station").toString())))
            return Result.failure("跳站");
        if(map.get("PN")!=null && map.get("MN")!=null && map.get("banhao")!=null && map.get("station")!=null)
            try {
                factoryMapper.updatePn(map.get("PN").toString(), map.get("MN").toString(), map.get("banhao").toString(), Integer.parseInt(map.get("station").toString()));
            }catch (DataAccessException e){
            return Result.failure(e.getMessage());
        }
        return  Result.success();
    }
    @PostMapping("/SN")
    public ResponseEntity<Object> SNDown(@RequestBody Map map){
        String orderId= (String) map.get("orderId");
        PNdata pn;
        int nowStation=factoryMapper.getStationByMN(map.get("MN").toString());
        if(!DataCheck(map))
            return Result.failure("数据校验失败");
        if(!CheckUtil.checkStation(orderService.getCheckMethod().get(orderId), nowStation, Integer.parseInt(map.get("station").toString())))
            return Result.failure("跳站");
        if(map.get("SN")!=null && map.get("MN")!=null  && map.get("station")!=null)
            try {
                factoryMapper.updateSn(map.get("SN").toString(), map.get("MN").toString(), Integer.parseInt(map.get("station").toString()));
            }catch (DataAccessException e){
                return Result.failure(e.getMessage());
            }
        return  Result.success();
    }
}
