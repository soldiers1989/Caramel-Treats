package com.otc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSysUser;
import com.otc.api.mapper.YioSysUserMapper;
@Service
public class YioSysUserService {

	@Autowired
	private YioSysUserMapper yioSysUserMapper;

	 public List<YioSysUser> getAll() {
		return yioSysUserMapper.findAll();
	}
}
