package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.PushStatusBarInfo;

/**
 * ibatis操作push信息表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushStatusBarInfoMapper extends BaseMapper<PushStatusBarInfo>{
	
	int insertPushStatusBarInfo(PushStatusBarInfo pushStatusBarInfo);

	List<PushStatusBarInfo>  getByPushId(int pushId);
	
	PushStatusBarInfo getById(String id);
}