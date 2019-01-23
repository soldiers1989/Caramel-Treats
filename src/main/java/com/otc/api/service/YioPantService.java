package com.otc.api.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.pojo.pant.Pant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioPant;
import com.otc.api.mapper.YioPantMapper;
@Service
public class YioPantService {

	@Autowired
	private YioPantMapper yioPantMapper;

	public Pant report() {
		Pant pant = new Pant();
		pant.setDevice(yioPantMapper.count());
		pant.setOnLine(yioPantMapper.countByPant(1));
		pant.setDropLine(yioPantMapper.countByPant(2));
		return pant;
	}

	/**
	 * 设备列表
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<YioPant> list(Integer page,Integer size){
		PageHelper.startPage(page,size);
		List<YioPant> pants = yioPantMapper.findAll();
		PageInfo<YioPant> info = new PageInfo<>(pants);
		return info;
	}
}
