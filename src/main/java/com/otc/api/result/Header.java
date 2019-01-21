package com.otc.api.result;

public class Header {

	private Integer code;

	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
		if (code == 0) {
			this.message = "获取成功";
		} else if (code == 100) {
			this.message = "数据异常";
		} else if (code == 10001) {
			this.message = "用户已注册";
		} else if (code == 10002) {
			this.message = "两次密码不一致";
		} else if (code == 10003) {
			this.message = "用户不存在";
		} else if (code == 10004) {
			this.message = "密码不正确";
		} else if (code == 10005) {
			this.message = "旧密码不正确";
		} else if (code == 10006) {
			this.message = "交易密码错误";
		} else if (code == 10007) {
			this.message = "请设置交易密码";
		} else if (code == 20001) {
			this.message = "订单等不及飞走了";
		} else if (code == 40001) {
			this.message = "用户未登录";
		} else if (code == 40002) {
			this.message = "用户令牌错误";
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
