package com.otc.api.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otc.api.aop.Token;
import com.otc.api.domain.YioSelf;
import com.otc.api.domain.YioSeller;
import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioShopRate;
import com.otc.api.service.YioSelfService;
import com.otc.api.service.YioSellerService;
import com.otc.api.exception.MyException;

@Api("描述")
@RestController
@RequestMapping(value = "/yioSelf")
public class YioSelfController {

	@Autowired
	private YioSelfService yioSelfService;
	
	@Autowired
	private YioSellerService yioSellerService;

	// @Token
	// @ApiOperation(value = "接口描述", notes = "" ,response=YioSelf.class)
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	public Object getList(@Param("page") Integer page, @RequestParam(value = "uuid", required = false) String uuid)
			throws MyException {
		List<YioSelf> r = yioSelfService.getAll();
		return r;
	}

	/**
	 * 
	 * @param payType            支付类型
	 * @param checkStatus        自检状态
	 * @param qname              交易员昵称
	 * @param transactionAccount 交易员账号
	 * @param page
	 * @param size
	 * @return
	 * @throws MyException
	 */
	@Token
	@ApiOperation(value = "自检列表", notes = "", response = YioSelf.class)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public Object list(@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "checkStatus", required = false) Integer checkStatus,
			@RequestParam(value = "qname", required = false) String qname,
			@RequestParam(value = "transactionAccount", required = false) String transactionAccount,
			@Param("page") Integer page, @Param("size") Integer size, HttpServletRequest request) throws MyException {
		YioShop user = (YioShop) request.getAttribute("user");
		int sizeNo = size == null ? 10 : size.intValue();
		int pageNo = page == null ? 1 : page.intValue();
		return yioSelfService.list(payType, checkStatus, qname, pageNo, sizeNo, transactionAccount);
	}

	@ApiOperation(value = "更改自检状态", notes = "", response = YioShopRate.class)
	@RequestMapping(value = "/updateCheckStatus", method = RequestMethod.PUT, produces = "application/json")
	public Object updateCheckStatus(@RequestBody YioSelf ys) {
		return yioSelfService.updateCheckStatus(ys.getId(), ys.getSelfCheckStatus());
	}

	@ApiOperation(value = "检测账户", notes = "", response = YioShopRate.class)
	@RequestMapping(value = "/CheckAccount", method = RequestMethod.POST, produces = "application/json")
	public Object CheckAccount(@RequestBody YioSelf ys) {
		return yioSelfService.updateCheckStatus(ys.getId(), ys.getSelfCheckStatus());
	}

	/**
	 * 1:冻结  2：解冻
	 * @param ys
	 * @return
	 * @throws MyException
	 */
	@ApiOperation(value = "冻结/解冻用户", notes = "", response = YioShopRate.class)
	@RequestMapping(value = "/updateAccountStatus", method = RequestMethod.PUT, produces = "application/json")
	public Object updateAccountStatus(@RequestBody YioSelf ys) throws MyException {
		YioSeller ysr =new YioSeller();
		ysr.setFrozen(ys.getAccountStatus());
		ysr.setIds(ys.getSellerId());
		//冻结支付账户
		yioSellerService.updateStatusList(ysr);
		return yioSelfService.updateCheckStatus(ys.getId(), ys.getAccountStatus());
	}
}
