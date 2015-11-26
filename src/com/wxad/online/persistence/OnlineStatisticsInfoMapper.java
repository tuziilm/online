package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.OnlineStatisticsInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface OnlineStatisticsInfoMapper extends BaseMapper<OnlineStatisticsInfo>{
	
	int insertSalesStatisticsInfo(OnlineStatisticsInfo appInfo);
	
	OnlineStatisticsInfo getById(String id);
	
	List<OnlineStatisticsInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}