package com.wxad.online.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.PushStatusBarInfo;
import com.wxad.online.domain.PushStatusBarInfo;
import com.wxad.online.persistence.PushStatusBarInfoMapper;
import com.wxad.online.persistence.PushStatusBarInfoMapper;

/**
 * 参数信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class PushStatusBarInfoService extends BaseService<PushStatusBarInfo>{
	private PushStatusBarInfoMapper pushStatusBarInfoMapper;
	
	@Autowired
	public void setPushStatusBarInfoMapper(PushStatusBarInfoMapper PushStatusBarInfoMapper) {
		this.mapper = PushStatusBarInfoMapper;
		this.pushStatusBarInfoMapper=PushStatusBarInfoMapper;
	}
	public List<PushStatusBarInfo> getByPushId(int pushId){
		return pushStatusBarInfoMapper.getByPushId(pushId);
	}
}
