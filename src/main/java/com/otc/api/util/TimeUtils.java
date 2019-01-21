package com.otc.api.util;

public class TimeUtils {
	
	public static String getUnixTime(){
		Long i = System.currentTimeMillis()/1000L;
		String r = i.toString().substring(i.toString().length()-8, i.toString().length());
		return r;
	}
}
