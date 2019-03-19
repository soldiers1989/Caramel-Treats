package com.otc.api.web;

import com.otc.api.aop.Token;
import com.otc.api.domain.YioShop;
import com.otc.api.exception.MyException;
import com.otc.api.pojo.user.Login;
import com.otc.api.service.YioOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Api("首页")
@RestController
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private YioOrdersService yioOrdersService;

    @Token
    @ApiOperation(value = "首页", notes = "" ,response=YioShop.class)
    @RequestMapping(value = "/index/{id}",method = RequestMethod.GET,produces = "application/json")
    public Object index(@PathVariable("id") Integer id,HttpServletRequest request) throws MyException, ParseException {
        YioShop user=(YioShop)request.getAttribute("user");
        return yioOrdersService.index(id,user);
    }

    @Token
    @ApiOperation(value = "首页", notes = "" ,response=YioShop.class)
    @RequestMapping(value = "/indexReport/{id}/{date}",method = RequestMethod.GET,produces = "application/json")
    public Object IndexReport(@PathVariable("id") Integer id,@PathVariable("date") Integer date,HttpServletRequest request) throws MyException {
        YioShop user=(YioShop)request.getAttribute("user");
        return yioOrdersService.IndexReport(user,date,id);
    }
}
