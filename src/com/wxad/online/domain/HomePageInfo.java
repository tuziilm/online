package com.wxad.online.domain;

/**
 * ������Ϣ
 * @author xuzhenqin
 *
 */
public class HomePageInfo extends RemarkId {
	
	/**
	 * (�汾��)
	 */
	private String version;
	
	/**
	 * (��ҳ��ַ)
	 */
	private String url;
	
	/**
	 * pushId
	 */
	private Integer pushId;

	/**
	 * ���(�汾��)
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * ����(�汾��)
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * ���(��ҳ��ַ)
	 * @return
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * ����(��ҳ��ַ)
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ���push id
	 * @return
	 */
	public Integer getPushId() {
		return pushId;
	}


	/**
	 * ����push id
	 * @param pushId
	 */
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

}
