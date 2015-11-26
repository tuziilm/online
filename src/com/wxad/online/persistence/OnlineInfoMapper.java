package com.wxad.online.persistence;

import java.util.List;
import java.util.Map;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.OnlineGroupInfo;
import com.wxad.online.domain.OnlineInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.RegisterInfoList;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface OnlineInfoMapper extends BaseMapper<OnlineInfo>{
	
	List<OnlineInfo> getByUuid(String uuid);
	
	List<OnlineGroupInfo> countAll(Map map);
}