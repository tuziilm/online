package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.JingMoInfo;
import com.wxad.online.domain.JingMoInfoList;

/**
 * ibatis操作在线用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface JingMoInfoMapper extends BaseMapper<JingMoInfo>{
	List<JingMoInfoList> countActivity(Paginator paginator);
	int countAll(Paginator paginator);
}