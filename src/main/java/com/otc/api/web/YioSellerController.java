package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenAdmin;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.otc.api.domain.YioSeller;
import com.otc.api.domain.YioUser;
import com.otc.api.service.YioSellerService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

import javax.servlet.http.HttpServletRequest;

@Api("交易员")
@RestController
@RequestMapping(value = "/yioSeller")
public class YioSellerController {

	@Autowired
	private YioSellerService yioSellerService;

	@TokenAdmin
	@ApiOperation(value = "交易员列表", notes = "" ,response=YioSeller.class)
	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json")
	public Object list(@Param("id") Integer id,@Param("page") Integer page,
					   @Param("size") Integer size,HttpServletRequest request) throws MyException {
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioSellerService.list(id,pageNo,sizeNo);
	}
	
	@TokenAdmin
	@ApiOperation(value = "批量冻结付款账户", notes = "" ,response= YioSeller.class)
	@RequestMapping(value = "/updateStatusList",method = RequestMethod.PUT,produces = "application/json")
	public Object update(@RequestBody YioSeller ids) throws MyException{
		return yioSellerService.updateStatusList(ids);
	}
}
