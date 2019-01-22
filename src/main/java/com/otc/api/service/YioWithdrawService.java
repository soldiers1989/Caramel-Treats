package com.otc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioWithdraw;
import com.otc.api.mapper.YioWithdrawMapper;
@Service
public class YioWithdrawService {

	@Autowired
	private YioWithdrawMapper yioWithdrawMapper;

	 public List<YioWithdraw> getAll() {
		return yioWithdrawMapper.findAll();
	}
}
