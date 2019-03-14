package com.otc.api.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenFinance;
import com.otc.api.domain.YioSysSettleFile;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioSysSettle;
import com.otc.api.service.YioSysSettleService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("清算列表")
@RestController
@RequestMapping(value = "/yioSysSettle")
public class YioSysSettleController {

	@Autowired
	private YioSysSettleService yioSysSettleService;


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@TokenFinance
	@ApiOperation(value = "财务清算列表", notes = "" ,response=YioSysSettle.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(@RequestParam(value = "status",required = false) Integer status,
					   @RequestParam(value = "start",required = false) Date start,
					   @RequestParam(value = "end",required = false) Date end,
					   @RequestParam(value = "name",required = false) String name,
					   @RequestParam(value = "bankCard",required = false) String bankCard,
					   @RequestParam(value = "username",required = false) String username,
					   @Param("page") Integer page, @Param("size") Integer size,HttpServletRequest request) throws MyException {
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioSysSettleService.list(pageNo,sizeNo,status,start,end,name,bankCard,username);
	}

	@TokenFinance
	@ApiOperation(value = "财务清算详情", notes = "" ,response=YioSysSettle.class)
	@RequestMapping(value = "/show/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object show(@PathVariable("id") Integer id, HttpServletRequest request) throws MyException {
		return yioSysSettleService.show(id);
	}

	@TokenFinance
	@ApiOperation(value = "驳回", notes = "" ,response=YioSysSettle.class)
	@RequestMapping(value = "/update",method = RequestMethod.PUT,produces = "application/json")
	public Object update(@RequestBody YioSysSettleFile file, HttpServletRequest request) throws MyException {
		yioSysSettleService.update(file);
		return null;
	}

	@TokenFinance
	@ApiOperation(value = "确认", notes = "" ,response=YioSysSettle.class)
	@RequestMapping(value = "/updateEnter",method = RequestMethod.PUT,produces = "application/json")
	public Object updateEnter(@RequestBody YioSysSettleFile file, HttpServletRequest request) throws MyException {
		yioSysSettleService.updateEnter(file);
		return null;
	}
}
