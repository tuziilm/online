package com.wxad.online.domain;

/**
 * ɾ��ָ����������
 * @author xuzhenqin
 *
 */
public class DeleteInfo extends RemarkId {
	
	/**
	 * Ҫɾ���İ���
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
	 * ���Ҫɾ���İ���
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * ����Ҫɾ���İ���
	 * @param packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * ���pushId
	 * @return
	 */
	public Integer getPushId() {
		return pushId;
	}
	
	/**
	 * ����pushId
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
