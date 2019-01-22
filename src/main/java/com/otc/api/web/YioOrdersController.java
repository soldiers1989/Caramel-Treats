package com.otc.api.web;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.otc.api.domain.YioOrders;
import com.otc.api.service.YioOrdersService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

@Api("描述")
@RestController
@RequestMapping(value = "/yioOrders")
public class YioOrdersController {

	@Autowired
	private YioOrdersService yioOrdersService;

	@ApiOperation(value = "今天", notes = "" ,response=YioOrders.class)
	@RequestMapping(value="/today",method = RequestMethod.GET,produces = "application/json")
	public Object today(@Param("shopname") String shopname, @Param("minPrice") BigDecimal minPrice, @Param("bigPrice") BigDecimal maxPrice,@Param("username") String username){
		yioOrdersService.today(shopname,minPrice,maxPrice,username);
		return null;
	}

}
