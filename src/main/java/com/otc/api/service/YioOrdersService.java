package com.otc.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.domain.YioShop;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.mapper.YioWithdrawMapper;
import com.otc.api.pojo.index.Index;
import com.otc.api.pojo.index.IndexReport;
import com.otc.api.pojo.order.OrderList;
import com.otc.api.pojo.order.OrderReport;
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
		}else {
			shop = yioShopMapper.findById(shop.getId());
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


	public OrderReport report(Integer id,YioShop shop,Date start,Date end,Integer type,String orderNo,String serverNo,String userName){
		OrderReport report = new OrderReport();
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}else {
			shop = yioShopMapper.findById(shop.getId());
		}
		if (type.equals(1)){
			start = DateUtils.getStartDay(DateUtils.startDate(new Date()));
			end = DateUtils.getEndDay(DateUtils.startDate(new Date()));
		}else if (type.equals(2)){
			start = DateUtils.getStartDay(DateUtils.startDate(DateUtils.addDate(new Date(),-1)));
			end = DateUtils.getEndDay(DateUtils.startDate(DateUtils.addDate(new Date(),-1)));
		}else if (type.equals(3)){
			start = DateUtils.getStartDay(DateUtils.startDate(DateUtils.addDate(new Date(),-7)));
			end = DateUtils.getEndDay(DateUtils.startDate(new Date()));
		}else if (type.equals(4)){
			start = DateUtils.getStartDay(DateUtils.startDate(DateUtils.addDate(new Date(),-30)));
			end = DateUtils.getEndDay(DateUtils.startDate(new Date()));
		}else {
			start = DateUtils.getStartDay(start);
			end = DateUtils.getEndDay(end);
		}
		report.setTotal(yioOrdersMapper.querySumOrderPrice(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setTotalCount(yioOrdersMapper.queryCountOrderPrice(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setFinishTotal(yioOrdersMapper.querySumOrderPriceAndStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setFinishCount(yioOrdersMapper.queryCountOrderPriceAndStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setInTotal(yioOrdersMapper.querySumOrderPriceAndStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setInCount(yioOrdersMapper.queryCountOrderPriceAndStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		return report;
	}

	public PageInfo<OrderList> list(Integer id, YioShop shop, Date start, Date end, Integer type, String orderNo, String serverNo, String userName, Integer status,Integer page,Integer size){
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}else {
			shop = yioShopMapper.findById(shop.getId());
		}
		if (type.equals(1)){
			start = DateUtils.getStartDay(DateUtils.startDate(new Date()));
			end = DateUtils.getEndDay(DateUtils.startDate(new Date()));
		}else if (type.equals(2)){
			start = DateUtils.getStartDay(DateUtils.startDate(DateUtils.addDate(new Date(),-1)));
			end = DateUtils.getEndDay(DateUtils.startDate(DateUtils.addDate(new Date(),-1)));
		}else if (type.equals(3)){
			start = DateUtils.getStartDay(DateUtils.startDate(DateUtils.addDate(new Date(),-7)));
			end = DateUtils.getEndDay(DateUtils.startDate(new Date()));
		}else if (type.equals(4)){
			start = DateUtils.getStartDay(DateUtils.startDate(DateUtils.addDate(new Date(),-30)));
			end = DateUtils.getEndDay(DateUtils.startDate(new Date()));
		}else {
			start = DateUtils.getStartDay(start);
			end = DateUtils.getEndDay(end);
		}
		if (status ==null || status.equals(0)){
			status = null;
		}
		PageHelper.startPage(page,size);
		List<OrderList> orderLists = yioOrdersMapper.query(status,shop.getAppId(),start,end,orderNo,serverNo,userName);
		PageInfo<OrderList> info = new PageInfo<>(orderLists);
		return info;
	}

}
