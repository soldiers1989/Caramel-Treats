package com.otc.api.domain;

public class YioPayType {
	//
	private String id;
	//支付类型编号
	private Integer payType;
	//支付类型名称
	private String name;
	//所属支付类型，如支付宝红包属于支付宝
	private String parentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
}
