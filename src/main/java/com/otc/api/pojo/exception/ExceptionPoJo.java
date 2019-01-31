package com.otc.api.pojo.exception;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class ExceptionPoJo {

    private Integer id;
    private String username;
    private String payName;
    private BigDecimal amount;
    private String name;//姓名
    private Integer type;//1支付宝 2微信
    private Date createdAt;
    private String createDate;
    private Integer status;//1:未处理  2已处理


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

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
