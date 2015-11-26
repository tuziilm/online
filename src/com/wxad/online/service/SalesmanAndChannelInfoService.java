package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanAndChannelInfo;
import com.wxad.online.persistence.SalesmanAndChannelInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class SalesmanAndChannelInfoService  extends SimpleCacheSupportService<SalesmanAndChannelInfo> {
	
	@Resource
	private SalesmanAndChannelInfoMapper salesmanAndChannelInfoMapper;
	@Autowired
	public void setSalesmanAndChannelInfoMapper(SalesmanAndChannelInfoMapper salesmanAndChannelInfoMapper) {
		this.mapper = salesmanAndChannelInfoMapper;
		this.salesmanAndChannelInfoMapper=salesmanAndChannelInfoMapper;
	}
	
	public List<SalesmanAndChannelInfo> countActivity(Paginator paginator){
		return salesmanAndChannelInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return salesmanAndChannelInfoMapper.countAll(paginator);
	}
	
	
	public SalesmanAndChannelInfo getByChannel(String channel){
		return salesmanAndChannelInfoMapper.getByChannel(channel);
	}
}
