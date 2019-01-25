package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioSysSettleFile {

	//
	private Integer id;
	//
	private String fileUrl;
	//备注
	private String remark;
	//应付金额
	private BigDecimal payAmount;
	//实付金额
	private BigDecimal amount;
	//
	private Date createTime;
	//
	private Integer settleId;
	//
	private String inFileUrl;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setFileUrl(String fileUrl){
		this.fileUrl=fileUrl;
	}

	public String getFileUrl(){
		return fileUrl;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setSettleId(Integer settleId){
		this.settleId=settleId;
	}

	public Integer getSettleId(){
		return settleId;
	}

	public void setInFileUrl(String inFileUrl){
		this.inFileUrl=inFileUrl;
	}

	public String getInFileUrl(){
		return inFileUrl;
	}

}
