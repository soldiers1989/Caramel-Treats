package com.otc.api.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.domain.YioRatio;
import com.otc.api.domain.YioShop;
import com.otc.api.mapper.*;
import com.otc.api.pojo.user.UserDetail;
import com.otc.api.pojo.user.UserDetailReport;
import com.otc.api.pojo.user.UserList;
import com.otc.api.pojo.user.UserReport;
import com.otc.api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioUser;

@Service
public class YioUserService {

	@Autowired
	private YioUserMapper yioUserMapper;

	@Autowired
	private YioShopMapper yioShopMapper;

	@Autowired
	private YioSellerMapper yioSellerMapper;

	@Autowired
	private YioOrdersMapper yioOrdersMapper;

	@Autowired
	private YioWithdrawMapper yioWithdrawMapper;

	@Autowired
	private YioRatioMapper ratioMapper;

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

	public UserDetail show(Integer id){
		return yioUserMapper.findId(id);
	}

	/**
	 *
	 * @param id
	 * @param start
	 * @param end
	 * @param type 0自定义 1今天  2昨天 3 7天 4 30天
	 * @return
	 */
	public UserDetailReport showReport(Integer id, Date start,Date end,Integer type){
		UserDetailReport report = new UserDetailReport();
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
		report.setRecharge(yioOrdersMapper.sumPayPrice(id,start,end));
		report.setWithdraw(yioWithdrawMapper.sumByDate(id,start,end));
		report.setReward((report.getRecharge().add(report.getWithdraw())).multiply(ratioMapper.findAll().get(0).getRatio()));
		report.setRechargeCount(yioOrdersMapper.countPayPrice(id,start,end));
		report.setWithdrawCount(yioWithdrawMapper.countByDate(id,start,end));
		return report;
	}
}
