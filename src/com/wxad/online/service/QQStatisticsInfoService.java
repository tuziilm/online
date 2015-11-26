package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.QQStatisticsInfo;
import com.wxad.online.persistence.QQStatisticsInfoMapper;

/**
 * 激活用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class QQStatisticsInfoService  extends SimpleCacheSupportService<QQStatisticsInfo> {
	
	@Resource
	private QQStatisticsInfoMapper QQStatisticsInfoMapper;
	@Autowired
	public void setQQStatisticsInfoMapper(QQStatisticsInfoMapper QQStatisticsInfoMapper) {
		this.mapper = QQStatisticsInfoMapper;
		this.QQStatisticsInfoMapper=QQStatisticsInfoMapper;
	}
	
	public List<QQStatisticsInfo> countActivity(Paginator paginator){
		return QQStatisticsInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return QQStatisticsInfoMapper.countAll(paginator);
	}
}
