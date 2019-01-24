package com.otc.api.pojo.detail;

import java.math.BigDecimal;

public class DetailReport {

    private BigDecimal inTotal;
    private Integer inCount;
    private BigDecimal outTotal;
    private Integer outCount;

    public BigDecimal getInTotal() {
        return inTotal;
    }

    public void setInTotal(BigDecimal inTotal) {
        this.inTotal = inTotal;
    }

    public Integer getInCount() {
        return inCount;
    }

    public void setInCount(Integer inCount) {
        this.inCount = inCount;
    }

    public BigDecimal getOutTotal() {
        return outTotal;
    }

    public void setOutTotal(BigDecimal outTotal) {
        this.outTotal = outTotal;
    }

    public Integer getOutCount() {
        return outCount;
    }

    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }
}
