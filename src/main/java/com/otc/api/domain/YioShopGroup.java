package com.otc.api.domain;

public class YioShopGroup {

	//
	private Integer id;
	//
	private Integer shopId;
	//
	private Integer sysUserId;

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

	public void setSysUserId(Integer sysUserId){
		this.sysUserId=sysUserId;
	}

	public Integer getSysUserId(){
		return sysUserId;
	}

}
