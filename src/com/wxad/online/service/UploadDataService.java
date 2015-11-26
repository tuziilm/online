package com.wxad.online.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxad.online.domain.UploadData;
import com.wxad.online.persistence.UploadDataMapper;

/**
 * 系统用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class UploadDataService  extends SimpleCacheSupportService<UploadData> {
	@Resource
	private UploadDataMapper uploadDataMapper;
	
	public int insertUploadData(UploadData upload){
		return uploadDataMapper.insertUploadData(upload);
	}

	public UploadData getById(String username) {
		return uploadDataMapper.getById(username);
	}
	
	public List<UploadData> getByUuid(String uuid) {
		return uploadDataMapper.getByUuid(uuid);
	}
}
