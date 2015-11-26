package com.wxad.base.util;

import java.util.ArrayList;
import java.util.List;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * push数据接口
 * @author xuzhenqin
 *
 */
public class IPushRuleInfo {

	/**
	 * 用于保存参数信息
	 */
	public static List<PushRuleInfo> pushRuleInfoList = new ArrayList<PushRuleInfo>();
	
	/**
	 * 用于保存升级信息
	 */
	public static UpdateInfo updateInfo = new UpdateInfo();
	
	/**
	 * 用于保存推送信息
	 */
	public static List<AppInfo> appInfoList = new ArrayList<AppInfo>();
	
	/**
	 * 用于保存删除信息
	 */
	public static List<DeleteInfo> deleteInfoList = new ArrayList<DeleteInfo>();
	
	/**
	 * 用于保存激活信息
	 */
	public static List<ActiveInfo> activeInfoList = new ArrayList<ActiveInfo>();
	
}
