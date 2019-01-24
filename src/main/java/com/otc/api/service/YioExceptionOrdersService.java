package com.otc.api.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.pojo.exception.ExceptionPoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioExceptionOrders;
import com.otc.api.mapper.YioExceptionOrdersMapper;
@Service
public class YioExceptionOrdersService {

	@Autowired
	private YioExceptionOrdersMapper yioExceptionOrdersMapper;

	public PageInfo<ExceptionPoJo> getAll(Date start,Date end,String username, Integer page, Integer size) {
		PageHelper.startPage(page,size);
		List<ExceptionPoJo> list = yioExceptionOrdersMapper.queryAllException(start,end,username);
		PageInfo<ExceptionPoJo> info = new PageInfo<>(list);
		return info;
	}
}
