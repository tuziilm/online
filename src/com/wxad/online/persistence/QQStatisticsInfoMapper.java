package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.QQStatisticsInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface QQStatisticsInfoMapper extends BaseMapper<QQStatisticsInfo>{
	
	int insertQQStatisticsInfo(QQStatisticsInfo appInfo);
	
	QQStatisticsInfo getById(String id);
	
	List<QQStatisticsInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}