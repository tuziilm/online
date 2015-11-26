package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.UploadData;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface UploadDataMapper extends BaseMapper<UploadData>{
	
	int insertUploadData(UploadData upload);

	List<UploadData> getByUuid(String uuid);
	
	UploadData getById(String id);
}