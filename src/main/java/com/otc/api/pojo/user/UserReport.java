package com.otc.api.pojo.user;

public class UserReport {

    private Integer serviceCount;//业务系统
    private Integer userCount;//交易员数量
    private Integer work;//正在接单
    private Integer ali;//支付宝用户
    private Integer wei;//微信用户
    private Integer aliWork;//支付宝用户
    private Integer weiWork;//微信用户

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Integer getAli() {
        return ali;
    }

    public void setAli(Integer ali) {
        this.ali = ali;
    }

    public Integer getWei() {
        return wei;
    }

    public void setWei(Integer wei) {
        this.wei = wei;
    }

    public Integer getAliWork() {
        return aliWork;
    }

    public void setAliWork(Integer aliWork) {
        this.aliWork = aliWork;
    }

    public Integer getWeiWork() {
        return weiWork;
    }

    public void setWeiWork(Integer weiWork) {
        this.weiWork = weiWork;
    }
}
