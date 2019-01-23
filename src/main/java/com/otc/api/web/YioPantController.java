package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenAdmin;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.otc.api.domain.YioPant;
import com.otc.api.service.YioPantService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

@Api("设备心跳")
@RestController
@RequestMapping(value = "/yioPant")
public class YioPantController {

	@Autowired
	private YioPantService yioPantService;

	@TokenAdmin
	@ApiOperation(value = "设备统计", notes = "" ,response=YioPant.class)
	@RequestMapping(value = "/report",method = RequestMethod.GET,produces = "application/json")
	public Object reprot() throws MyException {
		return yioPantService.report();
	}
}
