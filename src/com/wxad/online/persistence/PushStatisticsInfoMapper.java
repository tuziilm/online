package com.wxad.online.persistence;

import com.wxad.online.domain.PushStatisticsInfo;

/**
 * ibatis����push�ܿ��Ʊ��Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushStatisticsInfoMapper extends BaseMapper<PushStatisticsInfo>{
	
	PushStatisticsInfo getByDataKey(String datakey);
}