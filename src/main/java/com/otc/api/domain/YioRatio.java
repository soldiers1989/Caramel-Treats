package com.otc.api.domain;

import java.math.BigDecimal;

public class YioRatio {

	//
	private Integer id;
	//
	private BigDecimal ratio;

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
}
