package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.OnlineStatisticsInfo;
import com.wxad.online.persistence.OnlineStatisticsInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class OnlineStatisticsInfoService  extends SimpleCacheSupportService<OnlineStatisticsInfo> {
	
	@Resource
	private OnlineStatisticsInfoMapper onlineStatisticsInfoMapper;
	@Autowired
	public void setOnlineStatisticsInfoMapper(OnlineStatisticsInfoMapper onlineStatisticsInfoMapper) {
		this.mapper = onlineStatisticsInfoMapper;
		this.onlineStatisticsInfoMapper=onlineStatisticsInfoMapper;
	}
	
	public List<OnlineStatisticsInfo> countActivity(Paginator paginator){
		return onlineStatisticsInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return onlineStatisticsInfoMapper.countAll(paginator);
	}
}
