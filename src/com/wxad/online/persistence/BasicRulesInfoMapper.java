package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.BasicRulesInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface BasicRulesInfoMapper extends BaseMapper<BasicRulesInfo>{
	
	int insertBasicRulesInfo(BasicRulesInfo deleteInfo);

	List<BasicRulesInfo> getByChannel(String channel);
	
	BasicRulesInfo getById(String id);
}