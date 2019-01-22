package com.otc.api.service;

import java.util.Date;
import java.util.List;

import com.otc.api.exception.MyException;
import com.otc.api.pojo.shop.Shop;
import com.otc.api.pojo.user.Login;
import com.otc.api.pojo.user.UserPoJo;
import com.otc.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioShop;
import com.otc.api.mapper.YioShopMapper;
@Service
public class YioShopService {

	@Autowired
	private YioShopMapper yioShopMapper;

	/**
	 * 商户后台登陆
	 * @param login
	 * @return
	 * @throws MyException
	 */
	public UserPoJo login(Login login) throws MyException {
		UserPoJo poJo = new UserPoJo();
		List<YioShop> users = yioShopMapper.findAllByName(login.getUsername());
		if (users.size()<=0){
			throw new MyException("10003");
		}
		List<YioShop> usersPass = yioShopMapper.findAllByUserNameAndPass(login.getUsername(), MD5Util.string2MD5(login.getPassword()));
		if (usersPass.size()<=0){
			throw new MyException("10004");
		}
		YioShop user = usersPass.get(0);
		user.setLoginTime(new Date());
		user.setToken(MD5Util.string2MD5(user.getUsername()+user.getPassword()+user.getLoginTime()));
		yioShopMapper.update(user);
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
		if (shop.getAuthority().equals(1)){
			return yioShopMapper.find();
		}else {
			return yioShopMapper.findId(shop.getId());
		}
	}
}
