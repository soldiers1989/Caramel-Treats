package com.otc.api.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.otc.api.config.AlipayConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PayUtil {

	private static String public_key = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANfiuQq7zqNuxQZaatdlLVSQ1F6NBFfQMEr3m28VjLev1aCEFftCBB6MyFKH/zSjv8AzKZTrkT5NCB88fAEfoZ8CAwEAAQ==";
	private static String private_key = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA1+K5CrvOo27FBlpq12UtVJDUXo0EV9AwSvebbxWMt6/VoIQV+0IEHozIUof/NKO/wDMplOuRPk0IHzx8AR+hnwIDAQABAkAJQ1UlPMG1CX+yshZ7f3/ebQhvtiVFj01DKjSlI4uSJgkTuXUTUag5CTjHxfTNQkHlZJtMOmHCntK7g2V4x81hAiEA+LWEHPIdy+l/fnQWDWWTqBPFlIPwAqJrJOoEk873g5UCIQDeNuCVL9tuOI/atiOZZBZcKo+/9eHioxzB23FahikDYwIgD8Ko6ZCqNLbKPoAquZSbQ8Zk+eFWf0Q9l+xAeAFgi/UCIQCXzAMUxpFuNONIM74TzcRch5KVPkFlUamWX/KKNTWVIwIhANzsY1663IK+ojTHsQX9W4hoAfUYNOr4ZoMm1V//FnjT";

	/**
	 * 支付时间戳
	 * 
	 * @return
	 */
	public static String payTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 返回签名编码拼接url
	 * 
	 * @param params
	 * @param isEncode
	 * @return
	 */
	public static String getSignEncodeUrl(Map<String, String> map, boolean isEncode) {
		String sign = map.get("sign");
		String encodedSign = "";
		if (!map.isEmpty()) {
			map.remove("sign");
			List<String> keys = new ArrayList<String>(map.keySet());
			// key排序
			Collections.sort(keys);

			StringBuilder authInfo = new StringBuilder();

			boolean first = true;// 是否第一个
			for (String key : keys) {
				if (first) {
					first = false;
				} else {
					authInfo.append("&");
				}
				authInfo.append(key).append("=");
				if (isEncode) {
					try {
						authInfo.append(URLEncoder.encode(map.get(key), AlipayConstants.CHARSET_UTF8));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else {
					authInfo.append(map.get(key));
				}
			}

			try {
				encodedSign = authInfo.toString() + "&sign=" + URLEncoder.encode(sign, AlipayConstants.CHARSET_UTF8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return encodedSign.replaceAll("\\+", "%20");
	}

	public static String getSign(Map<String, String> map,String privateKey) throws AlipayApiException {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        boolean first = true;
        for (String key : keys) {
            if (first) {
                first = false;
            } else {
                authInfo.append("&");
            }
            authInfo.append(key).append("=").append(map.get(key)); 
        }
		System.out.println(authInfo.toString());
		String r = AlipaySignature.rsaSign(authInfo.toString(), privateKey, "utf-8", "RSA");
        return r;
    }



//	public static void main(String[] args) throws Exception {
//		Map<String, String> map = new HashMap<>();
//		map.put("appId","20190115151819");
//		map.put("notifyUrl","http://47.93.120.116:8008/yioOrders/testNotify");
//		map.put("timestamp",DateUtils.getDateFromString(new Date()));
//		map.put("version","1.0");
//		map.put("orderId","20190115151819");
//		map.put("amount","0.01");
//		map.put("payType","2");
//		map.put("timeExpire","");
//		String r = getSign(map,AlipayConfig.key);
//		System.out.println(r);
//		String urlEncodee= URLEncoder.encode(r);
//		String urlEncodeeU= URLEncoder.encode(r,"GBK");
//		System.out.println(urlEncodee);
//		System.out.println(urlEncodeeU);
//		String r1 = getSign(map,AlipayConfig.key);
//		if (r.equals(r1)){
//			System.out.println(true);
//		}
//
////		Map<String, String> map = new HashMap<>();
////		map.put("code","0");
////		map.put("msg","获取成功");
////		map.put("out_trade_no","");
////		map.put("trade_no","");
////		map.put("total_amount","");
////		map.put("seller_id","");
////		map.put("timestamp",DateUtils.getDateFromString(new Date()));
////		Gson gson = new Gson();
////		String s = HttpRequest.sendPost("http://127.0.0.1:8008/yioOrders/testNotify",gson.toJson(map));
////		System.out.println(s);
//	}
}