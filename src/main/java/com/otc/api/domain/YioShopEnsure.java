package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioShopEnsure {

	//
	private Integer id;
	//
	private Integer shopId;
	//
	private BigDecimal amount;
	//
	private BigDecimal rate;
	//
	private Date createTime;
	//0禁用 1启用
	private Integer status;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setShopId(Integer shopId){
		this.shopId=shopId;
	}

	public Integer getShopId(){
		return shopId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

}
