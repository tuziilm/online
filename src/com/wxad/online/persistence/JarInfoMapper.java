package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.JarInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface JarInfoMapper extends BaseMapper<JarInfo>{
	
	JarInfo getByPushId(int pushId);
}