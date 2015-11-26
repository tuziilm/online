package com.wxad.online.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wxad.online.domain.OnlineGroupInfo;
import com.wxad.online.domain.OnlineInfo;
import com.wxad.online.persistence.OnlineInfoMapper;

/**
 * 系统用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class OnlineInfoService  extends SimpleCacheSupportService<OnlineInfo> {
	
	@Resource
	private OnlineInfoMapper onlineInfoMapper;
	@Autowired
	public void setOnlineInfoMapper(OnlineInfoMapper onlineInfoMapper) {
		this.mapper = onlineInfoMapper;
		this.onlineInfoMapper=onlineInfoMapper;
	}
	
	public List<OnlineInfo> getByUuid(String uuid) {
		return onlineInfoMapper.getByUuid(uuid);
	}

	public List<OnlineGroupInfo>  countAll(Map map){
		return onlineInfoMapper.countAll(map);
	}
}
