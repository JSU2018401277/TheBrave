package jsu.DSystem.utils;


import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtils {
    public static void toJson(HttpServletResponse response, Object object) {
        //1.设置响应格式
        response.setContentType("application/json;charset=UTF-8");
        try {
            //2.获取写出流
            PrintWriter out=response.getWriter();
            //3.将对象转成json格式的字符串
            Object objs = JSONArray.toJSON(object);
            String json= objs.toString();
            //4.将字符串写出
            out.write(json);
            //5.关闭流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}