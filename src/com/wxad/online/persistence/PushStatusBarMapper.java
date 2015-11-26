package com.wxad.online.persistence;

import com.wxad.online.domain.PushStatusBar;
import com.wxad.online.domain.RegisterInfo;

/**
 * ibatis操作push总控制表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushStatusBarMapper extends BaseMapper<PushStatusBar>{
	
	int insertPushStatusBar(PushStatusBar pushStatusBar);

	PushStatusBar getByPushId(String pushId);
	
	PushStatusBar getById(String id);
}