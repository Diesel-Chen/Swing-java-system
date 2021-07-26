package com.neuedu.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Json字符串与对象转换的工具类
 *
 * @author 高军
 * @date 2021-7-8
 */
public class GsonUtil {

    /**
     * 将Java对象转换成Json字符串的方法
     *
     * @param object java对象
     * @return Json字符串
     */
    public static String toJson(Object object) {
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();
        String str = gson.toJson(object);
        str = str.replace("\n", "");
        str = str.replace(" ", "");
        return str;
    }

    /**
     * 将Json字符串转换成Java对象的方法
     *
     * @param str Json字符串
     * @param ?   表示不确定的java类型
     * @return Java对象
     */
    public static Object toObj(String str, Class<?> C) {
        JsonParser jp = new JsonParser();
        JsonArray jar = jp.parse(str).getAsJsonArray();
        Gson gson = new Gson();
        Object obj = null;
        for (JsonElement item : jar) {
            obj = gson.fromJson(item, C);
        }
        return obj;
    }

    /**
     * 将Json字符串转换成Java对象集合的方法
     *
     * @param str Json字符串
     * @param ?   表示不确定的java 类型
     * @return Java对象集合
     */
    public static List<Object> toObjList(String str, Class<?> C) {
        JsonParser jp = new JsonParser();
        JsonArray jar = jp.parse(str).getAsJsonArray();
        Gson gson = new Gson();
        List<Object> objList = new ArrayList<Object>();
        for (JsonElement item : jar) {
            Object obj = gson.fromJson(item, C);
            objList.add(obj);
        }
        return objList;
    }
}