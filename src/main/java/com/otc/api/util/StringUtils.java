package com.otc.api.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isBlank(String name) {
        if (name == null)
            return true;
        if (name.trim().length() == 0)
            return true;
        return false;
    }

    public static boolean isBigDecimal(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        int i = (chars[0] == '-') ? 1 : 0;
        if (i == sz) return false;

        if (chars[i] == '.') return false;//除了负号，第一位不能为'小数点'

        boolean radixPoint = false;
        for (; i < sz; i++) {
            if (chars[i] == '.') {
                if (radixPoint) return false;
                radixPoint = true;
            } else if (!(chars[i] >= '0' && chars[i] <= '9')) {
                return false;
            }
        }
        return true;
    }
    public static String beginNum(String hash){
        hash = hash.substring(2,hash.length());
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(hash);
        String num = m.replaceAll("").trim();
        return num.substring(0,3);
    }

    public static String endNum(String hash){
        hash = hash.substring(2,hash.length());
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(hash);
        String num = m.replaceAll("").trim();
        return num.substring(num.length()-3,num.length());
    }

    public static String substring(String str){
        str = str.substring(0,str.length()-1);

        return str;
    }

    public static String getTypeName(BigDecimal bigDecimal){
        if (bigDecimal==null || bigDecimal.compareTo(new BigDecimal("0"))==0){
            return "普通会员";
        } else if (bigDecimal.compareTo(new BigDecimal("100000"))==-1){
            return "青铜会员";
        } else if (bigDecimal.compareTo(new BigDecimal("500000"))==-1){
            return "白银会员";
        } else if (bigDecimal.compareTo(new BigDecimal("1000000"))==-1){
            return "黄金会员";
        } else if (bigDecimal.compareTo(new BigDecimal("5000000"))==-1){
            return "砖石会员";
        }else{
            return "砖石会员";
        }
    }
}
