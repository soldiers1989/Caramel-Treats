package com.otc.api.pojo.user;

import com.otc.api.util.StringUtils;

import java.math.BigDecimal;

public class UserList {

    private Integer id;
    private String username;//用户名
    private String name;//姓名
    private BigDecimal amount;//余额
    private BigDecimal reward;//收益
    private Integer work;//1:上班 2下班
    //账户状态
    private Integer status;//1 正常 2：冻结
    private String qname;

    //付款账户id
    private String userId;
    //付款账户状态
    private Integer payMentStatus;//1 正常 2：冻结
    
    public Integer getPayMentStatus() {
		return payMentStatus;
	}

	public void setPayMentStatus(Integer payMentStatus) {
		this.payMentStatus = payMentStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        if (StringUtils.isBlank(name))
            return "--";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        if (amount==null)
            return new BigDecimal(0);
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getReward() {
        if (reward==null)
            return new BigDecimal(0);
        return reward.divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }
}
