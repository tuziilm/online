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
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.NoDeleteInfo;
import com.wxad.online.mvc.AppInfoController.Form;
import com.wxad.online.service.NoDeleteInfoService;
import com.wxad.online.service.PushRuleInfoService;

@Controller
@RequestMapping("/push/nodelete")
public class NoDeleteInfoController extends CRUDController<NoDeleteInfo, NoDeleteInfoService, NoDeleteInfoController.Form, Query.NameQuery>{
	@Resource
	private PushRuleInfoService pushRuleInfoService;
	public NoDeleteInfoController() {
		super("push/nodelete");
	}
	@Resource
	public void setNoDeleteInfoService(NoDeleteInfoService NoDeleteInfoService){
		this.service=NoDeleteInfoService;
	}
	 @Override
	    protected void postCreate(Model model) {
	    	model.addAttribute("pushRuleList", pushRuleInfoService.getAllPushRulesCache());
	    }
	    @Override
	    protected void postModify(int id, NoDeleteInfo obj, Model model) {
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
    	NoDeleteInfo noDeleteInfo=form.toObj();
		try{
            service.saveOrUpdate(noDeleteInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends IdForm<NoDeleteInfo> {
        @NotBlank(message = "包名不能为空")
        private String packageName;
        private Integer pushId;

        @Override
        public NoDeleteInfo newObj() {
            return new NoDeleteInfo();
        }

        @Override
        public void onlineulateObj(NoDeleteInfo noDeleteInfo) {
        	noDeleteInfo.setPackageName(packageName);
        	noDeleteInfo.setPushId(pushId);
        	
        }

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public Integer getPushId() {
			return pushId;
		}

		public void setPushId(Integer pushId) {
			this.pushId = pushId;
		}

    }
}
