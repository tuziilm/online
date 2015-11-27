package com.wxad.online.service;

import com.wxad.online.domain.UploadStatus;
import com.wxad.online.persistence.UploadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SuppressWarnings("ALL")
@Service
public class UploadStatisticsService extends BaseService<UploadStatus> {
	private UploadStatisticsMapper uploadStatisticsMapper;

	@Autowired
	public void setUploadStatisticsMapper(UploadStatisticsMapper uploadStatisticsMapper) {
		this.mapper = uploadStatisticsMapper;
		this.uploadStatisticsMapper =uploadStatisticsMapper;
	}

}
