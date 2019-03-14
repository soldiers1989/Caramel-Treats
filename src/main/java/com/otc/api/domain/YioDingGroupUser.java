package com.otc.api.domain;

public class YioDingGroupUser {

	//
	private Integer id;
	//
	private Integer sellerId;
	//
	private Integer dingGroupId;

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

	public void setDingGroupId(Integer dingGroupId){
		this.dingGroupId=dingGroupId;
	}

	public Integer getDingGroupId(){
		return dingGroupId;
	}

}
