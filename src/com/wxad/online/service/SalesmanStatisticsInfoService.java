package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanStatisticsInfo;
import com.wxad.online.persistence.SalesmanStatisticsInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class SalesmanStatisticsInfoService  extends SimpleCacheSupportService<SalesmanStatisticsInfo> {
	
	@Resource
	private SalesmanStatisticsInfoMapper salesmanStatisticsInfoMapper;
	@Autowired
	public void setSalesmanStatisticsInfoMapper(SalesmanStatisticsInfoMapper salesmanStatisticsInfoMapper) {
		this.mapper = salesmanStatisticsInfoMapper;
		this.salesmanStatisticsInfoMapper=salesmanStatisticsInfoMapper;
	}
	
	public List<SalesmanStatisticsInfo> countActivity(Paginator paginator){
		return salesmanStatisticsInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return salesmanStatisticsInfoMapper.countAll(paginator);
	}
}
