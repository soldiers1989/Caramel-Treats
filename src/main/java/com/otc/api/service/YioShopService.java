package com.otc.api.service;

import java.util.Date;
import java.util.List;

import com.otc.api.domain.YioSysUser;
import com.otc.api.domain.YioUser;
import com.otc.api.exception.MyException;
import com.otc.api.mapper.YioSysUserMapper;
import com.otc.api.pojo.shop.Shop;
import com.otc.api.pojo.user.Login;
import com.otc.api.pojo.user.UserPoJo;
import com.otc.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otc.api.domain.YioPayType;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopRate;
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
			return yioShopMapper.find();
		}else {
			return yioShopMapper.findId(shop.getId());
		}
	}
	/**
	 * 商户渠道信息
	 * @param shopId
	 * @return
	 */
	public List<YioShopRate> getRate(String shopId){
		return yioShopRateMapper.findAllByPayShopId(Integer.parseInt(shopId));
	}
	/**
	 * 获取商户PrivateKey
	 * @param user
	 * @param appId
	 * @return
	 * @throws MyException
	 */
	public YioShop getPrivateKey(YioUser user,String appId)throws MyException{
		List<YioSysUser> usersPass = yioSysUserMapper.findAllByUserNameAndPass(user.getUsername(), MD5Util.string2MD5(user.getPassword()));
		if (usersPass.size()<=0){
			throw new MyException("10004");
		}
		return yioShopMapper.findByAppId(appId);
	}
	/**
	 * 新增商户渠道
	 * @param shopId
	 * @param yioShopRateliat
	 * @throws MyException
	 */
	public void getHasRate(String shopId,List<YioShopRate> yioShopRateliat)throws MyException{
		List<YioShopRate>shopRateList = yioShopRateMapper.findAllByPayShopId(Integer.parseInt(shopId));
		for (int i = 0; i < yioShopRateliat.size(); i++) {
			for (int j = 0; j < shopRateList.size(); j++) {
				if(yioShopRateliat.get(i).getPayType().equals(shopRateList.get(j).getPayType())){
					yioShopRateliat.remove(i);
				}
			}
		}
		if(!yioShopRateliat.isEmpty()){
			for (YioShopRate yioShopRate : shopRateList) {
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
	public int updateShopRate(String shopId ,String payType){
		return yioShopRateMapper.updateOpenOrClose(Integer.parseInt(shopId),Integer.parseInt(payType));
	}
	
	public List<YioPayType> listRate(){
		return yioShopRateMapper.findAllPayType();
		
	}
	
}
