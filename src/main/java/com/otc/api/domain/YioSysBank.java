package com.otc.api.domain;

public class YioSysBank {

	//
	private Integer id;
	//银行卡号
	private String bankCard;
	//收款人
	private String name;
	//开户行
	private String bankDeposit;
	//1:使用中 2:禁用
	private Integer status;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setBankCard(String bankCard){
		this.bankCard=bankCard;
	}

	public String getBankCard(){
		return bankCard;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setBankDeposit(String bankDeposit){
		this.bankDeposit=bankDeposit;
	}

	public String getBankDeposit(){
		return bankDeposit;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

}
