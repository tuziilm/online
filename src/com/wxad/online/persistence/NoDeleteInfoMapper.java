package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.NoDeleteInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface NoDeleteInfoMapper extends BaseMapper<NoDeleteInfo>{
	
	int insertDeleteInfo(NoDeleteInfo noDeleteInfo);

	List<NoDeleteInfo> getByPushId(int pushId);
	
	NoDeleteInfo getById(String id);
}