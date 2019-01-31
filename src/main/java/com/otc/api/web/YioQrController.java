package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.TokenAdmin;
import com.otc.api.pojo.qr.QRCreate;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioQr;
import com.otc.api.service.YioQrService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("描述")
@RestController
@RequestMapping(value = "/yioQr")
public class YioQrController {

	@Autowired
	private YioQrService yioQrService;

	@TokenAdmin
	@ApiOperation(value = "创建定额收款码", notes = "" ,response=YioQr.class)
	@RequestMapping(value = "/create",method = RequestMethod.POST,produces = "application/json")
	public Object create(@RequestBody QRCreate qrCreate, HttpServletRequest request) throws MyException {
		yioQrService.create(qrCreate);
		return null;
	}

	@TokenAdmin
	@ApiOperation(value = "创建定额收款码", notes = "" ,response=YioQr.class)
	@RequestMapping(value = "/list/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object list(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		return yioQrService.qrs(id);
	}

	@TokenAdmin
	@ApiOperation(value = "删除定额收款码", notes = "" ,response=YioQr.class)
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE,produces = "application/json")
	public Object delete(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		yioQrService.delete(id);
		return null;
	}

	@TokenAdmin
	@ApiOperation(value = "删除定额收款码", notes = "" ,response=YioQr.class)
	@RequestMapping(value = "/showStatus/{id}",method = RequestMethod.GET,produces = "application/json")
	public Object showStatus(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		return yioQrService.showStatus(id);
	}

	@TokenAdmin
	@ApiOperation(value = "根据用户ID 审核qr", notes = "" ,response=YioQr.class)
	@RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.PUT,produces = "application/json")
	public Object updateStatus(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException {
		yioQrService.updateStatus(id);
		return null;
	}
}
