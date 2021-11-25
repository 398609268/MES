package com.newland.mes.system.controller;

import com.newland.mes.system.entity.AppConfigSetting;
import com.newland.mes.system.entity.CheckItemShow;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.service.AppConfigService;
import com.newland.mes.system.service.PinHaoService;
import com.newland.mes.system.util.CheckInfoUtil;
import com.newland.mes.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appconfig")
public class AppConfigController {

    @Autowired
    AppConfigService appConfigService;
    @RequestMapping("/appconfigIndex")
    public String Index(){
        return "appconfig/appconfigIndex.html";
    }

    @RequestMapping("/ChooseCheckInfo")
    public ModelAndView ChooseCheckInfo(String id,String appName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/appconfig/ChooseCheckInfo");
        modelAndView.addObject("id", id);
        modelAndView.addObject("appName", appName);
        return modelAndView;
    }

    /**
     * 新增或者更新品号的卡控项目以及站位顺序
      * @param map
     * @return
     */
    @RequestMapping("/add")
    public ResponseEntity<Object> addPinhao(@RequestBody Map map){
        List<PosCheckInfo> posCheckInfoList= CheckInfoUtil.dealData(map);
        if(appConfigService.addPosType(map.get("appName").toString(),
                map.get("posname").toString(),posCheckInfoList)){
            return Result.success();
        }else
        return Result.failure("品号以存在或添加失败");
    }
    @RequestMapping("/update")
    public ResponseEntity<Object> UpdatePinhao(@RequestBody Map map){
        List<PosCheckInfo> posCheckInfoList= CheckInfoUtil.dealData(map);
        if(appConfigService.updatePosType(map.get("appName").toString(),
                map.get("posname").toString(),posCheckInfoList)){
            return Result.success();
        }else
            return Result.failure("品号不存在或修改失败");
    }

    /**
     * 转到设置卡控内容的页面
     * @param appName 品号
     * @return
     */
    @RequestMapping("/SetCheckMethodIndex")
    public ModelAndView SetCheckMethodIndex(String appName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/appconfig/SetCheckMethod");
        modelAndView.addObject("appName", appName);
        return modelAndView;
    }

    /**
     * 显示详细数据
     * @param appName
     * @return
     */
    @RequestMapping("/showdetail")
    public ModelAndView showdetail(String appName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/appconfig/CheckMethodDetail");
        modelAndView.addObject("appName", appName);
        return modelAndView;
    }
    /**
     * 获取要卡控的项目以及卡控方法列表
     * @param appName
     * @return
     */
    @RequestMapping("/getCheckItemAndMethodList")
    public ResponseEntity<Object> getCheckItemAndMethodList(String appName){
        Map map=appConfigService.getCheckMethod(appName);
        return Result.success(map);
    }

    /**
     * 变更或者新增卡控值
     * @param map
     * @return
     */
    @PostMapping("/addMethodAndValue")
    public ResponseEntity<Object> addMethodAndValue(@RequestBody Map map){
        String pinhao=map.get("appName").toString(),
        method=map.get("method").toString(),
        value=map.get("value").toString(),
        datasource=map.get("datasource").toString();
        if(appConfigService.addMethodAndValue(pinhao,method,value,datasource))
            return Result.success();
        else
            return Result.failure("更新失败");
    }

    /**
     * 构建配置表
     * @return
     */
    @RequestMapping("/info")
    public ResponseEntity<Object> getPinhaoInfo(int page,int limit){
        List<AppConfigSetting> allPinhaoInfo = appConfigService.getAllAppConfigInfo(page, limit);
        Map<String,Object> map=new HashMap();
        map.put("msg","获取成功");
        map.put("code",0);
        map.put("count",allPinhaoInfo.size());
        map.put("data",allPinhaoInfo);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping("/GetPinHaoItem")
    public ResponseEntity<Object> GetPinHaoCheckItem(String appName){
        List<CheckItemShow> pinHaoItem = appConfigService.getItem(appName);
        return Result.success(pinHaoItem);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> DelRole(@RequestBody List<String> Ids)
    {
        int i = appConfigService.delete(Ids);
        if(i!=Ids.size()) return Result.failure("删除失败...");
        else
        return Result.success();
    }
}
