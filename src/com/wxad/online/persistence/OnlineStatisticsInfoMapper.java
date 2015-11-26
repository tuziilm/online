package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.OnlineStatisticsInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface OnlineStatisticsInfoMapper extends BaseMapper<OnlineStatisticsInfo>{
	
	int insertSalesStatisticsInfo(OnlineStatisticsInfo appInfo);
	
	OnlineStatisticsInfo getById(String id);
	
	List<OnlineStatisticsInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}