package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioShopDeposit {

	//
	private Integer id;
	//
	private BigDecimal amount;
	//
	private Integer shopId;
	//
	private Date createdAt;
	//
	private Date updatedAt;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setShopId(Integer shopId){
		this.shopId=shopId;
	}

	public Integer getShopId(){
		return shopId;
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

}
