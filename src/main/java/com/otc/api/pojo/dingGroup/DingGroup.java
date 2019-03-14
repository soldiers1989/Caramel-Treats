package com.otc.api.pojo.dingGroup;

public class DingGroup {

    //
    private Integer id;
    //
    private Integer[] sellerId;
    //
    private Integer dingGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer[] sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getDingGroupId() {
        return dingGroupId;
    }

    public void setDingGroupId(Integer dingGroupId) {
        this.dingGroupId = dingGroupId;
    }
}
