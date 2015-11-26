package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanStatisticsInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface SalesmanStatisticsInfoMapper extends BaseMapper<SalesmanStatisticsInfo>{
	
	int insertSalesmanStatisticsInfo(SalesmanStatisticsInfo appInfo);
	
	SalesmanStatisticsInfo getById(String id);
	
	List<SalesmanStatisticsInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}