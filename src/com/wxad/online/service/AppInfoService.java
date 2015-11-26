package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.AppInfo;
import com.wxad.online.persistence.AppInfoMapper;

/**
 * 激活信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class AppInfoService extends BaseService<AppInfo>{
	private AppInfoMapper appInfoMapper;
	
	@Autowired
	public void setAppInfoMapper(AppInfoMapper appInfoMapper) {
		this.mapper = appInfoMapper;
		this.appInfoMapper=appInfoMapper;
	}
	public List<AppInfo> getByPushId(int pushId){
		return appInfoMapper.getByPushId(pushId);
	}
}
