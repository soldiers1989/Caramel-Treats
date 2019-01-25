package com.otc.api.domain;

import java.util.Date;
public class YioSysUser {

	//
	private Integer id;
	//
	private String username;
	//
	private String password;
	//
	private String token;
	//
	private Date loginTime;
	//
	private Date createTime;
	//1:管理员 2:业务系统 3:客服 4:财务
	private Integer authority;
	//业务系统 id
	private Integer serverId;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
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

	public void setToken(String token){
		this.token=token;
	}

	public String getToken(){
		return token;
	}

	public void setLoginTime(Date loginTime){
		this.loginTime=loginTime;
	}

	public Date getLoginTime(){
		return loginTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setAuthority(Integer authority){
		this.authority=authority;
	}

	public Integer getAuthority(){
		return authority;
	}

	public void setServerId(Integer serverId){
		this.serverId=serverId;
	}

	public Integer getServerId(){
		return serverId;
	}

}
