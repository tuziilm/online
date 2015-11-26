package com.wxad.online.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.PushStatusBar;
import com.wxad.online.persistence.PushStatusBarMapper;

/**
 * 参数信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class PushStatusBarService extends ObjectBasedGroupCacheSupportService<PushStatusBar> {
	private final static String LIST_ALL_KEY="list_all_key";
    private final static String MAP_ALL_KEY="map_all_key";

	private PushStatusBarMapper pushStatusBarMapper;
	@Autowired
	public void setLinkMapper(PushStatusBarMapper PushStatusBarMapper) {
		this.mapper = PushStatusBarMapper;
		this.pushStatusBarMapper=PushStatusBarMapper;
	}
	@Override
	public String[] cacheGroupKeys() {
		return new String[]{LIST_ALL_KEY, MAP_ALL_KEY};
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
	public void updateCacheList(Map<String, Object> update, PushStatusBar t) {
		 ((List<PushStatusBar>)update.get(LIST_ALL_KEY)).add(t);
	     ((Map<Integer, PushStatusBar>)update.get(MAP_ALL_KEY)).put(t.getId(), t);
		
	}
	  public List<PushStatusBar> getAllPushRulesCache(){
	        return (List<PushStatusBar>)getCache(LIST_ALL_KEY);
	    }

	    public Map<Integer, PushStatusBar> getAllPushRulesMapCache(){
	        return (Map<Integer, PushStatusBar>)getCache(MAP_ALL_KEY);
	    }
}
