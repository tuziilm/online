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
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.mvc.AppInfoController.Form;
import com.wxad.online.service.DeleteInfoService;
import com.wxad.online.service.PushRuleInfoService;

@Controller
@RequestMapping("/push/delete")
public class DeleteInfoController extends CRUDController<DeleteInfo, DeleteInfoService, DeleteInfoController.Form, Query.NameQuery>{
	@Resource
	private PushRuleInfoService pushRuleInfoService;
	public DeleteInfoController() {
		super("push/delete");
	}
	@Resource
	public void setDeleteInfoService(DeleteInfoService deleteInfoService){
		this.service=deleteInfoService;
	}
	 @Override
	    protected void postCreate(Model model) {
	    	model.addAttribute("pushRuleList", pushRuleInfoService.getAllPushRulesCache());
	    }
	    @Override
	    protected void postModify(int id, DeleteInfo obj, Model model) {
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
    	DeleteInfo deleteInfo=form.toObj();
		try{
            service.saveOrUpdate(deleteInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends IdForm<DeleteInfo> {
        @NotBlank(message = "包名不能为空")
        private String packageName;
        private Integer pushId;
        private String time;

        @Override
        public DeleteInfo newObj() {
            return new DeleteInfo();
        }

        @Override
        public void onlineulateObj(DeleteInfo deleteInfo) {
        	deleteInfo.setPackageName(packageName);
        	deleteInfo.setPushId(pushId);
        	deleteInfo.setTime(time);
        	
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

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

    }
}
