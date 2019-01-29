package com.otc.api.util;

import com.baidu.aip.ocr.AipOcr;
import com.google.gson.Gson;
import com.otc.api.pojo.qr.QRPoJo;
import org.json.JSONObject;
import java.util.HashMap;

public class QRimg {

    //设置APPID/AK/SK
    public static final String APP_ID = "15505031";
    public static final String API_KEY = "Cvdc03ppHGXlxYSYeoQimBE6";
    public static final String SECRET_KEY = "Vhq83Zrz7Dq4tw2QQGPQhD6SaBYwdEkP";

    public static String sample(String url) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        // 通用文字识别, 图片参数为远程url图片
        JSONObject res = client.basicGeneralUrl(url, options);
        System.out.println(res.toString(2));
        Gson gson = new Gson();
        QRPoJo q = gson.fromJson(res.toString(2), QRPoJo.class);
        return q.getWords_result().get(1).getWords().substring(1,q.getWords_result().get(1).getWords().length());
    }


    public static void main(String[] args) {
        String[] s = new String[]{"2","3"};
        Gson gson = new Gson();
        System.out.println(gson.toJson(s));
    }
}
