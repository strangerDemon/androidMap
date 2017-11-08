package com.example.administrator.androidmap.vo;

import com.example.administrator.androidmap.utils.StringUtils;

import net.minidev.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by tengdag on 2016/11/21 13:54.
 */

public class BaseVo implements Serializable {


    public static void fillIt(Object obj, JSONObject jObj){
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            if(method.getName().indexOf("set") >= 0){
                String fieldName = method.getName().substring(method.getName().indexOf("set") + 3, method.getName().length());
                String fieldNameFirst = fieldName.substring(0, 1).toLowerCase();
                fieldName = fieldName.length() > 1 ? fieldNameFirst + fieldName.substring(1, fieldName.length()) : fieldNameFirst;
                Class<?>[] types = method.getParameterTypes();
                if(types != null && types.length > 0){
                    if(types[0] == int.class || types[0] == Integer.class){
                        invokeMethod(method, obj, getIntValue(fieldName, jObj));
                    }else if(types[0] == long.class || types[0] == Long.class){
                        invokeMethod(method, obj, getLongValue(fieldName, jObj));
                    }else if(types[0] == boolean.class || types[0] == Boolean.class){
                        invokeMethod(method, obj, getBooleanValue(fieldName, jObj));
                    }else if(types[0] == double.class || types[0] == Double.class){
                        invokeMethod(method, obj, getDoubleValue(fieldName, jObj));
                    }else if(types[0] == String.class){
                        invokeMethod(method, obj, getStringValue(fieldName, jObj));
                    }
                }
            }
        }

    }


    private static void invokeMethod(Method method, Object obj, Object... args){
        try {
            method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStringValue(String name, JSONObject jObj) {
        if (jObj.containsKey(name)) {
            Object val = jObj.get(name);
            if (null != val)
                return val.toString();
        }
        return StringUtils.EMPTY_SYSM;
    }

    public static int getIntValue(String name, JSONObject jObj) {
        if (jObj.containsKey(name)) {
            Object val = jObj.get(name);
            try {
                if (null != val)
                    return Integer.valueOf(val.toString());
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static long getLongValue(String name, JSONObject jObj) {
        if (jObj.containsKey(name)) {
            Object val = jObj.get(name);
            try {
                if (null != val)
                    return Long.parseLong(val.toString());
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static float getFloatValue(String name, JSONObject jObj) {
        if (jObj.containsKey(name)) {
            Object val = jObj.get(name);
            try {
                if (null != val)
                    return Float.valueOf(val.toString());
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static String getDecimalValue(String name, JSONObject jObj) {
        return getDecimalValue(name, jObj, 2);
    }

    public static String getDecimalValue(String name, JSONObject jObj,
                                         int decimal) {
        float value = getFloatValue(name, jObj);
        String dec = "0.00";
        try {
            dec = new BigDecimal(value).setScale(decimal, RoundingMode.HALF_UP)
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dec;
    }

    public static boolean getBooleanValue(String name, JSONObject jObj) {
        if (jObj.containsKey(name)) {
            Object val = jObj.get(name);
            try {
                if (null != val)
                    return Boolean.valueOf(val.toString());
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static double getDoubleValue(String name, JSONObject jObj) {
        if (jObj.containsKey(name)) {
            Object val = jObj.get(name);
            try {
                if (null != val)
                    return Double.valueOf(val.toString());
            } catch (Exception e) {
            }
        }
        return 0;
    }
}
