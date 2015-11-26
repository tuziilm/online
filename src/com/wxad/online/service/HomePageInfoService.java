package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.HomePageInfo;
import com.wxad.online.persistence.HomePageInfoMapper;

/**
 * 升级信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class HomePageInfoService extends BaseService<HomePageInfo>{
	private HomePageInfoMapper homePageInfoMapper;
	
	@Autowired
	public void setHomePageInfoMapper(HomePageInfoMapper homePageInfoMapper) {
		this.mapper = homePageInfoMapper;
		this.homePageInfoMapper=homePageInfoMapper;
	}
	public List<HomePageInfo> getByPushId(int pushId){
		return homePageInfoMapper.getByPushId(pushId);
	}
}
