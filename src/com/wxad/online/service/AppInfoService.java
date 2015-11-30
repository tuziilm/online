package com.wxad.online.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.AppInfo;
import com.wxad.online.persistence.AppInfoMapper;

/**
 * 激活信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class AppInfoService extends ObjectBasedGroupCacheSupportService<AppInfo>{
	private final static String LIST_ALL_KEY="list_all_key";
	private final static String MAP_ALL_KEY="map_all_key";
	private final static String MAP_ALL_PKGNAME_KEY = "map_all_pkgname_key";
	@Override
	public String[] cacheGroupKeys() {
		return new String[]{LIST_ALL_KEY,MAP_ALL_KEY,MAP_ALL_PKGNAME_KEY};
	}

	@Override
	public Object newObject(String cacheGroupKey) {
		if(cacheGroupKey.startsWith("map")){
			return new HashMap();
		}else{
			return new ArrayList();
		}
	}

	@Override
	public void updateCacheList(Map<String, Object> update, AppInfo appInfo) {
		((List<AppInfo>)update.get(LIST_ALL_KEY)).add(appInfo);
		((Map<Integer, AppInfo>)update.get(MAP_ALL_KEY)).put(appInfo.getId(), appInfo);
		((Map<String, AppInfo>)update.get(MAP_ALL_PKGNAME_KEY)).put(appInfo.getPackageName(), appInfo);
	}

	private AppInfoMapper appInfoMapper;
	
	@Autowired
	public void setAppInfoMapper(AppInfoMapper appInfoMapper) {
		this.mapper = appInfoMapper;
		this.appInfoMapper=appInfoMapper;
	}
	public List<AppInfo> getByPushId(int pushId){
		return appInfoMapper.getByPushId(pushId);
	}

	public List<AppInfo> getAllAppInfosCache(){
		return (List<AppInfo>)getCache(LIST_ALL_KEY);
	}

	public Map<Integer, AppInfo> getAllAppInfosMapCache(){
		return (Map<Integer, AppInfo>)getCache(MAP_ALL_KEY);
	}
	public Map<String, AppInfo> getAllAppInfos2PkgNameMapCache(){
		return (Map<String, AppInfo>)getCache(MAP_ALL_PKGNAME_KEY);
	}

}
