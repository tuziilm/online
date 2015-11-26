package com.wxad.online.service;

import com.wxad.online.domain.SysUser;
import com.wxad.online.persistence.SysUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户数据操作服务类
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class SysUserService  extends SimpleCacheSupportService<SysUser> {
	private SysUserMapper sysUserMapper;
	@Autowired
	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.mapper = sysUserMapper;
		this.sysUserMapper=sysUserMapper;
	}

	public SysUser getByUsername(String username) {
		return sysUserMapper.getByUsername(username);
	}
}
