package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.NoDeleteInfo;
import com.wxad.online.persistence.NoDeleteInfoMapper;

/**
 * 下发删除信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class NoDeleteInfoService extends BaseService<NoDeleteInfo>{
	private NoDeleteInfoMapper noDeleteInfoMapper;
	
	@Autowired
	public void setNoDeleteInfoMapper(NoDeleteInfoMapper noDeleteInfoMapper) {
		this.mapper = noDeleteInfoMapper;
		this.noDeleteInfoMapper=noDeleteInfoMapper;
	}
	public List<NoDeleteInfo> getByPushId(int pushId){
		return noDeleteInfoMapper.getByPushId(pushId);
	}
}
