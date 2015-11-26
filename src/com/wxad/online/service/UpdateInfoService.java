package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.UpdateInfo;
import com.wxad.online.persistence.UpdateInfoMapper;

/**
 * 升级信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class UpdateInfoService extends BaseService<UpdateInfo>{
	private UpdateInfoMapper updateInfoMapper;
	
	@Autowired
	public void setUpdateInfoMapper(UpdateInfoMapper updateInfoMapper) {
		this.mapper = updateInfoMapper;
		this.updateInfoMapper=updateInfoMapper;
	}
	public List<UpdateInfo> getByPushId(int pushId){
		return updateInfoMapper.getByPushId(pushId);
	}
}
