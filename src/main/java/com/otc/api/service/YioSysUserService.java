package com.otc.api.service;

import java.util.List;

import com.otc.api.exception.MyException;
import com.otc.api.pojo.user.PassWord;
import com.otc.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSysUser;
import com.otc.api.mapper.YioSysUserMapper;
@Service
public class YioSysUserService {

	@Autowired
	private YioSysUserMapper yioSysUserMapper;

	public void updatePass(PassWord passWord,String token) throws MyException {
		YioSysUser user = yioSysUserMapper.findAllByToken(token);
		if (!passWord.getPassword().equals(passWord.getPassword1())){
			throw new MyException("10002");
		}
		user.setPassword(MD5Util.string2MD5(passWord.getPassword()));
		yioSysUserMapper.update(user);
	}
}
