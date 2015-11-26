package com.wxad.online.domain;

import com.wxad.online.common.JsonSupport;

/**
 * 销量统计
 * @author Administrator
 *
 */
public class SalesStatisticsInfo extends Id implements JsonSupport {

	/**
	 *  渠道号
	 */
	private String channel;
	
	/**
	 * 时间
	 */
	private String datetime;
	
	/**
	 * 总数
	 */
	private int total;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 总和
	 */
	private int count;
	
	private String salesman;
	
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
