package com.otc.api.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.pojo.seller.SellerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSeller;
import com.otc.api.mapper.YioSellerMapper;
@Service
public class YioSellerService {

	@Autowired
	private YioSellerMapper yioSellerMapper;

	public PageInfo<SellerList> list(Integer id,Integer page,Integer size) {
		PageHelper.startPage(page,size);
		List<SellerList> sellers = yioSellerMapper.findAllByUserId(id);
		PageInfo<SellerList> info = new PageInfo<>(sellers);
		return info;
	}
}
