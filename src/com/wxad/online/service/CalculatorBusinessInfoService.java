package com.wxad.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain. CalculatorBusinessInfo;
import com.wxad.online.persistence. CalculatorBusinessInfoMapper;

/**
 * 升级信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class CalculatorBusinessInfoService extends BaseService< CalculatorBusinessInfo>{
	private  CalculatorBusinessInfoMapper  CalculatorBusinessInfoMapper;
	
	@Autowired
	public void setCalculatorBusinessInfoMapper( CalculatorBusinessInfoMapper  CalculatorBusinessInfoMapper) {
		this.mapper =  CalculatorBusinessInfoMapper;
		this. CalculatorBusinessInfoMapper= CalculatorBusinessInfoMapper;
	}
	public CalculatorBusinessInfo getById(int id){
		return  CalculatorBusinessInfoMapper.getById(id);
	}
}
