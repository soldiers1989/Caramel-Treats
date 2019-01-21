package com.otc.api.util;


public class OrderNumberUtil {

	/**
	 * 下单渠道 支付方式 时间戳8位 随机数4位 用户编码4位
	 * 
	 * @param payType
	 * @param userToken
	 * @return
	 */
	public static String getOrderNumber(String payType, String userToken,Integer len) {
		String orderType = "2";// 下单渠道
		return orderType + payType + TimeUtils.getUnixTime() + fmtLong((long)len,4) + userToken;
	}

	public static String getCashNumber(String payType, String userToken,Integer len) {
		String orderType = "3";// 提现
		return orderType + payType + TimeUtils.getUnixTime() + fmtLong((long)len,4) + userToken;
	}
	
	public static String fmtLong(Long val, int size) {
		StringBuilder sb = new StringBuilder("");
		sb.append(val);
		if (sb.length() < size) {
			int cnt = size - sb.length();
			for (int i = 0; i < cnt; i++) {
				sb.insert(0, "0");
			}
			return sb.toString();
		} else if (sb.length() > size) {
			return sb.substring(sb.length() - size, size);
		} else {
			return sb.toString();
		}
	}

}
