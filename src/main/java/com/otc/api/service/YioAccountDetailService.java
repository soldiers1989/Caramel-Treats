package com.otc.api.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.domain.YioShop;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.pojo.detail.DetailPoJo;
import com.otc.api.pojo.detail.DetailReport;
import com.otc.api.pojo.detail.DetailServerPoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioAccountDetail;
import com.otc.api.mapper.YioAccountDetailMapper;
@Service
public class YioAccountDetailService {

	@Autowired
	private YioAccountDetailMapper yioAccountDetailMapper;

	@Autowired
	private YioShopMapper yioShopMapper;

	public PageInfo<DetailPoJo> list(Integer page, Integer size, YioShop shop, String orderNo, String serverNo, Date start,Date end,Integer type) {
		shop = yioShopMapper.findById(shop.getId());
		PageHelper.startPage(page,size);
		List<DetailPoJo> detailPoJos = yioAccountDetailMapper.query(shop.getAppId(),orderNo,serverNo,start,end,type);
		PageInfo<DetailPoJo> info = new PageInfo<>(detailPoJos);
		return info;
	}

	public DetailReport report(YioShop shop, String orderNo, String serverNo, Date start, Date end, Integer type) {
		shop = yioShopMapper.findById(shop.getId());
		DetailReport report = new DetailReport();
		report.setInTotal(yioAccountDetailMapper.querySum(shop.getAppId(),orderNo,serverNo,start,end));
		report.setInCount(yioAccountDetailMapper.queryCount(shop.getAppId(),orderNo,serverNo,start,end));
		report.setOutTotal(yioAccountDetailMapper.querySumServer(shop.getAppId(),orderNo,serverNo,start,end));
		report.setOutCount(yioAccountDetailMapper.queryCountServer(shop.getAppId(),orderNo,serverNo,start,end));
		return report;
	}

	public PageInfo<DetailServerPoJo> listAdmin(Integer id,Integer page, Integer size, YioShop shop, String orderNo, String serverNo, Date start,Date end,Integer type) {
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}
		PageHelper.startPage(page,size);
		List<DetailServerPoJo> detailPoJos = yioAccountDetailMapper.queryByServer(shop.getAppId(),orderNo,serverNo,start,end,type);
		PageInfo<DetailServerPoJo> info = new PageInfo<>(detailPoJos);
		return info;
	}

	public DetailReport reportAdmin(Integer id,YioShop shop, String orderNo, String serverNo, Date start, Date end, Integer type) {
		if (shop.getAuthority().equals(1)){
			if (id == 0){
				shop = yioShopMapper.findAll().get(0);
			}else {
				shop = yioShopMapper.findById(id);
			}
		}
		DetailReport report = new DetailReport();
		report.setInTotal(yioAccountDetailMapper.querySum(shop.getAppId(),orderNo,serverNo,start,end));
		report.setInCount(yioAccountDetailMapper.queryCount(shop.getAppId(),orderNo,serverNo,start,end));
		report.setOutTotal(yioAccountDetailMapper.querySumServer(shop.getAppId(),orderNo,serverNo,start,end));
		report.setOutCount(yioAccountDetailMapper.queryCountServer(shop.getAppId(),orderNo,serverNo,start,end));
		return report;
	}
}
