package com.otc.api.pojo.user;

public class UserPoJo {
    private String token;
    private String authority;//1:管理员 2:业务系统

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
