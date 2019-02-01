package com.otc.api.domain;

public class YioOrdersNotify {

	//
	private Integer id;
	//
	private String redirect;
	//
	private Integer type;
	//
	private Integer version;
	//
	private String redirectUrl;

	public YioOrdersNotify() {
	}

	public YioOrdersNotify(String redirect, String redirectUrl) {
		this.redirectUrl = redirectUrl;
		this.redirect = redirect;
		this.type = 1;
		this.version = 0;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setRedirect(String redirect){
		this.redirect=redirect;
	}

	public String getRedirect(){
		return redirect;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setVersion(Integer version){
		this.version=version;
	}

	public Integer getVersion(){
		return version;
	}

	public void setRedirectUrl(String redirectUrl){
		this.redirectUrl=redirectUrl;
	}

	public String getRedirectUrl(){
		return redirectUrl;
	}

}
