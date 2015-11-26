package com.wxad.online.domain;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.wxad.online.common.JsonSupport;

/**
 * 下发业务条件信息
 * 
 * @author xuzhenqin
 *
 */
public class PushStatusBar extends Id implements JsonSupport {

	private int pushInterval;
	
	
	private int requestInterval;
	private int activeTime;
	
	
	
	private String pushUrl;
	private Set<String> country;
	private String size;
	private String ram;
	private String channel;
	private String isTablet;
	private String rom;
	private int isTest;
	private String version;
	private int isMatching;
	
	


	public int getRequestInterval() {
		return requestInterval;
	}

	public void setRequestInterval(int requestInterval) {
		this.requestInterval = requestInterval;
	}

	public int getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(int activeTime) {
		this.activeTime = activeTime;
	}

	public void setCountry(Set<String> country) {
		this.country = country;
	}

	@JsonIgnore
	public String getJsonString() {
		mapper.getSerializationConfig().setSerializationInclusion(
				Inclusion.NON_NULL);
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}


	/**
	 * 获得业务请求地址
	 * 
	 * @return
	 */
	public String getPushUrl() {
		return pushUrl;
	}

	/**
	 * 设置业务请求地址
	 */
	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	/**
	 * 获得屏幕大小
	 * 
	 * @return
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 设置屏幕大小
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 获得ram大小(可用容量_总容量)
	 * 
	 * @return
	 */
	public String getRam() {
		return ram;
	}

	/**
	 * 设置ram大小(可用容量_总容量)
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}

	/**
	 * 获得渠道号
	 * 
	 * @return
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 设置渠道号
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * 获得是否是平板(可能不准确)
	 * 
	 * @return
	 */
	public String getIsTablet() {
		return isTablet;
	}

	/**
	 * 设置是否是平板(可能不准确)
	 */
	public void setIsTablet(String isTablet) {
		this.isTablet = isTablet;
	}

	/**
	 * 获得rom大小(可用容量_总容量)
	 * 
	 * @return
	 */
	public String getRom() {
		return rom;
	}

	/**
	 * 设置rom大小(可用容量_总容量)
	 */
	public void setRom(String rom) {
		this.rom = rom;
	}

	/**
	 * 获取是否需要配置参数值的
	 * 
	 * @return
	 */
	public int getIsMatching() {
		return isMatching;
	}

	/**
	 * 设置是否需要配置参数值的
	 * 
	 * @param isMatching
	 */
	public void setIsMatching(int isMatching) {
		this.isMatching = isMatching;
	}

	public String getCountry() {
		if (country == null || country.isEmpty()) {
			return "";
		}
		return Joiner.on(",").skipNulls().join(country);
	}

	public void setCountry(String country) {
		if (Strings.isNullOrEmpty(country)) {
			return;
		}
		this.country = Sets.newHashSet(Splitter.on(",").omitEmptyStrings()
				.trimResults().split(country));
	}

	public Set<String> getCountriesObject() {
		return this.country;
	}

	public void setCountriesObject(Set<String> country) {
		this.country = country;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getPushInterval() {
		return pushInterval;
	}

	public void setPushInterval(int pushInterval) {
		this.pushInterval = pushInterval;
	}

	public int getIsTest() {
		return isTest;
	}

	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}
}
