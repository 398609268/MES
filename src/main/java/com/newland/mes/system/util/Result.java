package com.newland.mes.system.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public static  ResponseEntity<Object> success() {
        return restResult(null, 0, "操作成功");
    }
    public static<T> ResponseEntity<Object> success(T data) {
        return restResult(data, 0, "操作成功");
    }
    public static<T>  ResponseEntity<Object> failure(T data) {
        return restResult(data, -1, "操作失败");
    }
    private static<T>  ResponseEntity<Object>  restResult(T data, int code, String msg) {

        Map<String,Object> apiResult=new HashMap<>();
        apiResult.put("code", code);
        apiResult.put("data", data);
        apiResult.put("msg", msg);
        return new ResponseEntity<>(apiResult, HttpStatus.OK);
    }
}
