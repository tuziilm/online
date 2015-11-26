package com.wxad.online.persistence;

import com.wxad.online.domain.PushStatisticsInfo;

/**
 * ibatis操作push总控制表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushStatisticsInfoMapper extends BaseMapper<PushStatisticsInfo>{
	
	PushStatisticsInfo getByDataKey(String datakey);
}