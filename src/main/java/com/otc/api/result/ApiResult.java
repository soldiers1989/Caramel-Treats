package com.otc.api.result;

public class ApiResult<T> {
	
	private Header header;
	
	private T data;

	public ApiResult(){
		
	}
	
	public ApiResult(T data){
		this.data=data;
	}
	
	
	public ApiResult(Integer code){
		Header header = new Header();
		header.setCode(code);
		this.header=header;
	}
	
	public ApiResult(Integer code,String message){
		Header header = new Header();
		header.setCode(code);
		header.setMessage(message);
		this.header=header;
	}
	
	public ApiResult(Integer code,T data){
		Header header = new Header();
		header.setCode(code);
		this.header=header;
		this.data=data;
	}
	
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
