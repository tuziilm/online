package com.wxad.online.persistence;

import java.util.List;

import com.wxad.online.domain.CalculatorBusinessInfo;

/**
 * ibatis����ϵͳ�û����Mapper�ӿ�
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface CalculatorBusinessInfoMapper extends BaseMapper<CalculatorBusinessInfo>{

	CalculatorBusinessInfo getById(int id);
}