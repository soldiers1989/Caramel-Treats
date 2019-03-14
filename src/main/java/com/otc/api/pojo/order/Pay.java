package com.otc.api.pojo.order;

import java.math.BigDecimal;

public class Pay {

    private String appId;//分配的应用ID
    private String notifyUrl;//服务器主动通知商户服务器里指定的页面http/https路径。
    private String timestamp;//发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
    private String version;//调用的接口版本，固定为：1.0
    private String orderId;//业务系统订单ID
    private BigDecimal amount;//金额
    private String sign;//请求参数的签名串
    private Integer payType;//1:支付宝 2:微信 3:银行卡
    private String timeExpire;//超时时间 yyyy-MM-dd HH:mm。默认5分钟

    //付款账户id
    private Integer sellerId;
    
    public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }
}
