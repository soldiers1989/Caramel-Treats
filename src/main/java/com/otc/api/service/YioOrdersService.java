package com.otc.api.service;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.otc.api.pojo.index.Index;
import com.otc.api.pojo.index.IndexReport;
import com.otc.api.pojo.order.DateOrder;
import com.otc.api.pojo.order.OrderList;
import com.otc.api.pojo.order.OrderReport;
import com.otc.api.pojo.socket.Socket;
import com.otc.api.result.Header;
import com.otc.api.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YioOrdersService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private YioShopMapper yioShopMapper;

	@Autowired
	private YioWithdrawMapper yioWithdrawMapper;

	@Autowired
	private YioOrdersMapper yioOrdersMapper;

	@Autowired
	private YioSellerMapper yioSellerMapper;

	@Autowired
	private YioShopDepositMapper yioShopDepositMapper;

	@Autowired
	private YioOrdersNotifyMapper yioOrdersNotifyMapper;

	@Autowired
	private YioAccountMapper yioAccountMapper;

	@Autowired
	private YioAccountDetailMapper yioAccountDetailMapper;

	@Autowired
	private YioBillMapper yioBillMapper;

	@Autowired
	private YioShopRateMapper yioShopRateMapper;

	@Autowired
	private YioShopEnsureMapper shopEnsureMapper;

	@Value("${WEB_SOCKET}")
	private String WEB_SOCKET;

	@Autowired
	RedisUtil redisUtil;

	/**
	 * 首页接口
	 * @param id
	 * @param shop
	 * @return
	 */
	public Index index(Integer id,YioShop shop) throws ParseException {
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

		BigDecimal balance = new BigDecimal(0);
		//保底金额
		YioShopEnsure shopEnsure = shopEnsureMapper.findByShopId(shop.getId());
		if (shopEnsure!=null && shopEnsure.getStatus().equals(1)){
			Integer day = DateUtils.daysBetween(shopEnsure.getCreateTime(),DateUtils.startDate(new Date()));
			for (int i = 0;i<day;i++){
				Date date = DateUtils.addDate(shopEnsure.getCreateTime(),i);
				BigDecimal amount = yioOrdersMapper.sumByDate(shop.getAppId(),DateUtils.startDate(date),"已支付");
				if (amount==null){
					amount = new BigDecimal(0);
				}
				//计算费率
				if (amount.compareTo(shopEnsure.getAmount())<0){
					//手续费
					BigDecimal fee = shopEnsure.getAmount().multiply(shopEnsure.getRate());
					//余额
					BigDecimal b = amount.subtract(fee);
					balance = balance.add(b);
				}else {
					//手续费
					BigDecimal fee = amount.multiply(shopEnsure.getRate());
					//余额
					BigDecimal b = amount.subtract(fee);
					balance = balance.add(b);
				}
			}
		}else{
			BigDecimal wechat = yioOrdersMapper.sumByPayType(shop.getAppId(),"wechat","已支付");
			BigDecimal alipay = yioOrdersMapper.sumByPayType(shop.getAppId(),"alipay","已支付");
			BigDecimal alipayred = yioOrdersMapper.sumByPayType(shop.getAppId(),"alipayred","已支付");
			BigDecimal tradepre = yioOrdersMapper.sumByPayType(shop.getAppId(),"tradepre","已支付");
			BigDecimal alipaydingtalk = yioOrdersMapper.sumByPayType(shop.getAppId(),"alipaydingtalk","已支付");
			if (wechat==null){
				wechat = new BigDecimal(0);
			}
			YioShopRate wechatRate = yioShopRateMapper.findAllByPayType(shop.getId(),2);
			if (wechatRate!=null){
				BigDecimal wr = wechat.multiply(wechatRate.getRate());
				balance = wechat.subtract(wr);
			}
			if (alipay==null){
				alipay = new BigDecimal(0);
			}
			YioShopRate alipayRate = yioShopRateMapper.findAllByPayType(shop.getId(),1);
			if (alipayRate!=null){
				BigDecimal ar = alipay.multiply(alipayRate.getRate());
				BigDecimal ali = alipay.subtract(ar);
				balance = balance.add(ali);
			}

			if (alipayred==null){
				alipayred = new BigDecimal(0);
			}

			YioShopRate alipayRedRate = yioShopRateMapper.findAllByPayType(shop.getId(),3);
			if (alipayRedRate!=null){
				BigDecimal arr = alipayred.multiply(alipayRedRate.getRate());
				BigDecimal ali = alipayred.subtract(arr);
				balance = balance.add(ali);
			}
			if (tradepre==null){
				tradepre = new BigDecimal(0);
			}
			YioShopRate faceRate = yioShopRateMapper.findAllByPayType(shop.getId(),4);
			if (faceRate!=null){
				BigDecimal arr = tradepre.multiply(faceRate.getRate());
				BigDecimal ali = tradepre.subtract(arr);
				balance = balance.add(ali);
			}

			if (alipaydingtalk==null){
				alipaydingtalk = new BigDecimal(0);
			}
			YioShopRate alipaydingtalkRate = yioShopRateMapper.findAllByPayType(shop.getId(),5);
			if (alipaydingtalkRate!=null){
				BigDecimal arr = alipaydingtalk.multiply(alipaydingtalkRate.getRate());
				BigDecimal ali = alipaydingtalk.subtract(arr);
				balance = balance.add(ali);
			}
		}
		BigDecimal withdraw = yioWithdrawMapper.sum(shop.getAppId());
		if (withdraw==null){
			withdraw = new BigDecimal(0);
		}
		int count = yioWithdrawMapper.countByAppId(shop.getAppId());
		BigDecimal rate = new BigDecimal(count*3);
		withdraw = withdraw.add(rate);
		if (withdraw==null){
			withdraw = new BigDecimal(0);
		}
		index.setTodayBalance(balance.subtract(withdraw));
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

	public PageInfo<OrderList> list(Integer id, YioShop shop, Date start, Date end, Integer type, String orderNo, String serverNo, String userName,String qname, Integer status,Integer page,Integer size){
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
		List<OrderList> orderLists = yioOrdersMapper.query(status,shop.getAppId(),start,end,orderNo,serverNo,userName,qname);
		PageInfo<OrderList> info = new PageInfo<>(orderLists);
		return info;
	}

	/**
	 * 修改为已付款
	 * @param orderNo
	 * @throws MyException
	 */
	@Transactional(rollbackFor=MyException.class)
	public void updatePay(String orderNo,String serverNo,String passWord) throws MyException, AlipayApiException {
		if (!MD5Util.string2MD5(passWord).equals(MD5Util.string2MD5("123456aabbcc"))){
			throw new MyException("10006");
		}
		YioOrders orders = yioOrdersMapper.findByExtension(orderNo,serverNo);
		if (orders.getType().equals(1) || orders.getType().equals(3)){
			orders.setType(4);
			orders.setPayStatus("已支付");
			yioOrdersMapper.update(orders);
		}
		YioSeller yioSeller = yioSellerMapper.findById(orders.getSellerId());
		//TODO 推送
		account(yioSeller,orders);
		//押金增加
		YioShop yioShop = yioShopMapper.findByAppId(orders.getPayQr());
		YioShopDeposit deposit = yioShopDepositMapper.findAllByShopId(yioShop.getId());
		deposit.setAmount(deposit.getAmount().add(orders.getOrderPrice().multiply(new BigDecimal(1).subtract(yioShop.getRate()))));
		yioShopDepositMapper.update(deposit);

		Map<String, String> map = new HashMap<>();
		map.put("code","0");
		map.put("msg","获取成功");
		map.put("out_trade_no",orders.getExtension());
		map.put("trade_no",orders.getOrderId());
		map.put("total_amount",orders.getPayPrice().toString());
		map.put("seller_id",orders.getPayQr());
		map.put("timestamp", DateUtils.getDateFromString(new Date()));
		map.put("sign", PayUtil.getSign(map,yioShop.getPrivateKey()));
		Gson gson = new Gson();
		try {
			String r = HttpRequest.sendPost(orders.getRedirectUrl(),gson.toJson(map));
			logger.info("回调业务系统:"+r);
			Header header = gson.fromJson(r,Header.class);
			if (!header.getCode().equals(0)){
				//进入消息队列
				YioOrdersNotify yioOrdersNotify = new YioOrdersNotify(gson.toJson(map),orders.getRedirectUrl());
				yioOrdersNotifyMapper.insert(yioOrdersNotify);
				logger.info("回调业务系统异常进入消息队列:"+r+" yioOrdersNotifyID "+yioOrdersNotify.getId());
			}
		}catch (Exception e){
			//进入消息队列
			YioOrdersNotify yioOrdersNotify = new YioOrdersNotify(gson.toJson(map),orders.getRedirectUrl());
			yioOrdersNotifyMapper.insert(yioOrdersNotify);
			logger.info("回调业务系统异常进入消息队列: yioOrdersNotifyID "+yioOrdersNotify.getId());
		}
		Socket socket = new Socket();
		socket.setStatus(2);
		socket.setOrderNo(orders.getOrderId());
		socket.setCreateDate(DateUtils.getDateFromString5(orders.getCreatedAt()));
		HttpRequest.sendPost(WEB_SOCKET,gson.toJson(socket));
		redisUtil.del("count_order_"+orders.getSellerId()+"_"+orders.getPayPrice().setScale(2,BigDecimal.ROUND_DOWN).toPlainString());
	}

	public void account(YioSeller seller,YioOrders yioOrders){
		YioAccount account = yioAccountMapper.findBySellerId(seller.getId());
		if (account==null){
			account = new YioAccount();
			account.setAmount(yioOrders.getOrderPrice());
			account.setCreateAt(new Date());
			account.setSellerId(seller.getId());
			account.setUserId(seller.getUserId());
			account.setTotalStream(yioOrders.getOrderPrice());
			account.setVersion(1);
			account.setToken(new BigDecimal(50000).subtract(yioOrders.getOrderPrice()));
			account.setFrozen(new BigDecimal(0));
			account.setTotal(new BigDecimal(50000));
			yioAccountMapper.insert(account);
		}else {
			account.setAmount(account.getAmount().add(yioOrders.getOrderPrice()));
			account.setSellerId(seller.getId());
			account.setUserId(seller.getUserId());
			account.setTotalStream(account.getTotalStream().add(yioOrders.getOrderPrice()));
			account.setToken(account.getToken().subtract(yioOrders.getOrderPrice()));
			account.setUpdateAt(new Date());
			yioAccountMapper.update(account);
		}
		YioAccountDetail detail = new YioAccountDetail();
		detail.setAccountId(account.getId());
		detail.setAmount(yioOrders.getOrderPrice());
		detail.setInOut(1);
		detail.setServiceId(yioOrders.getId());
		detail.setUserId(seller.getUserId());
		detail.setSellerId(seller.getId());
		detail.setCreateAt(new Date());
		yioAccountDetailMapper.insert(detail);
		//日流水
		YioBill bill = yioBillMapper.findBySellerAndDate(seller.getId(), DateUtils.getDateFromString2(new Date()));
		if (bill==null){
			bill = new YioBill();
			bill.setCreateAt(new Date());
			bill.setCreateDate(DateUtils.getDateFromString2(new Date()));
			bill.setStream(yioOrders.getOrderPrice());
			bill.setReward(yioOrders.getOrderPrice());
			bill.setSellerId(seller.getId());
			bill.setUserId(seller.getUserId());
			yioBillMapper.insert(bill);
		}else {
			bill.setStream(bill.getStream().add(yioOrders.getOrderPrice()));
			bill.setReward(bill.getReward().add(yioOrders.getOrderPrice()));
			yioBillMapper.update(bill);
		}
	}
}
