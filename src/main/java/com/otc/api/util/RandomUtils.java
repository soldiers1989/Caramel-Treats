package com.otc.api.util;

public class RandomUtils {
    /**
     * 生成6位随机数验证码
     * @return
     */
    public static int checkcode(){
        return (int)((Math.random()*9+1)*100000);
    }
}
