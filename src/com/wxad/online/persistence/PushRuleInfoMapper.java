package com.wxad.online.persistence;

import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.RegisterInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface PushRuleInfoMapper extends BaseMapper<PushRuleInfo>{
	
	int insertPushRuleInfo(PushRuleInfo pushRuleInfo);

	PushRuleInfo getByPushId(String pushId);
	
	PushRuleInfo getById(String id);
}