package com.otc.api.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otc.api.aop.Token;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopRate;
import com.otc.api.domain.YioUser;
import com.otc.api.exception.MyException;
import com.otc.api.pojo.user.Login;
import com.otc.api.service.YioShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
		YioShop user = (YioShop)request.getAttribute("user");
		return yioShopService.list(user);
	}

	@ApiOperation(value="商户渠道信息",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/shopRate",method = RequestMethod.GET,produces = "application/json")
	public Object getShopRate(@RequestParam(value = "shopId", required = false)String shopId){
		return yioShopService.getRate(shopId);
	}
	
	@ApiOperation(value="验证密码",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/tested",method = RequestMethod.GET,produces = "application/json")
	public  Object tested(@RequestParam(value = "password", required = false)String password,
			@RequestParam(value = "appId", required = false)String appId,
			HttpServletRequest request)throws MyException{
		YioUser user=(YioUser)request.getAttribute("user");
		return yioShopService.getPrivateKey(user, appId);
	} 
	
//	@ApiOperation(value="商户新增渠道",notes = "",response = YioShopRate.class)
//	@RequestMapping(value = "/create",method = RequestMethod.GET,produces = "application/json")
//	public Object create(@RequestParam(value = "shopId", required = false)String shopId,
//			@RequestParam(value = "yioShopRateList", required = false)List<YioShopRate> yioShopRateList){
//		 yioShopService.getHasRate(shopId,yioShopRateList);
//		return null;
//	}
	
	@ApiOperation(value="关闭用户渠道",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/update",method = RequestMethod.GET,produces = "application/json")
	public Object update(@RequestParam(value = "id", required = false)String id,
			@RequestParam(value = "payType", required = false)String payType){
		return yioShopService.updateShopRate(id, payType);
	}
//	@ApiOperation(value="查询所有支付渠道",notes = "",response = YioShopRate.class)
//	@RequestMapping(value = "/listRate",method = RequestMethod.GET,produces = "application/json")
//	public Object listRate() throws MyException{
//		return yioShopService.listRate();
//	}
}
