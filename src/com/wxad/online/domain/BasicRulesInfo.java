package com.wxad.online.domain;

/**
 * ������Ϣ
 * @author xuzhenqin
 *
 */
public class BasicRulesInfo extends RemarkId {
	
	private String channel;
	
	private int proportion;
	
	private int benchmark;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public int getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(int benchmark) {
		this.benchmark = benchmark;
	}

}
