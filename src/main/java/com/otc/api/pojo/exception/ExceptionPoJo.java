package com.otc.api.pojo.exception;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class ExceptionPoJo {

    private Integer id;
    private String username;
    private String payName;
    private BigDecimal amount;
    private Date createdAt;
    private String createDate;

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
}
