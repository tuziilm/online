package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.CalculatorBusinessInfo;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface CalculatorBusinessInfoMapper extends BaseMapper<CalculatorBusinessInfo>{

	CalculatorBusinessInfo getById(int id);
}