package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;

public class YioSelf {

	//自检id
	private Integer id;
	//支付类型 1：支付宝 2：微信 3： 红包 4：当面付
	private Integer payType;
	//交易员昵称
	private String traderName;
	//交易员账号
	private String traderAccno;
	//自检状态 0：待检测 1: 未通过 2： 已通过
	private Integer selfCheckStatus;
	//账户状态 1：冻结 2：解冻
	private Integer accountStatus;
	//支付账户id
	private String sellerId;
	//订单id
	private String orderId;
	//创建时间
	private BigDecimal amount;
	

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private Date createDate;
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}


	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setPayType(Integer payType){
		this.payType=payType;
	}

	public Integer getPayType(){
		return payType;
	}

	public void setTraderName(String traderName){
		this.traderName=traderName;
	}

	public String getTraderName(){
		return traderName;
	}

	public void setTraderAccno(String traderAccno){
		this.traderAccno=traderAccno;
	}

	public String getTraderAccno(){
		return traderAccno;
	}

	public void setSelfCheckStatus(Integer selfCheckStatus){
		this.selfCheckStatus=selfCheckStatus;
	}

	public Integer getSelfCheckStatus(){
		return selfCheckStatus;
	}

	public void setAccountStatus(Integer accountStatus){
		this.accountStatus=accountStatus;
	}

	public Integer getAccountStatus(){
		return accountStatus;
	}

}
