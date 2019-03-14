package com.otc.api.service;

import java.util.List;

import com.otc.api.domain.YioDingGroup;
import com.otc.api.domain.YioSeller;
import com.otc.api.exception.MyException;
import com.otc.api.mapper.YioDingGroupMapper;
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
	private YioDingGroupMapper yioDingGroupMapper;

	public void create(DingGroup dingGroup) throws MyException {
	 	for (Integer sellerId : dingGroup.getSellerId()){
			YioDingGroupUser groupUser1 = yioDingGroupUserMapper.findBySellerId(sellerId);
	 		if (groupUser1!=null) {
				throw new MyException("60001");
			}
			YioDingGroupUser groupUser = new YioDingGroupUser();
			groupUser.setSellerId(sellerId);
			groupUser.setDingGroupId(dingGroup.getDingGroupId());

			yioDingGroupUserMapper.insert(groupUser);
		}
	}

	public List<YioDingGroup> list() {
		return yioDingGroupMapper.findAll();
	}

	public void delete(Integer sellerId){
		yioDingGroupUserMapper.deleteSeller(sellerId);
	}
}
