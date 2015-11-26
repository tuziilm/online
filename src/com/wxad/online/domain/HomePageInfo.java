package com.wxad.online.domain;

/**
 * 更新信息
 * @author xuzhenqin
 *
 */
public class HomePageInfo extends RemarkId {
	
	/**
	 * (版本号)
	 */
	private String version;
	
	/**
	 * (首页地址)
	 */
	private String url;
	
	/**
	 * pushId
	 */
	private Integer pushId;

	/**
	 * 获得(版本号)
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置(版本号)
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获得(首页地址)
	 * @return
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * 设置(首页地址)
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获得push id
	 * @return
	 */
	public Integer getPushId() {
		return pushId;
	}


	/**
	 * 设置push id
	 * @param pushId
	 */
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}

}
