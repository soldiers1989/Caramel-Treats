package com.otc.api.service;

import java.util.Date;
import java.util.List;

import com.otc.api.domain.YioOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioRedLog;
import com.otc.api.mapper.YioRedLogMapper;
@Service
public class YioRedLogService {

	@Autowired
	private YioRedLogMapper yioRedLogMapper;

	public void create(Integer orderId) {
		YioRedLog yioRedLog = new YioRedLog();
		yioRedLog.setType(2);
		yioRedLog.setCreatedAt(new Date());
		yioRedLog.setOrderId(orderId);
		yioRedLogMapper.insert(yioRedLog);
	}
}
