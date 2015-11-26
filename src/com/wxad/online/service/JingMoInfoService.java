package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.JingMoInfo;
import com.wxad.online.domain.JingMoInfoList;
import com.wxad.online.persistence.JingMoInfoMapper;

/**
 * 系统用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 */
@Service
public class JingMoInfoService  extends SimpleCacheSupportService<JingMoInfo> {
	
	@Resource
	private JingMoInfoMapper jingMoInfoMapper;
	@Autowired
	public void setJingMoInfoMapper(JingMoInfoMapper jingMoInfoMapper) {
		this.mapper = jingMoInfoMapper;
		this.jingMoInfoMapper=jingMoInfoMapper;
	}
	public List<JingMoInfoList> countActivity(Paginator paginator){
		return jingMoInfoMapper.countActivity(paginator);
	}
	public int countAll(Paginator paginator){
		return jingMoInfoMapper.countAll(paginator);
	}
}
