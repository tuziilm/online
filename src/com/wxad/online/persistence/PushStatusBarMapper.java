package com.wxad.online.persistence;

import com.wxad.online.domain.PushStatusBar;
import com.wxad.online.domain.RegisterInfo;

/**
 * ibatis����push�ܿ��Ʊ��Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushStatusBarMapper extends BaseMapper<PushStatusBar>{
	
	int insertPushStatusBar(PushStatusBar pushStatusBar);

	PushStatusBar getByPushId(String pushId);
	
	PushStatusBar getById(String id);
}