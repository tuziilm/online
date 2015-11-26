package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.JarInfo;
import com.wxad.online.persistence.JarInfoMapper;

@Service
public class JarInfoService  extends BaseService<JarInfo>{
	private JarInfoMapper JarInfoMapper;
	
	@Autowired
	public void setJarInfoMapper(JarInfoMapper JarInfoMapper) {
		this.mapper = JarInfoMapper;
		this.JarInfoMapper=JarInfoMapper;
	}
	public JarInfo getByPushId(int pushId){
		return JarInfoMapper.getByPushId(pushId);
	}
}
