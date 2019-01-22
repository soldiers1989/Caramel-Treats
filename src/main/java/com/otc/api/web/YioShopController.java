package com.otc.api.web;

import com.otc.api.aop.Token;
import com.otc.api.pojo.user.Login;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioShop;
import com.otc.api.service.YioShopService;
import com.otc.api.exception.MyException;

import javax.servlet.http.HttpServletRequest;

@Api("业务系统商户")
@RestController
@RequestMapping(value = "/yioShop")
public class YioShopController {

	@Autowired
	private YioShopService yioShopService;

	@ApiOperation(value = "登陆", notes = "" ,response=YioShop.class)
	@RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
	public Object login(@RequestBody Login login) throws MyException {
		return yioShopService.login(login);
	}

	@Token
	@ApiOperation(value = "列表", notes = "" ,response=YioShop.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(HttpServletRequest request) throws MyException {
		YioShop user=(YioShop)request.getAttribute("user");
		return yioShopService.list(user);
	}

}
