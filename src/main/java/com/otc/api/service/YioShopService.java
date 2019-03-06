package com.otc.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.otc.api.domain.YioSysUser;
import com.otc.api.domain.YioUser;
import com.otc.api.exception.MyException;
import com.otc.api.mapper.YioSysUserMapper;
import com.otc.api.pojo.shop.Shop;
import com.otc.api.pojo.user.Login;
import com.otc.api.pojo.user.UserPoJo;
import com.otc.api.util.DateUtils;
import com.otc.api.util.MD5Util;
import com.otc.api.util.RSACoder;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.otc.api.domain.YioPayType;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopDeposit;
import com.otc.api.domain.YioShopRate;
import com.otc.api.mapper.YioShopDepositMapper;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.mapper.YioShopRateMapper;
@Service
public class YioShopService {

	@Autowired
	private YioShopMapper yioShopMapper;

	@Autowired
	private YioSysUserMapper yioSysUserMapper;
	@Autowired
	private YioShopRateMapper yioShopRateMapper;

	@Autowired
	private YioShopDepositMapper depositMapper;
	/**
	 * 商户后台登陆
	 * @param login
	 * @return
	 * @throws MyException
	 */
	public UserPoJo login(Login login) throws MyException {
		UserPoJo poJo = new UserPoJo();
		List<YioSysUser> users = yioSysUserMapper.findAllByName(login.getUsername());
		if (users.size()<=0){
			throw new MyException("10003");
		}
		List<YioSysUser> usersPass = yioSysUserMapper.findAllByUserNameAndPass(login.getUsername(), MD5Util.string2MD5(login.getPassword()));
		if (usersPass.size()<=0){
			throw new MyException("10004");
		}
		YioSysUser user = usersPass.get(0);
		user.setLoginTime(new Date());
		user.setToken(MD5Util.string2MD5(user.getUsername()+user.getPassword()+user.getLoginTime()));
		yioSysUserMapper.update(user);
		poJo.setAuthority(user.getAuthority().toString());
		poJo.setToken(user.getToken());
		return poJo;
	}

	/**
	 * 列表
	 * @param shop
	 * @return
	 */
	public List<Shop> list(YioShop shop){
		if (shop.getAuthority().equals(1) || shop.getAuthority().equals(4)){
			List<Shop> find = yioShopMapper.find();
			return find;
		}else {
			List<Shop> findId = yioShopMapper.findId(shop.getId());
			return findId;
		}
	}
	/**
	 * 商户渠道信息
	 * @param shopId
	 * @return
	 */
	public List<YioShopRate> getRate(String shopId){
		List<YioShopRate> findAllByPayShopId = yioShopRateMapper.findAllByPayShopId(Integer.parseInt(shopId));
		return findAllByPayShopId;
	}
	
	/**
	 * 获取商户PrivateKey
	 * @param user
	 * @param appId
	 * @return
	 * @throws MyException
	 */
	public String getPrivateKey(YioShop user,String appId)throws MyException{
		List<YioSysUser> usersPass = yioSysUserMapper.findAllByUserNameAndPass(user.getUsername(), MD5Util.string2MD5(user.getPassword()));
		if (usersPass.size()<=0){
			throw new MyException("10004");
		}
		return yioShopMapper.findByAppId(appId).getPrivateKey();
	}
	/**
	 * 新增商户渠道
	 * @param shopId
	 * @param yioShopRateliat
	 * @throws MyException
	 */
	public void getHasRate(String shopId,List<YioShopRate> yioShopRateliat)throws MyException{
		if(yioShopRateliat.size()>0) {
			List<YioShopRate>shopRateList = yioShopRateMapper.findAllByPayShopId(Integer.parseInt(shopId));
			for (int i = 0; i < yioShopRateliat.size(); i++) {
				if(shopRateList.size()>0) {
					for (int j = 0; j < shopRateList.size(); j++) {
						if(yioShopRateliat.get(i).getPayType().equals(shopRateList.get(j).getPayType())){
							yioShopRateliat.remove(i);
						}
					}
				}
			}
				for (YioShopRate yioShopRate : yioShopRateliat) {
					yioShopRate.setShopId(Integer.valueOf(shopId));
					yioShopRateMapper.insert(yioShopRate);
				}
		}
	}
	/**
	 * 关闭或开启渠道
	 * @param shopId
	 * @param payType
	 * @return
	 */
	public int updateShopRate(int shopId,int disable){
		return yioShopRateMapper.updateOpenOrClose(shopId,disable);
	}
	/**
	 * 关闭所有渠道
	 * @param shopId
	 * @param payType
	 * @return
	 */
	public int closeShopRate(YioShop ys){
		try {
			yioShopRateMapper.closeOpenOrClose(ys.getDisable(),ys.getId());
		} catch (Exception e) {
		}
		return 0;
	}
	
	public List<YioPayType> listRate(){
		return yioShopRateMapper.findAllPayType();
		
	}
	
	/**
	 * 商户列表
	 * @param shop
	 * @return
	 * @throws MyException 
	 */
	public PageInfo<YioShop> businessmenList(YioShop shop,int pageNo,int sizeNo) throws MyException{
		PageHelper.startPage(pageNo,sizeNo);
		List<YioShop> ys=new ArrayList<>();
		if (shop.getAuthority().equals(1) || shop.getAuthority().equals(4)){
			ys= yioShopMapper.findBusiness();
		}else {
			if(shop.getId()==null) {
				throw new MyException("50001");
			}
			ys= yioShopMapper.findIdBusiness(shop.getId());
		}
		for (YioShop yioShop : ys) {
			
			yioShop.setShopRateSize(yioShopRateMapper.countRate(yioShop.getId()));
		}
		PageInfo<YioShop> info = new PageInfo<>(ys);
		return info;
	}
	
	/**
	 * 商户信息
	 * @param shop
	 * @return
	 * @throws MyException 
	 */
	public YioShop selectBusinessmen(Integer id) throws MyException{
		List<YioShop> ys= yioShopMapper.findIdBusiness(id);
		if(ys.size()>0) {
			return ys.get(0);
		}
		return null;
	}
	/**
	 * 创建商户
	 * @param yioShop
	 * @throws Exception
	 */
	@Transactional(rollbackFor=MyException.class)
	public int create(YioShop yioShop) throws Exception {
		if(StringUtil.isEmpty(yioShop.getName())||yioShop.getAuthority()!=1){
			throw new MyException("50001");
		}
		yioShop.setCreateAt(new Date());
		yioShop.setAppId(DateUtils.getDateFromString3(new Date()));
		Map<String, Object> keyMap = RSACoder.initKey();
        //公钥
        byte[] publicKey = RSACoder.getPublicKey(keyMap);
        //私钥
        byte[] privateKey = RSACoder.getPrivateKey(keyMap);
		yioShop.setPrivateKey(Base64.encodeBase64String(privateKey));
		yioShop.setDisable(0);
		yioShop.setLoginTime(new Date());
		//商户id
		List<YioShop> findAllByName = yioShopMapper.findAllByName(yioShop.getName());
		if(findAllByName.size()>0) {
			throw new MyException("50007");
		}
		yioShopMapper.insert(yioShop);
		Integer id = yioShop.getId();;
		//押金
		YioShopDeposit shopDeposit = new YioShopDeposit();
		shopDeposit.setAmount(new BigDecimal(0));
		shopDeposit.setCreatedAt(new Date());
		shopDeposit.setShopId(id);
		shopDeposit.setUpdatedAt(new Date());
		depositMapper.insert(shopDeposit);
		
		//创建支付渠道
		getHasRate(String.valueOf(id), yioShop.getShopRateList());
		return 0;
	}
	
	/**
	 * 启动禁用商户(0:启用 1： 禁用)
	 * @param yioShop
	 * @throws Exception
	 */
	@Transactional(rollbackFor=MyException.class)
	public int updateBusinessmenStatus(Integer shopId,Integer disable) throws Exception {
		YioShop findById = yioShopMapper.findById(shopId);
		if(findById==null) {
			throw new MyException("50003");
		}
		YioShop ys= new YioShop();
		ys.setId(shopId);
		ys.setDisable(disable);
		int update = yioShopMapper.update(ys);
		if(update!=1) {
			throw new MyException("50002");
		}
		//禁用商户渠道
		if(disable==1) {
			closeShopRate(ys);
		}
		return update;
	}
	
}
