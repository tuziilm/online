package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.QQStatisticsInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface QQStatisticsInfoMapper extends BaseMapper<QQStatisticsInfo>{
	
	int insertQQStatisticsInfo(QQStatisticsInfo appInfo);
	
	QQStatisticsInfo getById(String id);
	
	List<QQStatisticsInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}