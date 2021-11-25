package com.newland.mes.system.util;

import com.newland.mes.system.entity.AppConfigSetting;
import com.newland.mes.system.entity.PinHaoSetting;
import com.newland.mes.system.entity.PosCheckInfo;

import java.util.*;

public class PinHaoUtil {
    public static final String stationSplit="/";
    public static final String methodSplit="/";      //卡控方法的站位分隔符
    public static final String methoditemSplit="-";
    public static final String datasourceSplit="/";  //数据来源的站位分隔符
    public static final String datasourceitemSplit="-";
    public static final String checkInfoSplit="/";      //卡控项目的站位分隔符
    public static final String checkInfoitemSplit="-";
    public static final String checkvalueSplit="/"; //卡控值的站位分隔符
    public static final String checkvalueItemSplit=":>";
    public static PinHaoSetting SetDataToPinhao(String pinhao, String appVersion, List<PosCheckInfo> list){
        PinHaoSetting pinHaoSetting=new PinHaoSetting();
        pinHaoSetting.setPinhao(pinhao);
        return pinHaoSetting;
    }
    public static List<String> getNameByidsWithMap(Map<Integer,String> map,List<String> ids){
        List<String> nameList=new ArrayList<>();
        for(String s:ids){
            if(s.length()>0)
            nameList.add(map.get(Integer.parseInt(s)));
        }
        return nameList;
    }
    public static List<List<String>> getNameByidsWithMapFormultiple(Map<Integer,String> map,List<String> checkInfo){
        List<List<String>> CheckInfoList=new ArrayList<>();
        if(checkInfo==null) return CheckInfoList;
        for(String s:checkInfo){
            List<String> checkInfoids= Arrays.asList(s.split("-"));
            List<String> stationName = getNameByidsWithMap(map, checkInfoids);
            CheckInfoList.add(stationName);
        }
        return CheckInfoList;
    }
    public static List<List<String>> getIds(String Info,String stationSplit,String itemsplit){
        List<List<String>> CheckInfoList=new ArrayList<>();
        if(Info==null) return new ArrayList<>();
        List<String> splitList= Arrays.asList(Info.split(stationSplit));
        for(String s:splitList){
            List<String> list = Arrays.asList(s.split(itemsplit));

            CheckInfoList.add(list);
        }
        return CheckInfoList;
    }
    public static List<List<String>> dealCheckValue(String checkvalue){
        List<List<String>> CheckValueList=new ArrayList<>();
        if (checkvalue==null) return CheckValueList;
        List<String> valueForStation= Arrays.asList(checkvalue.split(checkvalueSplit));
        for (String s:valueForStation){
            List<String> valueItem= Arrays.asList(s.split(checkvalueItemSplit));
            CheckValueList.add(valueItem);
        }
        return CheckValueList;
    }
    public static String getappend(List<List<String>> data,int i,int j,String defaultValue){
        if(data.size()>0){
            return data.get(i).get(j);
        }else
            return defaultValue;
    }
    public static Map<String,String> setothersByChange(List<PosCheckInfo> list,PinHaoSetting p){
        Map<String,String> map=new HashMap<>();
        StringBuilder StationId=new StringBuilder(),checkInfo=new StringBuilder(),
                checkMethodIdNew=new StringBuilder(),checkvalueNew=new StringBuilder(),datasourceNew=new StringBuilder();
        List<List<String>> checkInfoId=getIds(p.getCheckInfoId(),methodSplit,checkInfoitemSplit),
        checkMethodId=getIds(p.getCheckMethodId(),methodSplit,methoditemSplit),
        checkvalue=getIds(p.getCheckvalue(),checkvalueSplit,checkvalueItemSplit),
        datasource=getIds(p.getDatasource(),datasourceSplit,datasourceitemSplit);
        for(int i=0;i<list.size();i++){
            StationId.append(list.get(i).getId());
            StationId.append("/");
            if(list.get(i).getChildren()==null){
                checkInfo.append("0").append(checkInfoSplit);
                checkMethodIdNew.append("0").append(methoditemSplit);
                checkvalueNew.append("null").append(checkvalueItemSplit);
                datasourceNew.append("0").append(datasourceitemSplit);
            }else {
                System.out.println("List: "+list.get(i));
                List<PosCheckInfo> children = list.get(i).getChildren();
                if(children.size()==0){
                    checkInfo.append("0").append(checkInfoitemSplit);
                    checkMethodIdNew.append("0").append(methoditemSplit);
                    checkvalueNew.append("null").append(checkvalueItemSplit);
                    datasourceNew.append("0").append(datasourceitemSplit);
                }else {
                    for (int j = 0; j < children.size(); j++) {
                        int n;
                        for(n=0;n<checkInfoId.get(i).size();n++){
                            if (children.get(j).getId().toString().equals(checkInfoId.get(i).get(n))) {
                                checkInfo.append(getappend(checkInfoId, i, n, "0")).append(checkInfoitemSplit);
                                checkMethodIdNew.append(getappend(checkMethodId, i, n, "0")).append(methoditemSplit);
                                checkvalueNew.append(getappend(checkvalue, i, n, "null")).append(checkvalueItemSplit);
                                datasourceNew.append(getappend(datasource, i, n, "0")).append(datasourceitemSplit);
                                break;
                            }
                        }
                        if(n>=checkInfoId.get(i).size()) {
                            checkInfo.append(children.get(j).getId()).append(checkInfoitemSplit);
                            checkMethodIdNew.append("0").append(methoditemSplit);
                            checkvalueNew.append("null").append(checkvalueItemSplit);
                            datasourceNew.append("0").append(datasourceitemSplit);
                        }
                    }
                }
            }
            checkInfo.append("/");
            checkMethodIdNew.append("/");
            checkvalueNew.append("/");
            datasourceNew.append("/");
        }
        map.put("stationId",StationId.toString());
        map.put("checkInfoId",checkInfo.toString());
        map.put("checkMethodId",checkMethodIdNew.toString());
        map.put("checkvalue",checkvalueNew.toString());
        map.put("datasource",datasourceNew.toString());
        return map;
    }

    public static Map<String,String> setothersByChange(List<PosCheckInfo> list, AppConfigSetting p){
        Map<String,String> map=new HashMap<>();
        StringBuilder StationId=new StringBuilder(),checkInfo=new StringBuilder(),
                checkMethodIdNew=new StringBuilder(),checkvalueNew=new StringBuilder(),datasourceNew=new StringBuilder();
        List<List<String>> checkInfoId=getIds(p.getCheckInfoId(),methodSplit,checkInfoitemSplit),
                checkMethodId=getIds(p.getCheckMethodId(),methodSplit,methoditemSplit),
                checkvalue=getIds(p.getCheckvalue(),checkvalueSplit,checkvalueItemSplit),
                datasource=getIds(p.getDatasource(),datasourceSplit,datasourceitemSplit);
        for(int i=0;i<list.size();i++){
            StationId.append(list.get(i).getId());
            StationId.append("/");
            if(list.get(i).getChildren()==null){
                checkInfo.append("0").append(checkInfoSplit);
                checkMethodIdNew.append("0").append(methoditemSplit);
                checkvalueNew.append("null").append(checkvalueItemSplit);
                datasourceNew.append("0").append(datasourceitemSplit);
            }else {
                System.out.println("List: "+list.get(i));
                List<PosCheckInfo> children = list.get(i).getChildren();
                if(children.size()==0){
                    checkInfo.append("0").append(checkInfoitemSplit);
                    checkMethodIdNew.append("0").append(methoditemSplit);
                    checkvalueNew.append("null").append(checkvalueItemSplit);
                    datasourceNew.append("0").append(datasourceitemSplit);
                }else {
                    for (int j = 0; j < children.size(); j++) {
                        int n;
                        for(n=0;n<checkInfoId.get(i).size();n++){
                            if (children.get(j).getId().toString().equals(checkInfoId.get(i).get(n))) {
                                checkInfo.append(getappend(checkInfoId, i, n, "0")).append(checkInfoitemSplit);
                                checkMethodIdNew.append(getappend(checkMethodId, i, n, "0")).append(methoditemSplit);
                                checkvalueNew.append(getappend(checkvalue, i, n, "null")).append(checkvalueItemSplit);
                                datasourceNew.append(getappend(datasource, i, n, "0")).append(datasourceitemSplit);
                                break;
                            }
                        }
                        if(n>=checkInfoId.get(i).size()) {
                            checkInfo.append(children.get(j).getId()).append(checkInfoitemSplit);
                            checkMethodIdNew.append("0").append(methoditemSplit);
                            checkvalueNew.append("null").append(checkvalueItemSplit);
                            datasourceNew.append("0").append(datasourceitemSplit);
                        }
                    }
                }
            }
            checkInfo.append("/");
            checkMethodIdNew.append("/");
            checkvalueNew.append("/");
            datasourceNew.append("/");
        }
        map.put("stationId",StationId.toString());
        map.put("checkInfoId",checkInfo.toString());
        map.put("checkMethodId",checkMethodIdNew.toString());
        map.put("checkvalue",checkvalueNew.toString());
        map.put("datasource",datasourceNew.toString());
        return map;
    }
}
