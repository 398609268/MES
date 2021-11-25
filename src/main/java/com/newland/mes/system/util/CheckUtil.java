package com.newland.mes.system.util;

import com.newland.mes.system.entity.CheckMethod;


import java.util.Map;

public class CheckUtil {
    //1卡控固定值，2卡控范围

    public static  boolean checkDataByMethod(Integer checkMethods,String checkInfo ,String data){
        if(checkMethods==1){
            if(!data.equals(checkInfo))
                return false;
              else return true;
        }else if(checkMethods==2){
            String[] border=checkInfo.split("-");
            if(border.length<=1) return false;
            if(border[0].compareTo(border[1])<0 && data.compareTo(border[0])>=0 && data.compareTo(border[1])<=0)
                return true;
            else if(border[0].compareTo(border[1])>0 && data.compareTo(border[0])<=0 && data.compareTo(border[1])>=0)
                return true;
            else return false;

        }
        return false;
    }
    public static  boolean checkData(Map<String, Object> map, CheckMethod checkMethod){
        String checkInfo;
        Integer checkMethods;
        if(map==null || checkMethod==null) return false;
        for(String key:map.keySet()){
             checkInfo=checkMethod.getCheckInfo().get(key);
            checkMethods=checkMethod.getMethod().get(key);
            if(checkInfo!=null && checkMethods!=null){
                boolean b = checkDataByMethod(checkMethods, checkInfo, (String) map.get(key));
                if(b==false) {
                    System.out.println((String) map.get(key)+" "+checkMethods+" "+checkInfo);
                    return false;
                };
            }
        }

        return true;
    }
    public static boolean checkStation(CheckMethod checkMethod,int Nowstation,int fStation){
        String[] station=checkMethod.getOrder().getStationInfo().split("-");
        for(int i=0;i<station.length;i++){
            System.out.println(fStation+" "+Integer.parseInt(station[i]));
            if(fStation==Integer.parseInt(station[i])){
                if(i==0) return true;
                else{
                    if(Nowstation==Integer.parseInt(station[i-1])){
                        System.out.println(Nowstation+" "+Integer.parseInt(station[i-1]));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
