package com.otc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.otc.api.domain.YioSelf;
import com.otc.api.mapper.YioSelfMapper;
import com.otc.api.pojo.user.UserList;
@Service
public class YioSelfService {

	@Autowired
	private YioSelfMapper yioSelfMapper;

	 public List<YioSelf> getAll() {
		return yioSelfMapper.findAll();
	}
	 
	 /**
		 * 交易员自检列表
		 * @param username
		 * @param work
		 * @param page
		 * @param size
		 * @return
		 */
		public PageInfo<YioSelf> list(Integer payType,Integer checkStatus,String qname,Integer pageNo,Integer sizeNo,String transactionAccount){
			PageHelper.startPage(pageNo,sizeNo);
			List<YioSelf> lists = yioSelfMapper.queryForList(payType,checkStatus,qname,transactionAccount);
			PageInfo<YioSelf> info = new PageInfo<>(lists);
			return info;
		}
		
		/**
		 * 变更自检状态
		 * @param id
		 * @param accountStatus
		 * @return
		 */
		public int updateCheckStatus(int id,int accountStatus){
			return yioSelfMapper.updateOpenOrClose(id,accountStatus);
		}
		
		/**
		 * 变更账户状态
		 * @param id
		 * @param accountStatus
		 * @return
		 */
		public int updateAccountStatus(int id,int accountStatus){
			return yioSelfMapper.updateAccountStatus(id,accountStatus);
		}
}
