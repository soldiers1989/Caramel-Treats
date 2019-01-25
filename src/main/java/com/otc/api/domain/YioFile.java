package com.otc.api.domain;

public class YioFile {

	//
	private Integer id;
	//
	private String name;
	//
	private String fileUrl;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setFileUrl(String fileUrl){
		this.fileUrl=fileUrl;
	}

	public String getFileUrl(){
		return fileUrl;
	}

}
