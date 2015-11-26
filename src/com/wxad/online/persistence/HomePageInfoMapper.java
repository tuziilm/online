package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.HomePageInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface HomePageInfoMapper extends BaseMapper<HomePageInfo>{

	List<HomePageInfo> getByPushId(int pushId);
	
	HomePageInfo getById(String id);
}