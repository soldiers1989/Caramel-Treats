package com.otc.api.domain;

import java.math.BigDecimal;
import java.util.Date;
public class YioBill {

	//
	private Integer id;
	//流水
	private BigDecimal stream;
	//
	private Integer userId;
	//
	private Integer sellerId;
	//
	private BigDecimal reward;
	//
	private Date createAt;
	//
	private String createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getStream() {
		return stream;
	}

	public void setStream(BigDecimal stream) {
		this.stream = stream;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
