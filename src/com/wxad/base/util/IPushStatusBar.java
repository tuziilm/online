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
 * push״̬�����ݽӿ�
 * @author xuzhenqin
 *
 */
public class IPushStatusBar {

	/**
	 * ���ڱ��������Ϣ
	 */
	public static List<PushStatusBar> pushStatusBarList = new ArrayList<PushStatusBar>();
	
	/**
	 * ���ڱ���push״̬����Ϣ
	 */
	public static PushStatusBarInfo pushStatusBarInfo = new PushStatusBarInfo();	
}
