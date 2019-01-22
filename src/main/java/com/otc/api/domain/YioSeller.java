package com.otc.api.domain;

import java.util.Date;
public class YioSeller {

	//
	private Integer id;
	//名称
	private String name;
	//二维码图片
	private String url;
	//1:支付宝 2:微信
	private Integer type;
	//用户id
	private Integer userId;
	//
	private Date createAt;
	//
	private String username;
	//
	private String qr;

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

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrl(){
		return url;
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

	public void setCreateAt(Date createAt){
		this.createAt=createAt;
	}

	public Date getCreateAt(){
		return createAt;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getUsername(){
		return username;
	}

	public void setQr(String qr){
		this.qr=qr;
	}

	public String getQr(){
		return qr;
	}

}
