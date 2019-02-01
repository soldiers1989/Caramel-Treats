package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioAccountDetail {

	//
	private Integer id;
	//
	private Integer accountId;
	//
	private BigDecimal amount;
	//1:入 2:出
	private Integer inOut;
	//业务id
	private Integer serviceId;
	//
	private Integer userId;
	//
	private Integer sellerId;
	//
	private Date createAt;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setAccountId(Integer accountId){
		this.accountId=accountId;
	}

	public Integer getAccountId(){
		return accountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setInOut(Integer inOut){
		this.inOut=inOut;
	}

	public Integer getInOut(){
		return inOut;
	}

	public void setServiceId(Integer serviceId){
		this.serviceId=serviceId;
	}

	public Integer getServiceId(){
		return serviceId;
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

	public void setCreateAt(Date createAt){
		this.createAt=createAt;
	}

	public Date getCreateAt(){
		return createAt;
	}

}
