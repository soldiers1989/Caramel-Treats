package com.otc.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.otc.api.domain.*;
import com.otc.api.exception.MyException;
import com.otc.api.mapper.*;
import com.otc.api.pojo.order.OrderList;
import com.otc.api.pojo.order.OrderReport;
import com.otc.api.pojo.order.OrderWithdrawList;
import com.otc.api.pojo.socket.Socket;
import com.otc.api.pojo.withdraw.WithdrawPoJo;
import com.otc.api.result.ApiResult;
import com.otc.api.result.Header;
import com.otc.api.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YioWithdrawService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private YioWithdrawMapper yioWithdrawMapper;

	@Autowired
	private YioShopMapper yioShopMapper;

	@Autowired
	private YioShopGroupMapper yioShopGroupMapper;

	@Value("${PAY_URL}")
	private String PAY_URL;

	public OrderReport report(Integer id, YioShop shop, Date start, Date end, Integer type, String orderNo, String serverNo, String userName){
		OrderReport report = new OrderReport();
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}else if (shop.getAuthority().equals(2)){
			if (id!=0){
				shop = yioShopMapper.findById(id);
			}else {
				List<YioShopGroup> groups = yioShopGroupMapper.findBySysUserId(shop.getUserId());
				if (groups.size()>0){
					shop = yioShopMapper.findById(groups.get(0).getShopId());
				}
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
		report.setTotal(yioWithdrawMapper.querySumAmount(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setTotalCount(yioWithdrawMapper.queryCountAmount(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setFinishTotal(yioWithdrawMapper.querySumAmountByStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setFinishCount(yioWithdrawMapper.queryCountAmountByStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setInTotal(yioWithdrawMapper.querySumAmountByStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		report.setInCount(yioWithdrawMapper.queryCountAmountByStatus(shop.getAppId(),start,end,orderNo,serverNo,userName));
		return report;
	}

	public PageInfo<OrderWithdrawList> list(Integer id, YioShop shop, Date start, Date end, Integer type, String orderNo, String serverNo, String userName,String qname, Integer status, Integer page, Integer size){
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}else if (shop.getAuthority().equals(2)){
			if (id!=0){
				shop = yioShopMapper.findById(id);
			}else {
				List<YioShopGroup> groups = yioShopGroupMapper.findBySysUserId(shop.getUserId());
				if (groups.size()>0){
					shop = yioShopMapper.findById(groups.get(0).getShopId());
				}
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
		List<OrderWithdrawList> orderLists = yioWithdrawMapper.query(status,shop.getAppId(),start,end,orderNo,serverNo,userName,qname);
		PageInfo<OrderWithdrawList> info = new PageInfo<>(orderLists);
		return info;
	}

	public void create(WithdrawPoJo withdrawPoJo) throws MyException, AlipayApiException {
		YioShop shop =yioShopMapper.findById(withdrawPoJo.getShopId());
		//发起支付请求
		Map<String, String> map = new HashMap<>();
		map.put("appId",shop.getAppId());
		map.put("amount",withdrawPoJo.getAmount().toString());
		map.put("orderId",OrderUtil.getOrderNoByAtomic());
		map.put("payType",withdrawPoJo.getPayType().toString());
		map.put("timestamp",DateUtils.getDateFromString(new Date()));
		map.put("name",withdrawPoJo.getName());
		map.put("url","");
		map.put("bankCard",withdrawPoJo.getBankCard());
		map.put("bankDeposit",withdrawPoJo.getBankDeposit());
		map.put("notifyUrl","");
		map.put("version","1.0");
		map.put("sign", PayUtil.getSign(map,shop.getPrivateKey()));
		Gson gson = new Gson();
		String r = HttpRequest.sendPost(PAY_URL,gson.toJson(map));
		ApiResult result = gson.fromJson(r, ApiResult.class);
		if (!result.getHeader().getCode().equals(0)){
			throw new MyException("100");
		}
	}
}
