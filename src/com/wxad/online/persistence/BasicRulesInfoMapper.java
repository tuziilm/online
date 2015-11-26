package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.BasicRulesInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface BasicRulesInfoMapper extends BaseMapper<BasicRulesInfo>{
	
	int insertBasicRulesInfo(BasicRulesInfo deleteInfo);

	List<BasicRulesInfo> getByChannel(String channel);
	
	BasicRulesInfo getById(String id);
}