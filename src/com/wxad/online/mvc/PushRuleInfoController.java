package com.wxad.online.mvc;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxad.online.common.Country;
import com.wxad.online.common.IdForm;
import com.wxad.online.common.Paginator;
import com.wxad.online.common.Query;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.service.PushRuleInfoService;

/**
 * push规则入口
 * @author <a href="tuziilm@gmail.com">tuziilm</a>
 *
 */
@Controller
@RequestMapping("/pushRule")
public class PushRuleInfoController extends CRUDController<PushRuleInfo, PushRuleInfoService, com.wxad.online.mvc.PushRuleInfoController.PushRuleInfoForm, Query.NameQuery>{
	public PushRuleInfoController() {
		super("pushRule");
	}
	@Resource
	public void setPushRuleInfoService(PushRuleInfoService pushRuleInfoService){
		this.service=pushRuleInfoService;
	}
	
	public static class PushRuleInfoForm extends IdForm<PushRuleInfo> {
		private int apkNum;
		private String pushUrl;
		private String deviceUrl;
		private String requestRate;
		private Set<String> countriesObject;
		private String size;
		private String ram;
		private String channel;
		private String isTablet;
		private String systemVersion;
		private String sdkVersion;
		private String model;
		private String resolution;
		private String rom;
		private String hasSim;
		private String isRoot;
		private String hardware;
		private int isMatching;
		private int testmodel;
		private String version;
		private int maxInstallCount;
		private int deleteEkenRate;
		private int getEmail;
		private String feedbackUrl;

		public String getFeedbackUrl() {
			return feedbackUrl;
		}

		public void setFeedbackUrl(String feedbackUrl) {
			this.feedbackUrl = feedbackUrl;
		}
		
		public int getMaxInstallCount() {
			return maxInstallCount;
		}

		public void setMaxInstallCount(int maxInstallCount) {
			this.maxInstallCount = maxInstallCount;
		}

		public int getDeleteEkenRate() {
			return deleteEkenRate;
		}

		public void setDeleteEkenRate(int deleteEkenRate) {
			this.deleteEkenRate = deleteEkenRate;
		}

		public int getGetEmail() {
			return getEmail;
		}

		public void setGetEmail(int getEmail) {
			this.getEmail = getEmail;
		}

		public int getApkNum() {
			return apkNum;
		}

		public void setApkNum(int apkNum) {
			this.apkNum = apkNum;
		}

		public String getPushUrl() {
			return pushUrl;
		}

		public void setPushUrl(String pushUrl) {
			this.pushUrl = pushUrl;
		}

		public String getDeviceUrl() {
			return deviceUrl;
		}

		public void setDeviceUrl(String deviceUrl) {
			this.deviceUrl = deviceUrl;
		}

		public String getRequestRate() {
			return requestRate;
		}

		public void setRequestRate(String requestRate) {
			this.requestRate = requestRate;
		}
		
		public Set<String> getCountriesObject() {
            return countriesObject;
        }

        public void setCountriesObject(Set<String> countriesObject) {
            this.countriesObject = countriesObject;
        }

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getRam() {
			return ram;
		}

		public void setRam(String ram) {
			this.ram = ram;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getIsTablet() {
			return isTablet;
		}

		public void setIsTablet(String isTablet) {
			this.isTablet = isTablet;
		}

		public String getSystemVersion() {
			return systemVersion;
		}

		public void setSystemVersion(String systemVersion) {
			this.systemVersion = systemVersion;
		}

		public String getSdkVersion() {
			return sdkVersion;
		}

		public void setSdkVersion(String sdkVersion) {
			this.sdkVersion = sdkVersion;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = resolution;
		}

		public String getRom() {
			return rom;
		}

		public void setRom(String rom) {
			this.rom = rom;
		}

		public String getHasSim() {
			return hasSim;
		}

		public void setHasSim(String hasSim) {
			this.hasSim = hasSim;
		}

		public String getIsRoot() {
			return isRoot;
		}

		public void setIsRoot(String isRoot) {
			this.isRoot = isRoot;
		}

		public String getHardware() {
			return hardware;
		}

		public void setHardware(String hardware) {
			this.hardware = hardware;
		}

		public int getIsMatching() {
			return isMatching;
		}

		public void setIsMatching(int isMatching) {
			this.isMatching = isMatching;
		}

		@Override
		public PushRuleInfo newObj() {
			return new PushRuleInfo();
		}

		@Override
		public void onlineulateObj(PushRuleInfo obj) {
			obj.setApkNum(apkNum);
			obj.setChannel(channel);
			obj.setCountriesObject(countriesObject);
			obj.setDeviceUrl(deviceUrl);
			obj.setHardware(hardware);
			obj.setHasSim(hasSim);
			obj.setIsMatching(isMatching);
			obj.setIsRoot(isRoot);
			obj.setIsTablet(isTablet);
			obj.setModel(model);
			obj.setPushUrl(pushUrl);
			obj.setRam(ram);
			obj.setRequestRate(requestRate);
			obj.setResolution(resolution);
			obj.setRom(rom);
			obj.setSdkVersion(sdkVersion);
			obj.setSize(size);
			obj.setSystemVersion(systemVersion);
			obj.setTestmodel(testmodel);
			obj.setVersion(version);
			obj.setMaxInstallCount(maxInstallCount);
			obj.setDeleteEkenRate(deleteEkenRate);
			obj.setGetEmail(getEmail);
			obj.setFeedbackUrl(feedbackUrl);
		}

		public int getTestmodel() {
			return testmodel;
		}

		public void setTestmodel(int testmodel) {
			this.testmodel = testmodel;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
	}

	@Override
	public void innerSave(PushRuleInfoForm form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		//执行更新或保存的操作
		PushRuleInfo pushRuleInfo = form.toObj();
		try{
			service.saveOrUpdate(pushRuleInfo);
		}catch(DuplicateKeyException e){
			errors.addError(new ObjectError("database", "代号已经存在！"));
		}
	}
	 @Override
	 protected boolean preList(int page, Paginator paginator, Query.NameQuery query, Model model) {
	       paginator.setNeedTotal(true);//分页
	       model.addAttribute("countryMap", Country.shortcut2CountryMap);
	       return super.preList(page, paginator, query, model);
	   }
	 @Override
	    protected void postCreate(Model model) {
	        model.addAttribute("countries", Country.countries);
	    }

	    @Override
	    protected void postModify(int id, PushRuleInfo obj, Model model) {
	        postCreate(model);
	    }

	    @Override
	    protected void onSaveError(PushRuleInfoForm form, BindingResult errors, Model model, HttpServletRequest request, HttpServletResponse response) {
	        postCreate(model);
	    }
	 
}
