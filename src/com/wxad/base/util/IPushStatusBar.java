package com.wxad.base.util;

import java.util.ArrayList;
import java.util.List;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.PushStatusBar;
import com.wxad.online.domain.PushStatusBarInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * push状态栏数据接口
 * @author xuzhenqin
 *
 */
public class IPushStatusBar {

	/**
	 * 用于保存参数信息
	 */
	public static List<PushStatusBar> pushStatusBarList = new ArrayList<PushStatusBar>();
	
	/**
	 * 用于保存push状态栏信息
	 */
	public static PushStatusBarInfo pushStatusBarInfo = new PushStatusBarInfo();	
}
