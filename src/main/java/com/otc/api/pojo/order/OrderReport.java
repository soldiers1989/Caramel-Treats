package com.otc.api.pojo.order;

import java.math.BigDecimal;

public class OrderReport {

    private BigDecimal total;//总额
    private Integer totalCount;
    private BigDecimal finishTotal;//成交额
    private Integer finishCount;
    private BigDecimal inTotal;//实收
    private Integer inCount;

    public BigDecimal getTotal() {
        if (total==null)
            return new BigDecimal(0);
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getFinishTotal() {
        if (finishTotal==null)
            return new BigDecimal(0);
        return finishTotal;
    }

    public void setFinishTotal(BigDecimal finishTotal) {
        this.finishTotal = finishTotal;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public BigDecimal getInTotal() {
        if (inTotal==null)
            return new BigDecimal(0);
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
}
