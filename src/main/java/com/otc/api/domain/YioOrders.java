package com.otc.api.domain;

import java.util.Date;
public class YioOrders {

	//
	private Integer id;
	//
	private String orderId;
	//
	private double orderPrice;
	//
	private double payPrice;
	//
	private String payStatus;
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

	public void setOrderPrice(double orderPrice){
		this.orderPrice=orderPrice;
	}

	public double getOrderPrice(){
		return orderPrice;
	}

	public void setPayPrice(double payPrice){
		this.payPrice=payPrice;
	}

	public double getPayPrice(){
		return payPrice;
	}

	public void setPayStatus(String payStatus){
		this.payStatus=payStatus;
	}

	public String getPayStatus(){
		return payStatus;
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
