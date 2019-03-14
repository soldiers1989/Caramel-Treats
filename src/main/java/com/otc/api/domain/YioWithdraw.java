package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioWithdraw {

	//
	private Integer id;
	//开发者的应用ID
	private String appId;
	//提现金额
	private BigDecimal amount;
	//业务系统订单id
	private String orderId;
	//提现方式1:支付宝 2:微信3:银行卡
	private Integer payType;
	//
	private String timestamp;
	//提现人姓名
	private String name;
	//支付宝、微信二维码
	private String url;
	//银号卡号
	private String bankCard;
	//开户行
	private String bankDeposit;
	//
	private String notifyUrl;
	//提现订单号
	private String withdrawNo;
	//
	private Date createdAt;
	//
	private Integer userId;
	//
	private Integer sellerId;
	//
	private Date updatedAt;
	//1:提现中 2:已完成 3:处理中 4：驳回
	private Integer payStatus;
	//凭证url
	private String fileUrl;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setAppId(String appId){
		this.appId=appId;
	}

	public String getAppId(){
		return appId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setPayType(Integer payType){
		this.payType=payType;
	}

	public Integer getPayType(){
		return payType;
	}

	public void setTimestamp(String timestamp){
		this.timestamp=timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrl(){
		return url;
	}

	public void setBankCard(String bankCard){
		this.bankCard=bankCard;
	}

	public String getBankCard(){
		return bankCard;
	}

	public void setBankDeposit(String bankDeposit){
		this.bankDeposit=bankDeposit;
	}

	public String getBankDeposit(){
		return bankDeposit;
	}

	public void setNotifyUrl(String notifyUrl){
		this.notifyUrl=notifyUrl;
	}

	public String getNotifyUrl(){
		return notifyUrl;
	}

	public void setWithdrawNo(String withdrawNo){
		this.withdrawNo=withdrawNo;
	}

	public String getWithdrawNo(){
		return withdrawNo;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt=createdAt;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setSellerId(Integer sellerId){
		this.sellerId=sellerId;
	}

	public Integer getSellerId(){
		return sellerId;
	}

	public void setUpdatedAt(Date updatedAt){
		this.updatedAt=updatedAt;
	}

	public Date getUpdatedAt(){
		return updatedAt;
	}

	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}

	public Integer getPayStatus(){
		return payStatus;
	}

	public void setFileUrl(String fileUrl){
		this.fileUrl=fileUrl;
	}

	public String getFileUrl(){
		return fileUrl;
	}

}
