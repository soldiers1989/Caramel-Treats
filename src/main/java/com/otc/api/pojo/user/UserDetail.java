package com.otc.api.pojo.user;

import java.math.BigDecimal;

public class UserDetail {

    private Integer id;
    private Integer status;//交易员状态
    private Integer work;//接单状态
    private String username;//账号
    private String name;//姓名
    private BigDecimal amount;//账户余额
    private BigDecimal reward;//收益
    private BigDecimal withdraw;//待提现额度

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getReward() {
        if (reward==null)
            return new BigDecimal(0);
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public BigDecimal getWithdraw() {
        if (withdraw==null)
            return new BigDecimal(0);
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }
}
