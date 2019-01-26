package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioAccount {

	//
	private Integer id;
	//
	private BigDecimal amount;
	//
	private Integer sellerId;
	//
	private Integer userId;
	//
	private BigDecimal totalStream;
	//
	private Date createAt;
	//
	private Date updateAt;
	//
	private Integer version;
	//代币数量
	private BigDecimal token;
	//冻结金额
	private BigDecimal frozen;
	//总额度
	private BigDecimal total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalStream() {
		return totalStream;
	}

	public void setTotalStream(BigDecimal totalStream) {
		this.totalStream = totalStream;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BigDecimal getToken() {
		return token;
	}

	public void setToken(BigDecimal token) {
		this.token = token;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
