package com.otc.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.domain.YioAccount;
import com.otc.api.domain.YioSysSettleFile;
import com.otc.api.mapper.YioAccountMapper;
import com.otc.api.mapper.YioSysSettleFileMapper;
import com.otc.api.pojo.settle.SettleShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSysSettle;
import com.otc.api.mapper.YioSysSettleMapper;
@Service
public class YioSysSettleService {

	@Autowired
	private YioSysSettleMapper yioSysSettleMapper;

	@Autowired
	private YioSysSettleFileMapper yioSysSettleFileMapper;

	@Autowired
	private YioAccountMapper yioAccountMapper;

	@Value("${URL}")
	private String URL;

	/**
	 * 根据状态查询 清算订单
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<YioSysSettle> list(Integer page,Integer size,Integer status) {
		PageHelper.startPage(page,size);
		List<YioSysSettle> list = yioSysSettleMapper.queryByStatus(status);
		PageInfo<YioSysSettle> info = new PageInfo<>(list);
		return info;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public List<SettleShow> show(Integer id){
		List<SettleShow> settleShows = yioSysSettleMapper.findAllDetail(id);
		List<SettleShow> shows = new ArrayList<>();
		for (SettleShow show : settleShows){
			show.setFileUrl(URL+show.getFileUrl());
			show.setInFileUrl(URL+show.getInFileUrl());
			shows.add(show);
		}
		return shows;
	}

	/**
	 * 驳回
	 * @param yioSysSettle
	 */
	public void update(YioSysSettleFile yioSysSettle){
		YioSysSettle sysSettle = yioSysSettleMapper.findById(yioSysSettle.getId());
		sysSettle.setStatus(3);
		yioSysSettleMapper.update(sysSettle);
		YioSysSettleFile file = yioSysSettleFileMapper.findById(yioSysSettle.getSettleId());
		file.setAmount(yioSysSettle.getAmount());
		yioSysSettleFileMapper.update(file);

	}

	/**
	 * 确认
	 * @param yioSysSettle
	 */
	public void updateEnter(YioSysSettleFile yioSysSettle){
		YioSysSettle sysSettle = yioSysSettleMapper.findById(yioSysSettle.getId());
		sysSettle.setStatus(2);
		yioSysSettleMapper.update(sysSettle);
		YioSysSettleFile file = yioSysSettleFileMapper.findById(yioSysSettle.getSettleId());
		file.setAmount(sysSettle.getAmount());
		file.setInFileUrl(yioSysSettle.getInFileUrl());
		yioSysSettleFileMapper.update(file);
		//解冻结账
		List<YioAccount> accounts = yioAccountMapper.findAllByUserId(sysSettle.getUserId());
		for (YioAccount account : accounts){
			account.setFrozen(account.getFrozen().subtract(sysSettle.getAmount()));
			account.setToken(account.getToken().add(sysSettle.getAmount()));
			yioAccountMapper.update(account);
		}

	}
}
