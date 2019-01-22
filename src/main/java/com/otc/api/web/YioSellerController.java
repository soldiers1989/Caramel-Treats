package com.otc.api.web;

import java.util.List;

import io.swagger.annotations.Api;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.otc.api.domain.YioSeller;
import com.otc.api.service.YioSellerService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

@Api("描述")
@RestController
@RequestMapping(value = "/yioSeller")
public class YioSellerController {

	@Autowired
	private YioSellerService yioSellerService;

	//@Token
	//@ApiOperation(value = "接口描述", notes = "" ,response=YioSeller.class)
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},produces = "application/json")
	public Object getList(@Param("page") Integer page,@RequestParam(value = "uuid", required = false) String uuid) throws MyException {
		List<YioSeller> r = yioSellerService.getAll();
		return r;
	}
}
