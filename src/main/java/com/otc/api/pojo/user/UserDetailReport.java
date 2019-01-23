package com.otc.api.pojo.user;

import java.math.BigDecimal;

public class UserDetailReport {

    private BigDecimal recharge;//充值总额
    private Integer rechargeCount;//充值笔数
    private BigDecimal withdraw;//提现总额
    private Integer withdrawCount;//提现总额
    private BigDecimal reward;//收益

    public BigDecimal getRecharge() {
        if (recharge==null)
            return new BigDecimal(0);
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getWithdraw() {
        if (withdraw==null)
            return new BigDecimal(0);
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }

    public BigDecimal getReward() {
        if (reward==null)
            return new BigDecimal(0);
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public Integer getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public Integer getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(Integer withdrawCount) {
        this.withdrawCount = withdrawCount;
    }
}
