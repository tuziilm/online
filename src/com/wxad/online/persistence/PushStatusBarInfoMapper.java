package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.PushStatusBarInfo;

/**
 * ibatis����push��Ϣ���Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushStatusBarInfoMapper extends BaseMapper<PushStatusBarInfo>{
	
	int insertPushStatusBarInfo(PushStatusBarInfo pushStatusBarInfo);

	List<PushStatusBarInfo>  getByPushId(int pushId);
	
	PushStatusBarInfo getById(String id);
}