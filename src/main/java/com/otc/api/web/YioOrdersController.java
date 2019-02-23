package com.otc.api.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alipay.api.AlipayApiException;
import com.otc.api.aop.Token;
import com.otc.api.aop.TokenAdmin;
import com.otc.api.domain.YioSeller;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioUser;
import com.otc.api.pojo.order.UpdatePay;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioOrders;
import com.otc.api.service.YioOrdersService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("描述")
@RestController
@RequestMapping(value = "/yioOrders")
public class YioOrdersController {

	@Autowired
	private YioOrdersService yioOrdersService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@Token
	@ApiOperation(value = "充值交易", notes = "" ,response=YioSeller.class)
	@RequestMapping(value = "/report/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object report(@PathVariable("id") Integer id,
						 @RequestParam(value = "start",required = false) Date start,
						 @RequestParam(value = "end",required = false) Date end,
						 @RequestParam(value = "type",required = false) Integer type,
						 @RequestParam(value = "orderNo",required = false) String orderNo,
						 @RequestParam(value = "serverNo",required = false) String serverNo,
						 @RequestParam(value = "userName",required = false) String userName,
						 HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		return yioOrdersService.report(id,user,start,end,type,orderNo,serverNo,userName);
	}

	@Token
	@ApiOperation(value = "充值交易", notes = "" ,response=YioSeller.class)
	@RequestMapping(value = "/list/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object list(@PathVariable("id") Integer id,
						 @RequestParam(value = "start",required = false) Date start,
						 @RequestParam(value = "end",required = false) Date end,
						 @RequestParam(value = "type",required = false) Integer type,
						 @RequestParam(value = "orderNo",required = false) String orderNo,
						 @RequestParam(value = "serverNo",required = false) String serverNo,
						 @RequestParam(value = "userName",required = false) String userName,
					     @RequestParam(value = "qname",required = false) String qname,
					     @RequestParam(value = "status",required = false) Integer status,
					     @Param("page") Integer page,@Param("size") Integer size,
						 HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioOrdersService.list(id,user,start,end,type,orderNo,serverNo,userName,qname,status,pageNo,sizeNo);
	}

	@TokenAdmin
	@ApiOperation(value = "修改已付款", notes = "" ,response=YioOrders.class)
	@RequestMapping(value="/updatePay",method = RequestMethod.PUT,produces = "application/json")
	public Object updatePay(@RequestBody UpdatePay updatePay, HttpServletRequest request) throws MyException, AlipayApiException {
		yioOrdersService.updatePay(updatePay.getOrderNo(),updatePay.getServerNo(),updatePay.getPassWord());
		return null;
	}
}
