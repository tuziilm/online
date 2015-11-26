package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.BasicRulesInfo;
import com.wxad.online.persistence.BasicRulesInfoMapper;

/**
 * 升级信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class BasicRulesInfoService extends BaseService<BasicRulesInfo>{
	private BasicRulesInfoMapper basicRulesInfoMapper;
	
	@Autowired
	public void setBasicRulesInfoMapper(BasicRulesInfoMapper BasicRulesInfoMapper) {
		this.mapper = BasicRulesInfoMapper;
		this.basicRulesInfoMapper=BasicRulesInfoMapper;
	}
	public List<BasicRulesInfo> getByChannel(String channel){
		return basicRulesInfoMapper.getByChannel(channel);
	}
}
