package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesStatisticsInfo;
import com.wxad.online.domain.SalesStatisticsInfoDateTimeSummary;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface SalesStatisticsInfoMapper extends BaseMapper<SalesStatisticsInfo>{
	
	int insertSalesStatisticsInfo(SalesStatisticsInfo appInfo);
	
	SalesStatisticsInfo getById(String id);
	
	List<SalesStatisticsInfoDateTimeSummary> countActivity(Paginator paginator);
	
	int countAll(Paginator paginator);
	
	List<SalesStatisticsInfoDateTimeSummary> selectDateTimeTotal(Paginator paginator);
}