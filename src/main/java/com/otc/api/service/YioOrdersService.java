package com.otc.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.otc.api.domain.YioShop;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.mapper.YioWithdrawMapper;
import com.otc.api.pojo.index.Index;
import com.otc.api.pojo.index.IndexReport;
import com.otc.api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioOrders;
import com.otc.api.mapper.YioOrdersMapper;
@Service
public class YioOrdersService {

	@Autowired
	private YioOrdersMapper yioOrdersMapper;

	@Autowired
	private YioShopMapper yioShopMapper;

	@Autowired
	private YioWithdrawMapper yioWithdrawMapper;


	/**
	 * 首页接口
	 * @param id
	 * @param shop
	 * @return
	 */
	public Index index(Integer id,YioShop shop){
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}
		Index index = new Index();
		index.setTodayBalance(yioOrdersMapper.sumByDate(shop.getAppId(),DateUtils.startDate(new Date()),"已支付"));
		index.setTodayCount(yioOrdersMapper.countByCreateDate(shop.getAppId(),DateUtils.startDate(new Date())));
		index.setWithdraw(yioWithdrawMapper.sumByStatus(shop.getAppId(),1));
		index.setWithdrawCount(yioWithdrawMapper.countByStatus(shop.getAppId(),1));
		index.setYesterdayBalance(yioOrdersMapper.sumByDate(shop.getAppId(),DateUtils.startDate(DateUtils.addDate(new Date(),-1)),"已支付"));
		index.setYesterdayCount(yioOrdersMapper.countByCreateDate(shop.getAppId(),DateUtils.startDate(DateUtils.addDate(new Date(),-1))));
		index.setYesterdayWithdraw(yioWithdrawMapper.sumByStatusAndDate(shop.getAppId(),2,DateUtils.startDate(DateUtils.addDate(new Date(),-1))));
		index.setYesterdayWithdrawCount(yioWithdrawMapper.countByStatusAndDate(shop.getAppId(),2,DateUtils.startDate(DateUtils.addDate(new Date(),-1))));
		return index;
	}

	/**
	 * 首页统计报表
	 * @param shop
	 * @param date
	 * @return
	 */
	public List<IndexReport> IndexReport(YioShop shop,Integer date,Integer id){
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}
		return yioOrdersMapper.report(shop.getAppId(),date);
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
