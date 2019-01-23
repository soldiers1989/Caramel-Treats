package com.otc.api.domain;

public class YioPant {

	//
	private Integer id;
	//
	private Integer sellerId;
	//
	private long timestamp;
	//1:活着 2:死亡
	private Integer pant;
	//1支付宝 2微信
	private Integer type;
	//
	private Integer userId;

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

	public void setTimestamp(long timestamp){
		this.timestamp=timestamp;
	}

	public long getTimestamp(){
		return timestamp;
	}

	public void setPant(Integer pant){
		this.pant=pant;
	}

	public Integer getPant(){
		return pant;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getUserId(){
		return userId;
	}

}
