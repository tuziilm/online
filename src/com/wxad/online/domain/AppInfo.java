package com.wxad.online.domain;

/**
 * 需要静默的APP对象
 * @author xuzhenqin
 *
 */
public class AppInfo extends RemarkId {
	
	/**
	 * (包名)
	 */
	private String packageName;
	
	/**
	 * (下载地址)
	 */
	private String url;
	
	/**
	 * (哈希值)
	 */
	private String hash;
	
	/**
	 * (安装类型:user、system、update)
	 */
	private String type;
	
	/**
	 * (版本号)
	 */
	private String version;
	
	/**
	 * pushId
	 */
	private Integer pushId;
	
	/**
	 * 需要激活的activity包名加类名
	 */
	private String activity;
	
	/**
	 * 是否强制安装
	 */
	private String enforceInstall;
	
	/**
	 *  获得(包名)
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * 设置包名
	 * @param packageName
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 *  获得(下载地址)
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
	 *  获得(哈希值)
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
	 *  获得(安装类型:user、system、update)
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 设置(安装类型:user、system、update)
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 *  获得(版本号)
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
	 *  获得pushId
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

	/**
	 * 获得需要激活的activity包名加类名
	 * @return
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * 设置需要激活的activity包名加类名
	 * @param activity
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getEnforceInstall() {
		return enforceInstall;
	}

	public void setEnforceInstall(String enforceInstall) {
		this.enforceInstall = enforceInstall;
	}

}
