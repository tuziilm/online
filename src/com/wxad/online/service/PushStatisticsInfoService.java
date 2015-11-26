package com.wxad.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.PushStatisticsInfo;
import com.wxad.online.persistence.PushStatisticsInfoMapper;

/**
 * 激活信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class PushStatisticsInfoService extends BaseService<PushStatisticsInfo>{
	private PushStatisticsInfoMapper PushStatisticsInfoMapper;
	
	@Autowired
	public void setPushStatisticsInfoMapper(PushStatisticsInfoMapper PushStatisticsInfoMapper) {
		this.mapper = PushStatisticsInfoMapper;
		this.PushStatisticsInfoMapper=PushStatisticsInfoMapper;
	}
	public PushStatisticsInfo getByDataKey(String datakey){
		return PushStatisticsInfoMapper.getByDataKey(datakey);
	}
}
