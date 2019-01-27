package com.otc.api.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSysBank;
import com.otc.api.mapper.YioSysBankMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YioSysBankService {

	@Autowired
	private YioSysBankMapper yioSysBankMapper;

	public PageInfo<YioSysBank> getAll(Integer page,Integer size) {
		PageHelper.startPage(page,size);
		List<YioSysBank> banks = yioSysBankMapper.findAll();
		PageInfo<YioSysBank> info = new PageInfo<>(banks);
		return info;
	}

	public void create(YioSysBank yioSysBank){
		yioSysBank.setStatus(2);
		yioSysBankMapper.insert(yioSysBank);
	}

	public void update(YioSysBank yioSysBank){
		YioSysBank bank = yioSysBankMapper.findById(yioSysBank.getId());
		yioSysBank.setStatus(bank.getStatus());
		yioSysBankMapper.update(yioSysBank);
	}

	public void delete(Integer id){
		YioSysBank bank = yioSysBankMapper.findById(id);
		yioSysBankMapper.delete(bank);
	}

	public YioSysBank show(Integer id){
		return yioSysBankMapper.findById(id);
	}

	@Transactional
	public void updateStatus(Integer id){
		yioSysBankMapper.updateStatus(2);
		YioSysBank bank = yioSysBankMapper.findById(id);
		bank.setStatus(1);
		yioSysBankMapper.update(bank);
	}
}
