package com.wxad.online.persistence;

import java.util.List;
import java.util.Map;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanDiscountInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface SalesmanDiscountInfoMapper extends BaseMapper<SalesmanDiscountInfo>{
	
	int insertSalesStatisticsInfo(SalesmanDiscountInfo appInfo);
	
	SalesmanDiscountInfo getById(String id);
	
	List<SalesmanDiscountInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
	
	SalesmanDiscountInfo getSalesmanAndChannel(Map map);
}