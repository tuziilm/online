package com.wxad.online.common;

public class ChannelQuery extends com.wxad.online.common.Query {

	public String channel;
	public String salesman;

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public ChannelQuery() {

	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
		addItem("channel", channel);
	}
}
