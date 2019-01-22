package com.otc.api.domain;

import java.util.Date;
public class YioShop {

	//
	private Integer id;
	//商户名称
	private String name;
	//私钥
	private String privateKey;
	//
	private Date createAt;
	//商户appid
	private String appId;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setPrivateKey(String privateKey){
		this.privateKey=privateKey;
	}

	public String getPrivateKey(){
		return privateKey;
	}

	public void setCreateAt(Date createAt){
		this.createAt=createAt;
	}

	public Date getCreateAt(){
		return createAt;
	}

	public void setAppId(String appId){
		this.appId=appId;
	}

	public String getAppId(){
		return appId;
	}

}
