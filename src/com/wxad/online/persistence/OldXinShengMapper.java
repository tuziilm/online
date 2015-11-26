package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.OldXinSheng;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface OldXinShengMapper extends BaseMapper<OldXinSheng>{

	List<OldXinSheng> getByUuid(String uuid);
}