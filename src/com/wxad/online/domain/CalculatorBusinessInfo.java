package com.wxad.online.domain;

/**
 * 更新信息
 * @author xuzhenqin
 *
 */
public class CalculatorBusinessInfo extends RemarkId {
	
	/**
	 * (版本号)
	 */
	private String version;
	
	/**
	 * (首页地址)
	 */
	private String url;
	
	private String packageName;
	
	private String hash;
	
	private String name;
	
	private String activity;
	
	

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

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
}
