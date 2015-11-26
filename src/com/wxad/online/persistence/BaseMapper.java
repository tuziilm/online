package com.wxad.online.persistence;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.Id;

import java.util.List;

/**
 * ibatis操作表的Mapper基础接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface BaseMapper<T extends Id> {
	
    int deleteById(Integer id);
    
    int deleteByIds(int ids[]);

    int insert(T channel);

    List<T> selectAll();
    
    List<T> select(Paginator page);

    int count(Paginator page);

    T selectById(Integer id);
    
    int updateByIdSelective(T t);
    
    int updateById(T t);
}