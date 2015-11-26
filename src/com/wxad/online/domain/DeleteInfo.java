package com.wxad.online.domain;

/**
 * 删除指定包名数据
 * @author xuzhenqin
 *
 */
public class DeleteInfo extends RemarkId {
	
	/**
	 * 要删除的包名
	 */
	private String packageName;
	
	/**
	 * push id
	 */
	private Integer pushId;
	
	private String time;
	
	private String version;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
