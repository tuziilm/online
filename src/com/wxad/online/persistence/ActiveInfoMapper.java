package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface ActiveInfoMapper extends BaseMapper<ActiveInfo>{
	
	int insertActiveInfo(ActiveInfo activeInfo);

	List<ActiveInfo>  getByPushId(int pushId);
	
	ActiveInfo getById(String id);
}