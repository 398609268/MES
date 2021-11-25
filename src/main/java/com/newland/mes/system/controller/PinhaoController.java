package com.newland.mes.system.controller;

import com.alibaba.fastjson.JSON;
import com.newland.mes.system.entity.CheckItemShow;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;
import com.newland.mes.system.entity.PosType;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pinhao")
public class PinhaoController {

    @Autowired
    PinHaoService pinHaoService;
    @RequestMapping("/pinhaoIndex")
    public String Index(){
        return "pinhao/pinhaoIndex.html";
    }

    @RequestMapping("/ChooseCheckInfo")
    public ModelAndView ChooseCheckInfo(String id,String posname){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pinhao/ChooseCheckInfo");
        modelAndView.addObject("id", id);
        modelAndView.addObject("posname", posname);
        System.out.println(posname);
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
        if(pinHaoService.addPosType(map.get("PinHao").toString(),
                map.get("posname").toString(),posCheckInfoList)){
            return Result.success();
        }else
        return Result.failure("品号以存在或添加失败");
    }
    @RequestMapping("/update")
    public ResponseEntity<Object> UpdatePinhao(@RequestBody Map map){
        List<PosCheckInfo> posCheckInfoList= CheckInfoUtil.dealData(map);
        if(pinHaoService.updatePosType(map.get("PinHao").toString(),
                map.get("posname").toString(),posCheckInfoList)){
            return Result.success();
        }else
            return Result.failure("品号不存在或修改失败");
    }

    /**
     * 转到设置卡控内容的页面
     * @param pinhao 品号
     * @return
     */
    @RequestMapping("/SetCheckMethodIndex")
    public ModelAndView SetCheckMethodIndex(String pinhao){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pinhao/SetCheckMethod");
        modelAndView.addObject("pinhao", pinhao);
        return modelAndView;
    }

    /**
     * 显示详细数据
     * @param pinhao
     * @return
     */
    @RequestMapping("/showdetail")
    public ModelAndView showdetail(String pinhao){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pinhao/CheckMethodDetail");
        modelAndView.addObject("pinhao", pinhao);
        return modelAndView;
    }
    /**
     * 获取要卡控的项目以及卡控方法列表
     * @param pinhao
     * @return
     */
    @RequestMapping("/getCheckItemAndMethodList")
    public ResponseEntity<Object> getCheckItemAndMethodList(String pinhao){
        Map map=pinHaoService.getCheckMethod(pinhao);
        return Result.success(map);
    }

    /**
     * 变更或者新增卡控值
     * @param map
     * @return
     */
    @PostMapping("/addMethodAndValue")
    public ResponseEntity<Object> addMethodAndValue(@RequestBody Map map){
        String pinhao=map.get("pinhao").toString(),
        method=map.get("method").toString(),
        value=map.get("value").toString(),
        datasource=map.get("datasource").toString();
        if(pinHaoService.addMethodAndValue(pinhao,method,value,datasource))
            return Result.success();
        else
            return Result.failure("更新失败");
    }

    /**
     * 构建品号表
     * @return
     */
    @RequestMapping("/info")
    public ResponseEntity<Object> getPinhaoInfo(int page,int limit){
        List<PinHaoSetting> allPinhaoInfo = pinHaoService.getAllPinhaoInfo(page, limit);
        Map<String,Object> map=new HashMap();
        map.put("msg","获取成功");
        map.put("code",0);
        map.put("count",allPinhaoInfo.size());
        map.put("data",allPinhaoInfo);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping("/GetPinHaoItem")
    public ResponseEntity<Object> GetPinHaoCheckItem(String pinhao){
        List<CheckItemShow> pinHaoItem = pinHaoService.getPinHaoItem(pinhao);
        return Result.success(pinHaoItem);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> DelRole(@RequestBody List<String> Ids)
    {
        int i = pinHaoService.deletePinhao(Ids);
        if(i!=Ids.size()) return Result.failure("删除失败...");
        else
        return Result.success();
    }
}
