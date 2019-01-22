package com.otc.api.domain;

import java.util.Date;
public class YioUser {

	//
	private Integer id;
	//
	private String username;
	//
	private String password;
	//
	private String lastLogin;
	//
	private Date createdAt;
	//
	private Date updatedAt;
	//
	private String token;
	//手机号
	private String phone;
	//
	private String tradingPwd;
	//1:普通用户 2: 保险柜
	private Integer authority;
	//1:上班 2:下班
	private Integer work;
	//
	private String invite;
	//
	private String inviter;

	private Integer status;

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

	public void setLastLogin(String lastLogin){
		this.lastLogin=lastLogin;
	}

	public String getLastLogin(){
		return lastLogin;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt=createdAt;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(Date updatedAt){
		this.updatedAt=updatedAt;
	}

	public Date getUpdatedAt(){
		return updatedAt;
	}

	public void setToken(String token){
		this.token=token;
	}

	public String getToken(){
		return token;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setTradingPwd(String tradingPwd){
		this.tradingPwd=tradingPwd;
	}

	public String getTradingPwd(){
		return tradingPwd;
	}

	public void setAuthority(Integer authority){
		this.authority=authority;
	}

	public Integer getAuthority(){
		return authority;
	}

	public void setWork(Integer work){
		this.work=work;
	}

	public Integer getWork(){
		return work;
	}

	public void setInvite(String invite){
		this.invite=invite;
	}

	public String getInvite(){
		return invite;
	}

	public void setInviter(String inviter){
		this.inviter=inviter;
	}

	public String getInviter(){
		return inviter;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
