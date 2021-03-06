package com.wxad.online.domain;

/**
 * 上传数据
 * @author xuzhenqin
 *
 */
public class UploadData extends RemarkId {	
	/**
	 * uuid
	 */
	private String uuid;
	
	/**
	 * 当前版本
	 */
	private String version;
	
	
	private String currentVersion;
	private String saveVersion;
	
	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getSaveVersion() {
		return saveVersion;
	}

	public void setSaveVersion(String saveVersion) {
		this.saveVersion = saveVersion;
	}

	/**
	 * 网络类型
	 */
	private String netType;
	
	/**
	 * 渠道号
	 */
	private String channel;
	


	
	/**
	 * 是否是平板(可能不准确)
	 */
	private String isTablet;
	
	/**
	 * 请求时间
	 */
	private String datetime;
	
	/**
	 * mac地址
	 */
	private String macAddress;
	
	/**
	 * 系统版本
	 */
	private String systemVersion;
	
	/**
	 * sdk版本
	 */
	private String sdkVersion;

	/**
	 * 设置型号
	 */
	private String model;
	
	/**
	 * 系统语言(国家代号_语言代号)
	 */
	private String language;
	
	/**
	 * 屏蔽尺寸
	 */
	private String size;

	/**
	 * 屏幕分辨率
	 */
	private String resolution;
	
	/**
	 * ram大小(可用容量_总容量)
	 */
	private String ram;
	
	/**
	 * rom大小(可用容量_总容量)
	 */
	private String rom;
	
	/**
	 * 是否有SIM卡
	 */
	private String hasSim;
	
	/**
	 * 是否更新设备信息
	 */
	private String isUpdateDeviceInfo;
	
	/**
	 * 国家区域
	 */
	private String country;
	
	/**
	 * 是否root
	 */
	private String isRoot;
	
//	private String email;
//	
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	/**
	 * 获得root
	 * @return
	 */
	public String getIsRoot() {
		return isRoot;
	}

	/**
	 * 设置root
	 * @param isRoot
	 */
	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}

	/**
	 * hardware
	 */
	private String hardware;
	
	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	/**
	 * 获得是否更新设备信息
	 */
	public String getIsTablet() {
		return isTablet;
	}

	/**
	 * 设置是否更新设备信息
	 */
	public void setIsTablet(String isTablet) {
		this.isTablet = isTablet;
	}

	/**
	 * 获得屏蔽尺寸
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 设置屏蔽尺寸
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 获得ram大小(可用容量_总容量)
	 * @return
	 */
	public String getRam() {
		return ram;
	}

	/**
	 * 设置ram大小(可用容量_总容量)
	 * @return
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}

	/**
	 * 获得rom大小(可用容量_总容量)
	 * @return
	 */
	public String getRom() {
		return rom;
	}

	/**
	 * 设置rom大小(可用容量_总容量)
	 * @return
	 */
	public void setRom(String rom) {
		this.rom = rom;
	}

	/**
	 * 获得是否有SIM卡
	 * @return
	 */
	public String getHasSim() {
		return hasSim;
	}

	/**
	 * 设置是否有SIM卡
	 * @return
	 */
	public void setHasSim(String hasSim) {
		this.hasSim = hasSim;
	}

	/**
	 * 获得是否更新设备信息
	 * @return
	 */
	public String getIsUpdateDeviceInfo() {
		return isUpdateDeviceInfo;
	}

	/**
	 * 设置是否更新设备信息
	 * @return
	 */
	public void setIsUpdateDeviceInfo(String isUpdateDeviceInfo) {
		this.isUpdateDeviceInfo = isUpdateDeviceInfo;
	}

	/**
	 * 获得渠道号
	 * @return
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 设置渠道号
	 * @return
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}


	/**
	 * 获得请求时间
	 * @return
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * 设置请求时间
	 * @return
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	/**
	 * 获得mac地址
	 * @return
	 */
	public String getMacAddress() {
		return macAddress;
	}


	/**
	 * 设置mac地址
	 * @return
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	/**
	 * 获得系统版本
	 * @return
	 */
	public String getSystemVersion() {
		return systemVersion;
	}

	/**
	 * 设置系统版本
	 * @return
	 */
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	/**
	 * 获得sdk版本
	 * @return
	 */
	public String getSdkVersion() {
		return sdkVersion;
	}

	/**
	 * 设置sdk版本
	 * @return
	 */
	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	/**
	 * 获得设置型号
	 * @return
	 */
	public String getModel() {
		return model;
	}

	/**
	 * 设置设置型号
	 * @return
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * 获得系统语言(国家代号_语言代号)
	 * @return
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置系统语言(国家代号_语言代号)
	 * @return
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获得屏幕分辨率
	 * @return
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * 设置屏幕分辨率
	 * @return
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * 获得国家区域
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 设置国家区域
	 * @return
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 设置uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 设置uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 获得当前版本
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置当前版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获得网络类型
	 */
	public String getNetType() {
		return netType;
	}

	/**
	 * 设置网络类型
	 */
	public void setNetType(String netType) {
		this.netType = netType;
	}
}
