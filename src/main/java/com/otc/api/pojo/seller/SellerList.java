package com.otc.api.pojo.seller;

import java.math.BigDecimal;

public class SellerList {

    private Integer id;
    private String name;
    private String username;//用户名
    private Integer type;//1支付宝 2微信
    private BigDecimal amount;
    private Integer pant;//1 在线 2离线
    private String qname;
    private String bankName;
    private String cardNo;

    //账户状态 1:冻结 2： 解冻
    private Integer userStatus ;

    private Integer userGroup;//1:在组 2：不再组

    public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPant() {
        return pant;
    }

    public void setPant(Integer pant) {
        this.pant = pant;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getUserGroup() {
        if (userGroup==null){
            return 2;
        }else {
            return 1;
        }
    }

    public void setUserGroup(Integer userGroup) {
        this.userGroup = userGroup;
    }
}
