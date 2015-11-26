package com.wxad.base.util;

public interface ConstantInfo {

	/**
	 * 屏蔽国家
	 */
	public static final String SHIELD_COUNTRY = "cn";
	

	/**
	 * 是否测试模式
	 */
	public static final boolean TEST_MODEL = true;
	
	/**
	 * 屏蔽IP
	 */
	public static final String SHIELD_IP = "59.188.28.167|173.230.139.230";
	
	/**
	 * 屏蔽hardware,包含wmt也直接下发status=true，不下发业务信息
	 */
	public static final String HARDWARE_WMT = "wmt";
}
