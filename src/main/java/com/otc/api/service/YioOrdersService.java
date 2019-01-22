package com.otc.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.otc.api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioOrders;
import com.otc.api.mapper.YioOrdersMapper;
@Service
public class YioOrdersService {

	@Autowired
	private YioOrdersMapper yioOrdersMapper;

	 public List<YioOrders> getAll() {
		return yioOrdersMapper.findAll();
	}

	public void today(String shopname,BigDecimal minPrice,BigDecimal maxPrice,String username){
		Date date=new Date();
		Date date1=DateUtils.getStartDay(date);
		Date date2=DateUtils.getEndDay(date);
		BigDecimal a=yioOrdersMapper.findOrderPriceByDate(date1,date2,shopname,minPrice,maxPrice,username);
		int b=yioOrdersMapper.findOrderCountByDate(date1,date2);
		BigDecimal c=yioOrdersMapper.findPayPriceByDate(date1,date2);
		int d=yioOrdersMapper.findPayCountByDate(date1,date2);
		BigDecimal e=yioOrdersMapper.getPriceByDate(date1,date2);
		int f=yioOrdersMapper.getCountByDate(date1,date2);

	}
}
