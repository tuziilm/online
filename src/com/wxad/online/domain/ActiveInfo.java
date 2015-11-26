package com.wxad.online.domain;

/**
 * 激活信息表
 * @author xuzhenqin
 *
 */
public class ActiveInfo extends RemarkId {
	
	/**
	 * (包名)
	 */
	private String packageName;
	
	/**
	 * (激活次数)
	 */
	private int count;
	
	/**
	 * (多久激活)
	 */
	private String date;
	
	/**
	 * pushId
	 */
	private Integer pushId;
	
	/**
	 * 设置(包名)
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * 获得(包名)
	 * @param packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * 设置(激活次数)
	 * @return
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * 获得(激活次数)
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 设置 (多久激活)
	 * @return
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * 获得(多久激活)
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * 设置pushId
	 * @return
	 */
	public Integer getPushId() {
		return pushId;
	}
	
	/**
	 * 获得
	 * @param pushId
	 */
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

}
