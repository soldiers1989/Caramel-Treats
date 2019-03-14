package com.otc.api.web;

import java.util.List;

import com.otc.api.aop.Token;
import com.otc.api.pojo.dingGroup.DingGroup;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otc.api.domain.YioDingGroupUser;
import com.otc.api.service.YioDingGroupUserService;
import com.otc.api.exception.MyException;
import com.otc.api.result.ApiResult;

@Api("组用户")
@RestController
@RequestMapping(value = "/yioDingGroupUser")
public class YioDingGroupUserController {

	@Autowired
	private YioDingGroupUserService yioDingGroupUserService;

	@Token
	@ApiOperation(value = "分配组用户", notes = "" ,response=YioDingGroupUser.class)
	@RequestMapping(method = RequestMethod.POST,produces = "application/json")
	public Object create(@RequestBody DingGroup dingGroup) throws MyException {

		return "";
	}
}
