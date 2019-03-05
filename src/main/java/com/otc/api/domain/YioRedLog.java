package com.otc.api.domain;

import java.util.Date;
public class YioRedLog {

	//
	private Integer id;
	//
	private Integer orderId;
	//
	private String orderNo;
	//
	private Date createdAt;
	//1:进入红包方法 2:点击支付
	private Integer type;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setOrderId(Integer orderId){
		this.orderId=orderId;
	}

	public Integer getOrderId(){
		return orderId;
	}

	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt=createdAt;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

}
