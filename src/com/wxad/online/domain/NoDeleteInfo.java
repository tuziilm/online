package com.wxad.online.domain;

/**
 * ɾ��ָ����������
 * @author xuzhenqin
 *
 */
public class NoDeleteInfo extends RemarkId {
	
	/**
	 * Ҫɾ���İ���
	 */
	private String packageName;
	
	/**
	 * push id
	 */
	private Integer pushId;
	
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
}
