package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.EkenAppInfo;
import com.wxad.online.persistence.EkenAppInfoMapper;

@Service
public class EkenAppInfoService  extends BaseService<EkenAppInfo>{
	private EkenAppInfoMapper ekenAppInfoMapper;
	
	@Autowired
	public void setEkenAppInfoMapper(EkenAppInfoMapper ekenAppInfoMapper) {
		this.mapper = ekenAppInfoMapper;
		this.ekenAppInfoMapper=ekenAppInfoMapper;
	}
	public EkenAppInfo getByPushId(int pushId){
		return ekenAppInfoMapper.getByPushId(pushId);
	}
	
	public List<EkenAppInfo> getByChannel(String channel){
		return ekenAppInfoMapper.getByChannel(channel);
	}
}
