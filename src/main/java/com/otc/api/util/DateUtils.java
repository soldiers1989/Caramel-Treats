package com.otc.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	public static Date parseDate(String start) {
		if (start.isEmpty()) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = df.parse(start);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date parseDate1(String start) {
		if (start.isEmpty()) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse(start);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getDateFromString5(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = simple.format(date);
		return newDate;
	}

	public static Date addDateMinute(Date date ,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, i);
		return calendar.getTime();
	}
	public static String getDateFromString(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simple.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String newDate = simple.format(date);
		return newDate;
	}

	public static String getDateFromString4(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = simple.format(date);
		return newDate;
	}

	public static String getDateFromString1(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		simple.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String newDate = simple.format(date);
		return newDate;
	}
	public static String getDateFromStringT(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		simple.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String newDate = simple.format(date);
		return newDate;
	}
	public static String getDateFromString2(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		simple.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String newDate = simple.format(date);
		return newDate;
	}
	public static String getDateFromString3(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		simple.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String newDate = simple.format(date);
		return newDate;
	}
	public static Date startDate(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		simple.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String newDate = simple.format(date);
		return parseDate1(newDate);

	}

	public static Date addDate(Date date ,int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, i);
		return calendar.getTime();
	}

	public static Date getBeginDayOfWeek() {
		Date date = new Date();
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek += 7;
		}
		cal.add(Calendar.DATE, 2 - dayofweek);
		return cal.getTime();
	}

	// 获取本周的结束时间
	public static Date getEndDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getBeginDayOfWeek());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal.getTime();
	}

	public static Date getStartDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getEndDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days));
	}

}
