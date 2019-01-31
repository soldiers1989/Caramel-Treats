package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioExceptionOrders {

	//
	private Integer id;
	//
	private Integer sellerId;
	//
	private Integer userId;
	//
	private Date createdAt;
	//
	private BigDecimal amount;

	private Integer status;//1:未处理  2已处理

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
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

	public void setCreatedAt(Date createdAt){
		this.createdAt=createdAt;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
