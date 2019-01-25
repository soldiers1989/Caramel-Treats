package com.otc.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otc.api.domain.YioSysSettleFile;
import com.otc.api.mapper.YioSysSettleFileMapper;
@Service
public class YioSysSettleFileService {

	@Autowired
	private YioSysSettleFileMapper yioSysSettleFileMapper;

	 public List<YioSysSettleFile> getAll() {
		return yioSysSettleFileMapper.findAll();
	}
}
