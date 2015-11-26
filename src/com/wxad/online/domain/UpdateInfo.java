package com.wxad.online.domain;

/**
 * 更新信息
 * @author xuzhenqin
 *
 */
public class UpdateInfo extends RemarkId {
	
	/**
	 * (版本号)
	 */
	private String version;
	
	/**
	 * (下载地址)
	 */
	private String url;
	
	/**
	 * (哈希值)
	 */
	private String hash;
	
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
	 * 获得(下载地址)
	 * @return
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * 设置(下载地址)
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获得(哈希值)
	 * @return
	 */
	public String getHash() {
		return hash;
	}


	/**
	 * 设置(哈希值)
	 * @param hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
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
