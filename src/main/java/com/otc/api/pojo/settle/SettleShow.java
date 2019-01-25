package com.otc.api.pojo.settle;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class SettleShow {

    private Integer id;

    private Integer settleId;
    //订单号
    private String orderNo;
    //付款人姓名
    private String name;
    //应付金额
    private BigDecimal payAmount;
    //实付金额
    private BigDecimal amount;
    //收款账户
    private String bankCard;
    //收款人
    private String bankName;
    //开户行
    private String bankDeposit;
    //1:待确认 2:已确认 3:驳回
    private Integer status;
    //付款凭证
    private String fileUrl;
    //收款凭证
    private String inFileUrl;

    private Date createTime;

    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankDeposit() {
        return bankDeposit;
    }

    public void setBankDeposit(String bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getInFileUrl() {
        return inFileUrl;
    }

    public void setInFileUrl(String inFileUrl) {
        this.inFileUrl = inFileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return DateUtils.getDateFromString(this.createTime);
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
