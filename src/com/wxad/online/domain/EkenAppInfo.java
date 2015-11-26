package com.wxad.online.domain;

/**
 * EkenAppInfo
 * @author xuzhenqin
 *
 */
public class EkenAppInfo extends RemarkId {
	
	private int pushId;
	
	private String packageName;
	
	private String url;
	
	private String hash;
	
	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getPushId() {
		return pushId;
	}

	public void setPushId(int pushId) {
		this.pushId = pushId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	
}
