package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.EkenAppInfo;
import com.wxad.online.domain.JarInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface EkenAppInfoMapper extends BaseMapper<EkenAppInfo>{
	
	EkenAppInfo getByPushId(int pushId);
	
	List<EkenAppInfo> getByChannel(String channel);
}