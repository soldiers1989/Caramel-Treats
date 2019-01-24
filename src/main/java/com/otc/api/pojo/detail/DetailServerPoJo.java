package com.otc.api.pojo.detail;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class DetailServerPoJo {
    private Integer id;
    private Date createdAt;
    private String createDate;
    private String orderNo;//支付订单号
    private String serverNo;//业务订单号
    private Integer type;//类型 1充值 2 提现
    private String username;//交易员账户
    private String serverName;//业务账户
    private BigDecimal amount;//金额

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
        return DateUtils.getDateFromString(this.createdAt);
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getServerNo() {
        return serverNo;
    }

    public void setServerNo(String serverNo) {
        this.serverNo = serverNo;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
