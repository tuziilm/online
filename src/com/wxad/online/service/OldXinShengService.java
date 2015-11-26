package com.wxad.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxad.online.domain.OldXinSheng;
import com.wxad.online.persistence.OldXinShengMapper;

/**
 * 升级信息服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class OldXinShengService extends BaseService<OldXinSheng>{
	private OldXinShengMapper oldXinShengMapper;
	
	@Autowired
	public void setOldXinShengMapper(OldXinShengMapper oldXinShengMapper) {
		this.mapper = oldXinShengMapper;
		this.oldXinShengMapper=oldXinShengMapper;
	}
	public List<OldXinSheng> getByUuid(String uuid){
		return oldXinShengMapper.getByUuid(uuid);
	}
}
