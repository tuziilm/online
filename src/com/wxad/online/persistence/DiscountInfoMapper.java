package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.DiscountInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface DiscountInfoMapper extends BaseMapper<DiscountInfo>{
	
	int insertSalesStatisticsInfo(DiscountInfo appInfo);
	
	DiscountInfo getById(String id);
	
	List<DiscountInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}