package com.wxad.online.domain;

/**
 * JARÊý¾Ý
 * @author xuzhenqin
 *
 */
public class JarInfo extends RemarkId {
	
	private int pushId;
	
	private String jarVersion;
	
	private String jarUrl;
	
	private String jarHash;

	public int getPushId() {
		return pushId;
	}

	public void setPushId(int pushId) {
		this.pushId = pushId;
	}

	public String getJarVersion() {
		return jarVersion;
	}

	public void setJarVersion(String jarVersion) {
		this.jarVersion = jarVersion;
	}

	public String getJarUrl() {
		return jarUrl;
	}

	public void setJarUrl(String jarUrl) {
		this.jarUrl = jarUrl;
	}

	public String getJarHash() {
		return jarHash;
	}

	public void setJarHash(String jarHash) {
		this.jarHash = jarHash;
	}
}
