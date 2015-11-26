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
 * �·�ҵ��������Ϣ
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
	 * ���ҵ�������ַ
	 * 
	 * @return
	 */
	public String getPushUrl() {
		return pushUrl;
	}

	/**
	 * ����ҵ�������ַ
	 */
	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	/**
	 * �����Ļ��С
	 * 
	 * @return
	 */
	public String getSize() {
		return size;
	}

	/**
	 * ������Ļ��С
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * ���ram��С(��������_������)
	 * 
	 * @return
	 */
	public String getRam() {
		return ram;
	}

	/**
	 * ����ram��С(��������_������)
	 */
	public void setRam(String ram) {
		this.ram = ram;
	}

	/**
	 * ���������
	 * 
	 * @return
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * ����������
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * ����Ƿ���ƽ��(���ܲ�׼ȷ)
	 * 
	 * @return
	 */
	public String getIsTablet() {
		return isTablet;
	}

	/**
	 * �����Ƿ���ƽ��(���ܲ�׼ȷ)
	 */
	public void setIsTablet(String isTablet) {
		this.isTablet = isTablet;
	}

	/**
	 * ���rom��С(��������_������)
	 * 
	 * @return
	 */
	public String getRom() {
		return rom;
	}

	/**
	 * ����rom��С(��������_������)
	 */
	public void setRom(String rom) {
		this.rom = rom;
	}

	/**
	 * ��ȡ�Ƿ���Ҫ���ò���ֵ��
	 * 
	 * @return
	 */
	public int getIsMatching() {
		return isMatching;
	}

	/**
	 * �����Ƿ���Ҫ���ò���ֵ��
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
