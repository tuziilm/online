package com.wxad.online.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxad.online.common.IdForm;
import com.wxad.online.common.Paginator;
import com.wxad.online.common.Query;
import com.wxad.online.domain.PushStatusBarInfo;
import com.wxad.online.service.PushStatusBarInfoService;
import com.wxad.online.service.PushStatusBarService;

@Controller
@RequestMapping("/push/statusbar")
public class PushStatusBarInfoController extends CRUDController<PushStatusBarInfo, PushStatusBarInfoService, PushStatusBarInfoController.Form, Query.NameQuery>{
	@Resource
	private PushStatusBarService pushStatusBarService;
	
	public PushStatusBarInfoController() {
		super("push/statusbar");
	}
	@Resource
	public void setPushStatusBarInfoService(PushStatusBarInfoService pushStatusBarInfoService){
		this.service=pushStatusBarInfoService;
	}

    @Override
    protected boolean preList(int page, Paginator paginator, Query.NameQuery query, Model model) {
        paginator.setNeedTotal(true);
        return super.preList(page, paginator, query, model);
    }
    @Override
    protected void postCreate(Model model) {
    	model.addAttribute("pushStatusBarList", pushStatusBarService.getAllPushRulesCache());
    }
    @Override
    protected void postModify(int id, PushStatusBarInfo obj, Model model) {
    	postCreate(model);
    }
    @Override
    protected void onSaveError(Form form, BindingResult errors, Model model,
    		HttpServletRequest request, HttpServletResponse response) {
    	postCreate(model);
    }
    @Override
	public void innerSave(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
    	PushStatusBarInfo appInfo=form.toObj();
		try{
            service.saveOrUpdate(appInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends IdForm<PushStatusBarInfo> {
        
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

		@Override
		public PushStatusBarInfo newObj() {
			return new PushStatusBarInfo();
		}
		@Override
		public void onlineulateObj(PushStatusBarInfo obj) {
			obj.setMsg(msg);
			obj.setBusinessType(businessType);
			obj.setUrl(url);
			obj.setShowType(showType);
			obj.setPackageName(packageName);
			obj.setImageUrl(imageUrl);
			obj.setIconUrl(iconUrl);
			obj.setTitle(title);
			obj.setContent(content);
			obj.setPushId(pushId);
		}
        
    }
}
