package com.otc.api.domain;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;
public class YioSysSettle {

	//
	private Integer id;
	//订单号
	private String orderNo;
	//付款人姓名
	private String name;
	//清算金额
	private BigDecimal amount;
	//收款账户
	private String bankCard;
	//收款人
	private String bankName;
	//开户行
	private String bankDeposit;
	//1:待确认 2:已确认 3:驳回
	private Integer status;
	//
	private Date createTime;
	//
	private String createDate;

	private Integer userId;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setBankCard(String bankCard){
		this.bankCard=bankCard;
	}

	public String getBankCard(){
		return bankCard;
	}

	public void setBankName(String bankName){
		this.bankName=bankName;
	}

	public String getBankName(){
		return bankName;
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

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public String getCreateDate() {
		return DateUtils.getDateFromString(this.createTime);
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
