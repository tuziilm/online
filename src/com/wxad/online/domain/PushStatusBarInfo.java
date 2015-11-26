package com.wxad.online.domain;

/**
 * 需要PUSH的APP对象
 * @author xuzhenqin
 *
 */
public class PushStatusBarInfo extends RemarkId {
	
	
	private int businessType;
	private String url;
	private int showType;
	private String packageName;
	private String imageUrl;
	private String iconUrl;
	private String title;
	private String content;
	private String msg;
	
	/**
	 * pushId
	 */
	private Integer pushId;
	
	public Integer getPushId() {
		return pushId;
	}
	public void setPushId(Integer pushId) {
		this.pushId = pushId;
	}
	public int getBusinessType() {
		return businessType;
	}
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
