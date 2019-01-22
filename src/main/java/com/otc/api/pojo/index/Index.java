package com.otc.api.pojo.index;

import java.math.BigDecimal;

public class Index {

    private BigDecimal todayBalance;//今日余额
    private Integer todayCount;//收入笔数
    private BigDecimal withdraw;//当前待提现额度
    private Integer withdrawCount;//待处理提现笔数
    private BigDecimal yesterdayBalance;//昨日收入
    private Integer yesterdayCount;//昨日收入笔数
    private BigDecimal yesterdayWithdraw;//昨日支出
    private Integer yesterdayWithdrawCount;//昨日支出笔数

    public BigDecimal getTodayBalance() {
        if (todayBalance==null)
            return new BigDecimal(0);
        return todayBalance;
    }

    public void setTodayBalance(BigDecimal todayBalance) {
        this.todayBalance = todayBalance;
    }

    public Integer getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Integer todayCount) {
        this.todayCount = todayCount;
    }

    public BigDecimal getWithdraw() {
        if (withdraw==null)
            return new BigDecimal(0);
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }

    public Integer getWithdrawCount() {
        return withdrawCount;
    }

    public void setWithdrawCount(Integer withdrawCount) {
        this.withdrawCount = withdrawCount;
    }

    public BigDecimal getYesterdayBalance() {
        if (yesterdayBalance==null)
            return new BigDecimal(0);
        return yesterdayBalance;
    }

    public void setYesterdayBalance(BigDecimal yesterdayBalance) {
        this.yesterdayBalance = yesterdayBalance;
    }

    public Integer getYesterdayCount() {
        return yesterdayCount;
    }

    public void setYesterdayCount(Integer yesterdayCount) {
        this.yesterdayCount = yesterdayCount;
    }

    public BigDecimal getYesterdayWithdraw() {
        if (yesterdayWithdraw==null)
            return new BigDecimal(0);
        return yesterdayWithdraw;
    }

    public void setYesterdayWithdraw(BigDecimal yesterdayWithdraw) {
        this.yesterdayWithdraw = yesterdayWithdraw;
    }

    public Integer getYesterdayWithdrawCount() {
        return yesterdayWithdrawCount;
    }

    public void setYesterdayWithdrawCount(Integer yesterdayWithdrawCount) {
        this.yesterdayWithdrawCount = yesterdayWithdrawCount;
    }
}
