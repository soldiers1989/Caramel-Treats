package com.otc.api.pojo.order;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class OrderWithdrawList {
    private Integer id;
    private Date createdAt;
    private String createDate;//
    private String orderId;//支付订单号
    private String extension;//业务订单号
    private BigDecimal orderPrice;//
    private BigDecimal payPrice;//
    private String username;
    private Integer type;//1:未到帐 2:已到帐 3:已过期 4:部帐 5:清算
    private String qname;
    //银行卡号
    private String bankNo;
    //操作人账号
    private String operateNo;
    
    public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getOperateNo() {
		return operateNo;
	}

	public void setOperateNo(String operateNo) {
		this.operateNo = operateNo;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreateDate() {
        return DateUtils.getDateFromString(createdAt);
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }
}
