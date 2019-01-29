package com.otc.api.domain;

import java.math.BigDecimal;

public class YioQr {

	//
	private Integer id;
	//
	private BigDecimal amount;
	//
	private Integer sellerId;
	//
	private Integer userId;
	//1通过 2 未通过
	private Integer status;
	//
	private String fileUrl;
	//1支付宝 2微信
	private Integer type;

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

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setFileUrl(String fileUrl){
		this.fileUrl=fileUrl;
	}

	public String getFileUrl(){
		return fileUrl;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

}
