package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanAndChannelInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface SalesmanAndChannelInfoMapper extends BaseMapper<SalesmanAndChannelInfo>{
	
	int insertSalesStatisticsInfo(SalesmanAndChannelInfo appInfo);
	
	SalesmanAndChannelInfo getById(String id);
	
	SalesmanAndChannelInfo getByChannel(String channel);
	
	List<SalesmanAndChannelInfo> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}