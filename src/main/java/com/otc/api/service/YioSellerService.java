package com.otc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSeller;
import com.otc.api.mapper.YioSellerMapper;
@Service
public class YioSellerService {

	@Autowired
	private YioSellerMapper yioSellerMapper;

	 public List<YioSeller> getAll() {
		return yioSellerMapper.findAll();
	}
}
