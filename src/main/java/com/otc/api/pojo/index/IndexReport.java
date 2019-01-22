package com.otc.api.pojo.index;

import com.otc.api.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class IndexReport {

    private Date date;

    private String dateTime;

    private BigDecimal amount;

    public String getDateTime() {
        return DateUtils.getDateFromString2(date);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
