package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.persistence.DeleteInfoMapper;

/**
 * 下发删除信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class DeleteInfoService extends BaseService<DeleteInfo>{
	private DeleteInfoMapper deleteInfoMapper;
	
	@Autowired
	public void setDeleteInfoMapper(DeleteInfoMapper deleteInfoMapper) {
		this.mapper = deleteInfoMapper;
		this.deleteInfoMapper=deleteInfoMapper;
	}
	public List<DeleteInfo> getByPushId(int pushId){
		return deleteInfoMapper.getByPushId(pushId);
	}
}
