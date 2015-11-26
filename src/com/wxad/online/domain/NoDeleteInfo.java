package com.wxad.online.domain;

/**
 * 删除指定包名数据
 * @author xuzhenqin
 *
 */
public class NoDeleteInfo extends RemarkId {
	
	/**
	 * 要删除的包名
	 */
	private String packageName;
	
	/**
	 * push id
	 */
	private Integer pushId;
	
	/**
	 * 获得要删除的包名
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * 设置要删除的包名
	 * @param packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * 获得pushId
	 * @return
	 */
	public Integer getPushId() {
		return pushId;
	}
	
	/**
	 * 设置pushId
	 * @param pushId
	 */
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
}
