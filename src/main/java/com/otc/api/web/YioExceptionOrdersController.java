package com.otc.api.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenAdmin;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioExceptionOrders;
import com.otc.api.service.YioExceptionOrdersService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("异常单据")
@RestController
@RequestMapping(value = "/yioExceptionOrders")
public class YioExceptionOrdersController {

	@Autowired
	private YioExceptionOrdersService yioExceptionOrdersService;


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@TokenAdmin
	@ApiOperation(value = "异常单据列表", notes = "" ,response=YioExceptionOrders.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(@RequestParam(value = "start",required = false) Date start,
					   @RequestParam(value = "end",required = false) Date end,
					   @RequestParam(value = "username",required = false) String username,
					   @RequestParam(value = "status",required = false) Integer status,
			@Param("page") Integer page,@Param("size") Integer size,HttpServletRequest request) throws MyException {
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioExceptionOrdersService.getAll(start,end,username,status,pageNo,sizeNo);
	}

	@TokenAdmin
	@ApiOperation(value = "确认处理", notes = "" ,response=YioExceptionOrders.class)
	@RequestMapping(value = "/updateEnter/{id}",method = RequestMethod.PUT,produces = "application/json")
	public Object updateEnter(@PathVariable("id") Integer id) throws MyException {
		yioExceptionOrdersService.updateEnter(id);
		return null;
	}
}
