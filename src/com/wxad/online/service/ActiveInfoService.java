package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.persistence.ActiveInfoMapper;


/**
 * 激活信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class ActiveInfoService extends BaseService<ActiveInfo>{
	private ActiveInfoMapper activeInfoMapper;
	
	@Autowired
	public void setActiveInfoMapper(ActiveInfoMapper activeInfoMapper) {
		this.mapper = activeInfoMapper;
		this.activeInfoMapper=activeInfoMapper;
	}
	public List<ActiveInfo> getByPushId(int pushId){
		return activeInfoMapper.getByPushId(pushId);
	}
}
