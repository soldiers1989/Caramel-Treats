package com.otc.api.pojo.pant;

public class Pant {
    private Integer device;//设备总数
    private Integer onLine;//在线
    private Integer dropLine;//掉线

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Integer getOnLine() {
        return onLine;
    }

    public void setOnLine(Integer onLine) {
        this.onLine = onLine;
    }

    public Integer getDropLine() {
        return dropLine;
    }

    public void setDropLine(Integer dropLine) {
        this.dropLine = dropLine;
    }
}
