package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.pojo.user.PassWord;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioSysUser;
import com.otc.api.service.YioSysUserService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("描述")
@RestController
@RequestMapping(value = "/yioSysUser")
public class YioSysUserController {

	@Autowired
	private YioSysUserService yioSysUserService;

	@Token
	@ApiOperation(value = "修改密码", notes = "" ,response=YioSysUser.class)
	@RequestMapping(value = "/updatePass",method = RequestMethod.PUT,produces = "application/json")
	public Object updatePass(@RequestBody PassWord passWord, HttpServletRequest request) throws MyException {
		String token = (String) request.getAttribute("token");
		yioSysUserService.updatePass(passWord,token);
		return null;
	}
}
