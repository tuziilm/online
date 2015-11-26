package com.wxad.online.domain;

public class SalesmanDiscountInfo  extends RemarkId {
	
	private int discount;
	
	private String channel;
	
	private int base;
	
	private String isSystemApp;
	
	private String country;
	
	private String salesman;

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public String getIsSystemApp() {
		return isSystemApp;
	}

	public void setIsSystemApp(String isSystemApp) {
		this.isSystemApp = isSystemApp;
	}
}
