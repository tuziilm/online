package com.wxad.base.util;

import java.util.ArrayList;
import java.util.List;

import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.UpdateInfo;

/**
 * push���ݽӿ�
 * @author xuzhenqin
 *
 */
public class IPushRuleInfo {

	/**
	 * ���ڱ��������Ϣ
	 */
	public static List<PushRuleInfo> pushRuleInfoList = new ArrayList<PushRuleInfo>();
	
	/**
	 * ���ڱ���������Ϣ
	 */
	public static UpdateInfo updateInfo = new UpdateInfo();
	
	/**
	 * ���ڱ���������Ϣ
	 */
	public static List<AppInfo> appInfoList = new ArrayList<AppInfo>();
	
	/**
	 * ���ڱ���ɾ����Ϣ
	 */
	public static List<DeleteInfo> deleteInfoList = new ArrayList<DeleteInfo>();
	
	/**
	 * ���ڱ��漤����Ϣ
	 */
	public static List<ActiveInfo> activeInfoList = new ArrayList<ActiveInfo>();
	
}
