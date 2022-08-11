package org.tcl.autotest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class CheckResponseUtils {

    /**
     * 检查接口的返回数据
     * @param result 接口数据，json格式
     * @param responseKey 接口数据中的key，如 code
     * @param responseKValue 接口数据中的value，如 0
     */
    public static boolean checkResponseData(String result, String responseKey, int responseKValue) {
        JSONObject createJsonObject = JSON.parseObject(result);
        int value = 0;
        try {
            value = createJsonObject.getInteger(responseKey);
        } catch (Exception e) {
            System.out.println(responseKey + "的实际返回值" + value + ", 期望值是" + responseKValue);
            return false;
        }
        if (responseKValue == value) {
            return true;
        } else {
            return false;
        }
    }



}
