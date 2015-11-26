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
import com.wxad.online.domain.PushStatusBar;
import com.wxad.online.service.PushStatusBarService;

/**
 * push规则入口
 * @author <a href="tuziilm@gmail.com">tuziilm</a>
 *
 */
@Controller
@RequestMapping("/pushStatusBar")
public class PushStatusBarController extends CRUDController<PushStatusBar, PushStatusBarService, com.wxad.online.mvc.PushStatusBarController.PushStatusBarForm, Query.NameQuery>{
	public PushStatusBarController() {
		super("pushStatusBar");
	}
	@Resource
	public void setPushStatusBarService(PushStatusBarService PushStatusBarService){
		this.service=PushStatusBarService;
	}
	
	public static class PushStatusBarForm extends IdForm<PushStatusBar> {
		
		private int pushInterval;
		private String pushUrl;
		private Set<String> countriesObject;
		private String size;
		private String ram;
		private String channel;
		private String isTablet;
		private String rom;
		private int isTest;
		private String version;
		private int isMatching;


		public int getPushInterval() {
			return pushInterval;
		}

		public void setPushInterval(int pushInterval) {
			this.pushInterval = pushInterval;
		}

		public String getPushUrl() {
			return pushUrl;
		}

		public void setPushUrl(String pushUrl) {
			this.pushUrl = pushUrl;
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

		public String getRom() {
			return rom;
		}

		public void setRom(String rom) {
			this.rom = rom;
		}

		public int getIsTest() {
			return isTest;
		}

		public void setIsTest(int isTest) {
			this.isTest = isTest;
		}

		public int getIsMatching() {
			return isMatching;
		}

		public void setIsMatching(int isMatching) {
			this.isMatching = isMatching;
		}

		@Override
		public void onlineulateObj(PushStatusBar obj) {
			obj.setPushInterval(pushInterval);
			obj.setPushUrl(pushUrl);
			obj.setChannel(channel);
			obj.setCountriesObject(countriesObject);
			obj.setIsMatching(isMatching);
			obj.setIsTablet(isTablet);
			obj.setIsTest(isTest);
			obj.setRam(ram);
			obj.setRom(rom);
			obj.setSize(size);
			obj.setVersion(version);
			obj.setIsMatching(isMatching);
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
		
		public Set<String> getCountriesObject() {
            return countriesObject;
        }

        public void setCountriesObject(Set<String> countriesObject) {
            this.countriesObject = countriesObject;
        }
		
		@Override
		public PushStatusBar newObj() {
			return new PushStatusBar();
		}
	}

	@Override
	public void innerSave(PushStatusBarForm form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		//执行更新或保存的操作
		PushStatusBar PushStatusBar = form.toObj();
		try{
			service.saveOrUpdate(PushStatusBar);
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
	    protected void postModify(int id, PushStatusBar obj, Model model) {
	        postCreate(model);
	    }

	    @Override
	    protected void onSaveError(PushStatusBarForm form, BindingResult errors, Model model, HttpServletRequest request, HttpServletResponse response) {
	        postCreate(model);
	    }
	 
}
