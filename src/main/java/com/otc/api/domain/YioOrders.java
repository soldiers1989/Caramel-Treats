package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.otc.api.pojo.order.Pay;
import com.otc.api.util.DateUtils;
import com.otc.api.util.OrderUtil;
import com.otc.api.util.StringUtils;
public class YioOrders {

	//
	private Integer id;
	//
	private String orderId;
	//
	private BigDecimal orderPrice;
	//
	private BigDecimal payPrice;
	//
	private String  payStatus;
	//
	private String payType;
	//
	private String payFormat;
	//
	private String payQr;
	//
	private String redirectUrl;
	//
	private String extension;
	//
	private Date createdAt;
	//
	private Date updatedAt;
	//
	private Date deletedAt;
	//收款方式id
	private Integer sellerId;
	//用户id
	private Integer userId;
	//1:未到帐 2:已到帐 3:已过期 4:部帐 5:清算
	private Integer type;
	public YioOrders() {
	}
	public YioOrders(YioSeller yioSeller,Pay pay,String payType) {
		this.orderId = OrderUtil.getOrderNoByAtomic();
		this.orderPrice = pay.getAmount();
		this.payPrice = yioSeller.getAmount();
		this.payStatus = "未支付";
		this.payType = payType;
		this.payFormat = payFormat;
		this.payQr = pay.getAppId();
		this.redirectUrl = pay.getNotifyUrl();
		this.extension = pay.getOrderId();
		this.createdAt = new Date();
		this.updatedAt = updatedAt;
		if (StringUtils.isBlank(pay.getTimeExpire())) {
			this.deletedAt = DateUtils.addDateMinute(this.getCreatedAt(),5);
		}else {
			this.deletedAt = DateUtils.parseDate(pay.getTimeExpire());
		}
		this.sellerId = yioSeller.getId();
		this.userId = yioSeller.getUserId();
		this.type = 1;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}


	public void setPayType(String payType){
		this.payType=payType;
	}

	public String getPayType(){
		return payType;
	}

	public void setPayFormat(String payFormat){
		this.payFormat=payFormat;
	}

	public String getPayFormat(){
		return payFormat;
	}

	public void setPayQr(String payQr){
		this.payQr=payQr;
	}

	public String getPayQr(){
		return payQr;
	}

	public void setRedirectUrl(String redirectUrl){
		this.redirectUrl=redirectUrl;
	}

	public String getRedirectUrl(){
		return redirectUrl;
	}

	public void setExtension(String extension){
		this.extension=extension;
	}

	public String getExtension(){
		return extension;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt=createdAt;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(Date updatedAt){
		this.updatedAt=updatedAt;
	}

	public Date getUpdatedAt(){
		return updatedAt;
	}

	public void setDeletedAt(Date deletedAt){
		this.deletedAt=deletedAt;
	}

	public Date getDeletedAt(){
		return deletedAt;
	}

	public void setSellerId(Integer sellerId){
		this.sellerId=sellerId;
	}

	public Integer getSellerId(){
		return sellerId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

}
