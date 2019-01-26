package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenFinance;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioSysBank;
import com.otc.api.service.YioSysBankService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("银行卡维护")
@RestController
@RequestMapping(value = "/yioSysBank")
public class YioSysBankController {

	@Autowired
	private YioSysBankService yioSysBankService;

	@TokenFinance
	@ApiOperation(value = "银行卡列表", notes = "" ,response=YioSysBank.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(@Param("page") Integer page, @Param("size") Integer size,HttpServletRequest request) throws MyException {
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioSysBankService.getAll(pageNo,sizeNo);
	}

	@TokenFinance
	@ApiOperation(value = "新增", notes = "" ,response=YioSysBank.class)
	@RequestMapping(value = "/create",method = RequestMethod.POST,produces = "application/json")
	public Object create(@RequestBody YioSysBank yioSysBank ,HttpServletRequest request) throws MyException {
		yioSysBankService.create(yioSysBank);
		return null;
	}

	@TokenFinance
	@ApiOperation(value = "修改", notes = "" ,response=YioSysBank.class)
	@RequestMapping(value = "/update",method = RequestMethod.PUT,produces = "application/json")
	public Object update(@RequestBody YioSysBank yioSysBank ,HttpServletRequest request) throws MyException {
		yioSysBankService.create(yioSysBank);
		return null;
	}

	@TokenFinance
	@ApiOperation(value = "查看", notes = "" ,response=YioSysBank.class)
	@RequestMapping(value = "/show/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object show(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		return yioSysBankService.show(id);
	}

	@TokenFinance
	@ApiOperation(value = "删除", notes = "" ,response=YioSysBank.class)
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE,produces = "application/json")
	public Object delete(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		yioSysBankService.delete(id);
		return null;
	}

	@TokenFinance
	@ApiOperation(value = "删除", notes = "" ,response=YioSysBank.class)
	@RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.PUT,produces = "application/json")
	public Object updateStatus(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		yioSysBankService.updateStatus(id);
		return null;
	}

}
