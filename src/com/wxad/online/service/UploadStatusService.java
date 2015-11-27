package com.wxad.online.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.UploadData;
import com.wxad.online.domain.UploadStatus;
import com.wxad.online.persistence.UploadDataMapper;
import com.wxad.online.persistence.UploadStatusMapper;

@Service
public class UploadStatusService  extends SimpleCacheSupportService<UploadStatus> {
	
	@Resource
	private UploadStatusMapper uploadStatusMapper;

	public int insertUploadStatus(UploadStatus uploadStatus) {
		return uploadStatusMapper.insertUploadStatus(uploadStatus);
	}
	
	public UploadStatus getById(String id) {
		return uploadStatusMapper.getById(id);
	}
	
	public UploadStatus getByUuid(String uuid) {
		return uploadStatusMapper.getByUuid(uuid);
	}
}
