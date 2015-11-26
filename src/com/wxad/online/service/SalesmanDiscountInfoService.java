package com.wxad.online.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanDiscountInfo;
import com.wxad.online.persistence.SalesmanDiscountInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class SalesmanDiscountInfoService  extends SimpleCacheSupportService<SalesmanDiscountInfo> {
	
	@Resource
	private SalesmanDiscountInfoMapper salesmanDiscountInfoMapper;
	@Autowired
	public void setSalesmanDiscountInfoMapper(SalesmanDiscountInfoMapper salesmanDiscountInfoMapper) {
		this.mapper = salesmanDiscountInfoMapper;
		this.salesmanDiscountInfoMapper=salesmanDiscountInfoMapper;
	}
	
	public List<SalesmanDiscountInfo> countActivity(Paginator paginator){
		return salesmanDiscountInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return salesmanDiscountInfoMapper.countAll(paginator);
	}
	
	public SalesmanDiscountInfo getSalesmanAndChannel(Map map){
		return salesmanDiscountInfoMapper.getSalesmanAndChannel(map);
	}
}
