package com.otc.api.web;

import java.util.List;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioRedLog;
import com.otc.api.service.YioRedLogService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

@Api("红包日志")
@RestController
@RequestMapping(value = "/yioRedLog")
public class YioRedLogController {

	@Autowired
	private YioRedLogService yioRedLogService;

	//@Token
	@ApiOperation(value = "点击支付埋点", notes = "" ,response=YioRedLog.class)
	@RequestMapping(value = "/{id}" ,method = RequestMethod.POST,produces = "application/json")
	public Object create(@PathVariable("id") Integer id) throws MyException {
		yioRedLogService.create(id);
		return null;
	}
}
