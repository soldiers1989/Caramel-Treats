package com.otc.api.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.otc.api.domain.YioOrders;
import com.otc.api.domain.YioPant;
import com.otc.api.domain.YioSelf;
import com.otc.api.domain.YioSeller;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopRate;
import com.otc.api.exception.MyException;
import com.otc.api.mapper.YioAccountDetailMapper;
import com.otc.api.mapper.YioAccountMapper;
import com.otc.api.mapper.YioBillMapper;
import com.otc.api.mapper.YioExceptionOrdersMapper;
import com.otc.api.mapper.YioOrdersMapper;
import com.otc.api.mapper.YioOrdersNotifyMapper;
import com.otc.api.mapper.YioSelfMapper;
import com.otc.api.mapper.YioSellerMapper;
import com.otc.api.mapper.YioShopDepositMapper;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.mapper.YioShopRateMapper;
import com.otc.api.pojo.order.Notify;
import com.otc.api.pojo.order.OrderPoJo;
import com.otc.api.pojo.order.Pay;
import com.otc.api.pojo.user.UserList;
import com.otc.api.util.DateUtils;
import com.otc.api.util.FileUpload;
import com.otc.api.util.HttpRequest;
import com.otc.api.util.OrderUtil;
import com.otc.api.util.PayUtil;
@Service
public class YioSelfService {

	@Autowired
	private YioShopMapper yioShopMapper;
	@Autowired
	private YioSelfMapper yioSelfMapper;
	@Autowired
	private YioOrdersMapper yioOrdersMapper;
	@Autowired
	private YioSellerMapper yioSellerMapper;
	@Value("${NOTIFY_URL}")
	private String notify_url;
	@Value("${APPID}")
	private String appid;
	@Value("${PRIVATE_KEY}")
	private String private_key;
	@Value("${CHECK_URL}")
	private String check_url;
	
	private Logger logger = Logger.getLogger(getClass());
	 public List<YioSelf> getAll() {
		return yioSelfMapper.findAll();
	}
	 
	 /**
		 * 交易员自检列表
		 * @param username
		 * @param work
		 * @param page
		 * @param size
		 * @return
		 */
		public PageInfo<YioSelf> list(Integer payType,Integer checkStatus,String qname,Integer pageNo,Integer sizeNo,String transactionAccount){
			PageHelper.startPage(pageNo,sizeNo);
			List<YioSelf> lists = yioSelfMapper.queryForList(payType,checkStatus,qname,transactionAccount);
			PageInfo<YioSelf> info = new PageInfo<>(lists);
			return info;
		}
		
		/**
		 * 变更自检状态
		 * @param id
		 * @param accountStatus
		 * @return
		 */
		public int updateCheckStatus(Integer id,int accountStatus){
			return yioSelfMapper.updateOpenOrClose(id,accountStatus);
		}
		
		/**
		 * 根据订单号变更状态
		 * @param id
		 * @param accountStatus
		 * @return
		 */
		public int updateCheckStatusByOrder(Integer orderId,int accountStatus){
			return yioSelfMapper.updateCheckStatusByOrder(orderId,accountStatus);
		}
		
		/**
		 * 检测账号
		 * @param id
		 * @param accountStatus
		 * @return
		 * @throws AlipayApiException 
		 */
		public OrderPoJo CheckAccount(Integer payType,BigDecimal amount,String sellerId,Integer id) throws AlipayApiException{
			Pay py =new Pay();
			py.setAmount(amount);
			py.setAppId(appid);
			py.setNotifyUrl(notify_url);
			py.setOrderId(OrderUtil.getOrderNoByAtomic());
			py.setPayType(payType);
			py.setVersion(sellerId);
			py.setTimestamp(String.valueOf(new Date().getTime()));
			Map<String, String> map = new HashMap<>();
			map.put("appId",appid);
			map.put("amount",py.getAmount().toString());
			map.put("orderId",py.getOrderId());
			map.put("payType",String.valueOf(py.getPayType()));
			map.put("notify_url",notify_url);
			map.put("version",sellerId);
			map.put("timestamp",py.getTimestamp());
			py.setSign(PayUtil.getSign(map,private_key));
			Gson gs =new Gson();
			String json = gs.toJson(py);
			String sendPost = HttpRequest.sendPost(check_url, json);
			OrderPoJo fromJson = gs.fromJson(sendPost, OrderPoJo.class);
			yioSelfMapper.updateOrder(id,Integer.valueOf(fromJson.getOrderNo()));
			return fromJson;
		}
		/**
		 * 回调
		 * @param notify
		 * @return
		 * @throws MyException
		 * @throws AlipayApiException
		 */
		public String notify(Notify notify) throws MyException, AlipayApiException {
			Map<String, String> map = new HashMap<>();
			map.put("code",notify.getCode());
			map.put("msg",notify.getMsg());
			map.put("out_trade_no",notify.getOut_trade_no());
			map.put("trade_no",notify.getTrade_no());
			map.put("total_amount",notify.getTotal_amount());
			map.put("seller_id",notify.getSeller_id());
			map.put("timestamp",notify.getTimestamp());
			YioShop yioShop = yioShopMapper.findByAppId(notify.getSeller_id());
			if (!PayUtil.getSign(map,yioShop.getPrivateKey()).equals(notify.getSign())){
				throw new MyException("100");
			}
			
			//
			logger.info("回调成功"+notify.getOut_trade_no());
			updateCheckStatusByOrder(Integer.valueOf(notify.getOut_trade_no()), 2);
			Map<String, String> mapReturn = new HashMap<>();
			mapReturn.put("code","0");
			Gson gson = new Gson();
			return gson.toJson(mapReturn);
		}
		
		
		public void insertAll(){
			   List<YioSelf> yioSelves =yioSelfMapper.finds();
			   for(YioSelf yioSelf:yioSelves){
			      yioSelf.setSelfCheckStatus(0);
			      yioSelf.setAccountStatus(2);
			      yioSelf.setCreateDate(new Date());
			      yioSelfMapper.insert(yioSelf);
			   }
			}
		
		/**
		 * 定时获取订单状态
		 * @param 
		 * @param 
		 * @return
		 */
		public String getOrderStatus(String orderId){
			List<YioOrders> findByMyOrderId = yioOrdersMapper.findByMyOrderId(orderId);
			return findByMyOrderId.get(0).getPayStatus();
		}
		/**
		 * 变更账户状态
		 * @param id
		 * @param accountStatus
		 * @return
		 */
		public int updateAccountStatus(int id,int accountStatus){
			return yioSelfMapper.updateAccountStatus(id,accountStatus);
		}
		
}
