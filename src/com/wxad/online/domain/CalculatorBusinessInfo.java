package com.wxad.online.domain;

/**
 * ������Ϣ
 * @author xuzhenqin
 *
 */
public class CalculatorBusinessInfo extends RemarkId {
	
	/**
	 * (�汾��)
	 */
	private String version;
	
	/**
	 * (��ҳ��ַ)
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
}
