package com.wxad.online.persistence;

import com.wxad.online.domain.SysUser;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser>{

	SysUser getByUsername(String username);
}