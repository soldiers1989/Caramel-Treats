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
	//
	private String username;
	//
	private String password;
	//
	private Date loginTime;
	//1:管理员 2:商户
	private Integer authority;
	//
	private String token;

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

	public void setUsername(String username){
		this.username=username;
	}

	public String getUsername(){
		return username;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return password;
	}

	public void setLoginTime(Date loginTime){
		this.loginTime=loginTime;
	}

	public Date getLoginTime(){
		return loginTime;
	}

	public void setAuthority(Integer authority){
		this.authority=authority;
	}

	public Integer getAuthority(){
		return authority;
	}

	public void setToken(String token){
		this.token=token;
	}

	public String getToken(){
		return token;
	}

}
