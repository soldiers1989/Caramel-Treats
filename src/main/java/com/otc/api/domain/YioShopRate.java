package com.otc.api.domain;

import java.math.BigDecimal;

public class YioShopRate {

	//
	private Integer id;
	//1:支付宝 2:微信 3:红包
	private Integer payType;
	//
	private BigDecimal rate;
	//
	private Integer shopId;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setPayType(Integer payType){
		this.payType=payType;
	}

	public Integer getPayType(){
		return payType;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setShopId(Integer shopId){
		this.shopId=shopId;
	}

	public Integer getShopId(){
		return shopId;
	}

}
