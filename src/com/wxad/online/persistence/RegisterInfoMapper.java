package com.wxad.online.persistence;

import java.util.List;
import java.util.Map;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.RegisterInfoList;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface RegisterInfoMapper extends BaseMapper<RegisterInfo>{
	
	List<RegisterInfo> getByUuid(String uuid);
	
	List<RegisterInfoList> countAll(Map map);
}