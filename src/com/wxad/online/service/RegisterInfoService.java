package com.wxad.online.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.RegisterInfoList;
import com.wxad.online.persistence.RegisterInfoMapper;

/**
 * 系统用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class RegisterInfoService  extends SimpleCacheSupportService<RegisterInfo> {
	
	@Resource
	private RegisterInfoMapper registerInfoMapper;
	@Autowired
	public void setRegisterInfoMapper(RegisterInfoMapper registerInfoMapper) {
		this.mapper = registerInfoMapper;
		this.registerInfoMapper=registerInfoMapper;
	}
	
	public List<RegisterInfo> getByUuid(String uuid) {
		return registerInfoMapper.getByUuid(uuid);
	}

	public List<RegisterInfoList>  countAll(Map map){
		return registerInfoMapper.countAll(map);
	}
}
