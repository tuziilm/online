package com.wxad.online.domain;

import com.wxad.online.common.JsonSupport;

/**
 * ����ͳ��
 * @author Administrator
 *
 */
public class SalesStatisticsInfoDateTimeSummary {

	/**
	 *  ������
	 */
	private String channel;
	
	/**
	 * ʱ��
	 */
	private String datetime;
	
	/**
	 * ����
	 */
	private String total;
	
	
	
	/**
	 * �ܺ�
	 */
	private int count;
	
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
