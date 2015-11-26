package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesStatisticsInfo;
import com.wxad.online.domain.SalesStatisticsInfoDateTimeSummary;
import com.wxad.online.persistence.SalesStatisticsInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class SalesStatisticsInfoService  extends SimpleCacheSupportService<SalesStatisticsInfo> {
	
	@Resource
	private SalesStatisticsInfoMapper salesStatisticsInfoMapper;
	@Autowired
	public void setSalesStatisticsInfoMapper(SalesStatisticsInfoMapper salesStatisticsInfoMapper) {
		this.mapper = salesStatisticsInfoMapper;
		this.salesStatisticsInfoMapper=salesStatisticsInfoMapper;
	}
	
	public List<SalesStatisticsInfoDateTimeSummary> countActivity(Paginator paginator){
		return salesStatisticsInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return salesStatisticsInfoMapper.countAll(paginator);
	}
	
	public List<SalesStatisticsInfoDateTimeSummary> selectDateTimeTotal(Paginator paginator){
		return salesStatisticsInfoMapper.selectDateTimeTotal(paginator);
	}
}
