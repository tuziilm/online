package com.wxad.online.domain;

import com.wxad.online.common.JsonSupport;

/**
 * �·�ҵ��������Ϣ
 * 
 * @author xuzhenqin
 *
 */
public class PushStatisticsInfo extends Id implements JsonSupport {

	private String datakey;
	private String value;
	
	public String getDatakey() {
		return datakey;
	}
	
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
