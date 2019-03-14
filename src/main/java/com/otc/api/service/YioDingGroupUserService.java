package com.otc.api.service;

import java.util.List;

import com.otc.api.domain.YioSeller;
import com.otc.api.mapper.YioSellerMapper;
import com.otc.api.pojo.dingGroup.DingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioDingGroupUser;
import com.otc.api.mapper.YioDingGroupUserMapper;
@Service
public class YioDingGroupUserService {

	@Autowired
	private YioDingGroupUserMapper yioDingGroupUserMapper;

	@Autowired
	private YioSellerMapper yioSellerMapper;

	 public void create(DingGroup dingGroup) {
	 	for (Integer sellerId : dingGroup.getSellerId()){
			YioDingGroupUser groupUser = new YioDingGroupUser();
			groupUser.setSellerId(sellerId);
			groupUser.setDingGroupId(dingGroup.getDingGroupId());
			yioDingGroupUserMapper.insert(groupUser);
		}
	}
}
