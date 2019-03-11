package com.otc.api.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.StringUtil;
import com.otc.api.aop.Token;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopRate;
import com.otc.api.domain.YioSysUser;
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
	
	@Token
	@ApiOperation(value="商户渠道信息",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/shopRate/{shopId}",method = RequestMethod.GET,produces = "application/json")
	public Object getShopRate(@PathVariable("shopId")String shopId){
		if("0".equals(shopId)) 
		{
			return	yioShopService.listRate();
		}
		return yioShopService.getRate(shopId);
	}
	
	@Token
	@ApiOperation(value="验证密码",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/testPassword",method = RequestMethod.GET,produces = "application/json")
	public  Object tested(@RequestParam(value = "password", required = false)String password,
			@RequestParam(value = "appId", required = false)String appId,
			HttpServletRequest request)throws MyException{
		YioShop user = (YioShop)request.getAttribute("user");
		user.setPassword(password);
		return yioShopService.getPrivateKey(user, appId);
	} 
	
	@Token
	@ApiOperation(value="商户新增渠道",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/createShopRate",method = RequestMethod.POST,produces = "application/json")
	public Object create(@RequestBody YioShop yioShop) throws MyException{
		 yioShopService.getHasRate(String.valueOf(yioShop.getId()),yioShop.getShopRateList());
		return null;
	}
	
	@ApiOperation(value="关闭用户渠道",notes = "",response = YioShopRate.class)
	@RequestMapping(value = "/updateShopRate",method = RequestMethod.PUT,produces = "application/json")
	public Object update(@RequestBody YioShopRate ysr){
		return yioShopService.updateShopRate(ysr.getId(),ysr.getDisable());
	}
//	@Token
//	@ApiOperation(value="查询所有支付渠道",notes = "",response = YioShopRate.class)
//	@RequestMapping(value = "/listRate",method = RequestMethod.GET,produces = "application/json")
//	public Object listRate() throws MyException{
//		return yioShopService.listRate();
//	}
	
	@Token
	@ApiOperation(value = "商户列表", notes = "" ,response=YioShop.class)
	@RequestMapping(value = "/businessList",method = RequestMethod.GET,produces = "application/json")
	public Object businessmenList(@Param("page") Integer page, @Param("size") Integer size,HttpServletRequest request) throws Exception {
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		YioShop user = (YioShop)request.getAttribute("user");
		return yioShopService.businessmenList(user,pageNo,sizeNo);
	}
	
	@Token
	@ApiOperation(value = "商户信息", notes = "" ,response=YioShop.class)
	@RequestMapping(value = "/businessInfo/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object businessmenList(@PathVariable("id") Integer id) throws Exception {
		return yioShopService.selectBusinessmen(id);
	}
	
	@Token
	@ApiOperation(value = "添加商户", notes = "" ,response=Integer.class)
	@RequestMapping(value = "/createBusinessmen",method = RequestMethod.POST,produces = "application/json")
	public Object create(@RequestBody YioShop yioShop,HttpServletRequest request) throws Exception {
		YioShop user = (YioShop)request.getAttribute("user");
		yioShop.setAuthority(user.getAuthority());
		int create = yioShopService.create(yioShop);
		return null;
	}
	@Token
	@ApiOperation(value = "启动禁用商户", notes = "" ,response=Integer.class)
	@RequestMapping(value = "/updateBusinessmenStatus",method = RequestMethod.PUT,produces = "application/json")
	public Object updateBusinessmenStatus(@RequestBody YioShop yioShop) throws Exception {
		int create = yioShopService.updateBusinessmenStatus(yioShop.getId(),yioShop.getDisable());
		return null;
	}
}
