package com.otc.api.pojo.order;

import java.math.BigDecimal;
import java.util.Date;

public class DateOrder {
    private Date date;
    private BigDecimal amount;

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
