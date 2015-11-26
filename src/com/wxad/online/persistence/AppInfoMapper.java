package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface AppInfoMapper extends BaseMapper<AppInfo>{
	
	int insertAppInfo(AppInfo appInfo);

	List<AppInfo> getByPushId(int pushId);
	
	AppInfo getById(String id);
}