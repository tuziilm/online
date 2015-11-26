package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface UpdateInfoMapper extends BaseMapper<UpdateInfo>{
	
	int insertUpdateInfo(UpdateInfo updateInfo);

	List<UpdateInfo> getByPushId(int pushId);
	
	UpdateInfo getById(String id);
}