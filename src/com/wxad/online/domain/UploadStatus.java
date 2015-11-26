package com.wxad.online.domain;

public class UploadStatus extends RemarkId {
	
	/**
	 * 获得uuid
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 设置uuid
	 * @return
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获得包名
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * 设置包名
	 * @return
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * 获得版本号
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置版本号
	 * @return
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 获得动作:active、delete、download、install
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 设置动作:active、delete、download、install
	 * @return
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * 获得状态:fail、success
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态:fail、success
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 获得备注信息，比如下载失败的时候是下载失败的原因
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置备注信息，比如下载失败的时候是下载失败的原因
	 * @return
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 获得更新反馈时间
	 * @return
	 */
	public String getDatetime() {
		return datetime;
	}
	
	/**
	 * 设置更新反馈时间
	 * @return
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	/**
	 * uuid
	 */
	private String uuid;
	
	/**
	 * (包名)
	 */
	private String packageName;
	
	/**
	 * (版本号)
	 */
	private String version;
	
	/**
	 * (动作:active、delete、download、install)
	 */
	private String action;
	
	/**
	 * (状态:fail、success)
	 */
	private String state;
	
	/**
	 * (备注信息，比如下载失败的时候是下载失败的原因)
	 */
	private String msg;
	
	/**
	 * upload 时间
	 */
	private String datetime;
}
