package com.otc.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.otc.api.pojo.qr.QRCreate;
import com.otc.api.util.QRimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioQr;
import com.otc.api.mapper.YioQrMapper;
@Service
public class YioQrService {

	@Autowired
	private YioQrMapper yioQrMapper;

	@Value("${URL}")
	private String URL;

	public void create(QRCreate create) {
		for (String u :create.getUrl().split(",")){
			YioQr qr = new YioQr();
			qr.setStatus(2);
			qr.setType(2);
			qr.setAmount(new BigDecimal(QRimg.sample(URL+u)));
			qr.setUserId(create.getId());
			qr.setFileUrl(u);
			yioQrMapper.insert(qr);
		}
	}

	public List<YioQr> qrs(Integer id){
		List<YioQr> qrs = new ArrayList<>();
		List<YioQr> list = yioQrMapper.findAllByUserId(id);
		for (YioQr qr :list){
			qr.setFileUrl(URL+qr.getFileUrl());
			qrs.add(qr);
		}
		return qrs;
	}

	public void delete(Integer id){
		yioQrMapper.delete(yioQrMapper.findById(id));
	}
}
