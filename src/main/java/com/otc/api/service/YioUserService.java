package com.otc.api.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.domain.YioShop;
import com.otc.api.mapper.YioSellerMapper;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.pojo.user.UserList;
import com.otc.api.pojo.user.UserReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioUser;
import com.otc.api.mapper.YioUserMapper;
@Service
public class YioUserService {

	@Autowired
	private YioUserMapper yioUserMapper;

	@Autowired
	private YioShopMapper yioShopMapper;

	@Autowired
	private YioSellerMapper yioSellerMapper;

	/**
	 * 交易员统计
	 * @param shop
	 * @return
	 */
	public UserReport report(YioShop shop) {
		UserReport userReport = new UserReport();
		userReport.setServiceCount(yioShopMapper.count());
		userReport.setUserCount(yioUserMapper.count());
		userReport.setWork(yioUserMapper.countWork());
		userReport.setAli(yioSellerMapper.countByType(1));
		userReport.setWei(yioSellerMapper.countByType(2));
		return userReport;
	}

	/**
	 * 交易员列表
	 * @param username
	 * @param work
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<UserList> list(String username,Integer work,Integer page,Integer size){
		PageHelper.startPage(page,size);
		List<UserList> lists = yioUserMapper.query(username,work);
		PageInfo<UserList> info = new PageInfo<>(lists);
		return info;
	}

	/**
	 * 修改冻结 解冻状态
	 * @param id
	 */
	public void updateStatus(Integer id){
		YioUser user = yioUserMapper.findById(id);
		if (user.getStatus().equals(1)){
			user.setStatus(2);
		}else {
			user.setStatus(1);
		}
		yioUserMapper.updateStatus(user);
	}
}
