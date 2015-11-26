package com.wxad.online.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxad.online.common.IdForm;
import com.wxad.online.common.Paginator;
import com.wxad.online.common.Query;
import com.wxad.online.domain.HomePageInfo;
import com.wxad.online.service.HomePageInfoService;
import com.wxad.online.service.PushRuleInfoService;

@Controller
@RequestMapping("/push/homepage")
public class HomePageInfoController extends CRUDController<HomePageInfo, HomePageInfoService, HomePageInfoController.Form, Query.NameQuery>{
	@Resource
	private PushRuleInfoService pushRuleInfoService;
	
	public HomePageInfoController() {
		super("push/homepage");
	}
	@Resource
	public void setHomePageInfoService(HomePageInfoService updateInfoService){
		this.service=updateInfoService;
	}
	 @Override
	    protected void postCreate(Model model) {
	    	model.addAttribute("pushRuleList", pushRuleInfoService.getAllPushRulesCache());
	    }
	    @Override
	    protected void postModify(int id, HomePageInfo obj, Model model) {
	    	postCreate(model);
	    }
	    @Override
	    protected void onSaveError(Form form, BindingResult errors, Model model,
	    		HttpServletRequest request, HttpServletResponse response) {
	    	postCreate(model);
	    }
    @Override
    protected boolean preList(int page, Paginator paginator, Query.NameQuery query, Model model) {
        paginator.setNeedTotal(true);
        return super.preList(page, paginator, query, model);
    }

    @Override
	public void innerSave(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
    	HomePageInfo updateInfo=form.toObj();
		try{
            service.saveOrUpdate(updateInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends IdForm<HomePageInfo> {
        @NotBlank(message = "version不能为空")
        private String version;
        @NotBlank(message = "url不能为空")
        private String url;
        private Integer pushId;

        @Override
        public HomePageInfo newObj() {
            return new HomePageInfo();
        }

        @Override
        public void onlineulateObj(HomePageInfo updateInfo) {
        	updateInfo.setUrl(url);
        	updateInfo.setVersion(version);
        	updateInfo.setPushId(pushId);
        	
        }

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Integer getPushId() {
			return pushId;
		}

		public void setPushId(Integer pushId) {
			this.pushId = pushId;
		}

    }
}
