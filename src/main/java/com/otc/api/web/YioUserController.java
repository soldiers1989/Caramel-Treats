package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.domain.YioShop;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioUser;
import com.otc.api.service.YioUserService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("交易员")
@RestController
@RequestMapping(value = "/yioUser")
public class YioUserController {

	@Autowired
	private YioUserService yioUserService;

	@Token
	@ApiOperation(value = "交易员统计", notes = "" ,response=YioUser.class)
	@RequestMapping(value = "/report",method = RequestMethod.GET,produces = "application/json")
	public Object report(HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		return yioUserService.report(user);
	}

	@Token
	@ApiOperation(value = "交易员列表", notes = "" ,response=YioUser.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(@RequestParam(value = "username",required = false) String username,
					   @RequestParam(value = "work",required = false) Integer work,@Param("page") Integer page,
					   @Param("size") Integer size,HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioUserService.list(username,work,pageNo,sizeNo);
	}

	@Token
	@ApiOperation(value = "修改状态", notes = "" ,response=YioUser.class)
	@RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.PUT,produces = "application/json")
	public Object updateStatus(@PathVariable("id") Integer id, HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		yioUserService.updateStatus(id);
		return null;
	}
}
