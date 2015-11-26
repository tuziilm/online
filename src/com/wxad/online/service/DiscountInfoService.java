package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.DiscountInfo;
import com.wxad.online.persistence.DiscountInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class DiscountInfoService  extends SimpleCacheSupportService<DiscountInfo> {
	
	@Resource
	private DiscountInfoMapper DiscountInfoMapper;
	@Autowired
	public void setDiscountInfoMapper(DiscountInfoMapper DiscountInfoMapper) {
		this.mapper = DiscountInfoMapper;
		this.DiscountInfoMapper=DiscountInfoMapper;
	}
	
	public List<DiscountInfo> countActivity(Paginator paginator){
		return DiscountInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return DiscountInfoMapper.countAll(paginator);
	}
}
