package com.example.demo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/2/8 22:27
 * @Version: 1.0
 **/
public class ResponseBo extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    public ResponseBo(){
        put("code",0);
        put("msg","操作成功");
    }

    public static ResponseBo error(){
        return error(1,"操作失败");
    }
    public static ResponseBo error(String msg) {
        return error(500, msg);
    }

    public static ResponseBo error(int code, String msg) {
        ResponseBo ResponseBo = new ResponseBo();
        ResponseBo.put("code", code);
        ResponseBo.put("msg", msg);
        return ResponseBo;
    }

    public static ResponseBo ok(String msg) {
        ResponseBo ResponseBo = new ResponseBo();
        ResponseBo.put("msg", msg);
        return ResponseBo;
    }

    public static ResponseBo ok(Map<String, Object> map) {
        ResponseBo ResponseBo = new ResponseBo();
        ResponseBo.putAll(map);
        return ResponseBo;
    }

    public static ResponseBo ok() {
        return new ResponseBo();
    }
    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }
}
