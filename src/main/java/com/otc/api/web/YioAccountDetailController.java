package com.otc.api.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenAdmin;
import com.otc.api.domain.YioShop;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioAccountDetail;
import com.otc.api.service.YioAccountDetailService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("账单明细")
@RestController
@RequestMapping(value = "/yioAccountDetail")
public class YioAccountDetailController {

	@Autowired
	private YioAccountDetailService yioAccountDetailService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@Token
	@ApiOperation(value = "账单明细", notes = "" ,response=YioAccountDetail.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(@RequestParam(value = "start",required = false) Date start,
					   @RequestParam(value = "end",required = false) Date end,
					   @RequestParam(value = "type",required = false) Integer type,
					   @RequestParam(value = "orderNo",required = false) String orderNo,
					   @RequestParam(value = "serverNo",required = false) String serverNo,
					   @Param("page") Integer page, @Param("size") Integer size, HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioAccountDetailService.list(pageNo,sizeNo,user,orderNo,serverNo,start,end,type);
	}

	@TokenAdmin
	@ApiOperation(value = "账单明细管理员", notes = "" ,response=YioAccountDetail.class)
	@RequestMapping(value = "/listAdmin/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object listAdmin(@PathVariable("id") Integer id,@RequestParam(value = "start",required = false) Date start,
					   @RequestParam(value = "end",required = false) Date end,
					   @RequestParam(value = "type",required = false) Integer type,
					   @RequestParam(value = "orderNo",required = false) String orderNo,
					   @RequestParam(value = "serverNo",required = false) String serverNo,
					   @Param("page") Integer page, @Param("size") Integer size, HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioAccountDetailService.listAdmin(id,pageNo,sizeNo,user,orderNo,serverNo,start,end,type);
	}

	@Token
	@ApiOperation(value = "账单明细", notes = "" ,response=YioAccountDetail.class)
	@RequestMapping(value = "/report",method = RequestMethod.GET,produces = "application/json")
	public Object report(@RequestParam(value = "start",required = false) Date start,
					   @RequestParam(value = "end",required = false) Date end,
					   @RequestParam(value = "type",required = false) Integer type,
					   @RequestParam(value = "orderNo",required = false) String orderNo,
					   @RequestParam(value = "serverNo",required = false) String serverNo,
					   HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		return yioAccountDetailService.report(user,orderNo,serverNo,start,end,type);
	}

	@TokenAdmin
	@ApiOperation(value = "账单明细", notes = "" ,response=YioAccountDetail.class)
	@RequestMapping(value = "/reportAdmin/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object reportAdmin(@PathVariable("id") Integer id,@RequestParam(value = "start",required = false) Date start,
						 @RequestParam(value = "end",required = false) Date end,
						 @RequestParam(value = "type",required = false) Integer type,
						 @RequestParam(value = "orderNo",required = false) String orderNo,
						 @RequestParam(value = "serverNo",required = false) String serverNo,
						 HttpServletRequest request) throws MyException {
		YioShop user = (YioShop)request.getAttribute("user");
		return yioAccountDetailService.reportAdmin(id,user,orderNo,serverNo,start,end,type);
	}
}
