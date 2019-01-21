package com.otc.api.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088411584716500";
	
	public static String appId = "2016091901927244";
	
	// 收款支付宝账号，一般情况下收款账号就是签约账号
	public static String seller_email = "zyx_touring@163.com";
	// 商户的私钥
	public static String key = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAlq/UjH9akXrSfWjOEc3b2f2qepiOvATrex11S7IerQIwE8qwJWzYpnSlSTSP4/v/UqGNrfV/BtZJCB1zgjW7AQIDAQABAkAa25IVzKk23t5fjeuUzs1NyGG3YjOmnXXfRrveVwwSp9YO0SIL1eTS33otrQuhKewR021/gu2uArTyOupMeLABAiEA+r+5Jn5ZxQeoXfGSnxFE7AKB89wt2Kukg6M9/rjxeMECIQCZ16tj+tapcVZ1HxAKJ0E02ZG7X8OMH8s0mMt1EAiSQQIhAMJoAOm6Tmk3La9gSxgusGv26cAithzlvtAcsUUbKIRBAiEAhUDXxav5z57KInybahACXQKvRjt2p6LKYm6OnIEcy4ECIQCOZKTRNePdTsONqL030XHuNhVKsJCuMSFiOmRb/vvP2Q==";

	//支付宝公钥
	public static String public_key ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	
	public static String alipay_refund = "https://openapi.alipay.com/gateway.do";
	
	public static String alipay_cash = "https://openapi.alipay.com/gateway.do";
	
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// RSA2
	public static String SIGNTYPE = "RSA2";
	// 手机网站RSA
	public static String SIGN_TYPE = "RSA";
	//手机网站支付宝回调地址
	public static String alipay_refund_web = "/v1/interface/shopOrders/aliPayWeb/notify";
	//手机网站支付宝同步通知地址
	public static String notify_url_web = "/h5v2/earn_share/shopping/pay-result.html";

	
	
}
