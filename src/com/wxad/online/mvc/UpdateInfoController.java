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
import org.springframework.web.multipart.MultipartFile;

import com.wxad.online.common.IdForm;
import com.wxad.online.common.Paginator;
import com.wxad.online.common.Query;
import com.wxad.online.common.SHA256Util;
import com.wxad.online.common.UpLoads;
import com.wxad.online.common.UploadType;
import com.wxad.online.domain.UpdateInfo;
import com.wxad.online.exception.UploadException;
import com.wxad.online.service.PushRuleInfoService;
import com.wxad.online.service.UpdateInfoService;

@Controller
@RequestMapping("/push/update")
public class UpdateInfoController extends CRUDController<UpdateInfo, UpdateInfoService, UpdateInfoController.Form, Query.NameQuery>{
	@Resource
	private PushRuleInfoService pushRuleInfoService;
	
	public UpdateInfoController() {
		super("push/update");
	}
	@Resource
	public void setUpdateInfoService(UpdateInfoService updateInfoService){
		this.service=updateInfoService;
	}
	 @Override
	    protected void postCreate(Model model) {
	    	model.addAttribute("pushRuleList", pushRuleInfoService.getAllPushRulesCache());
	    }
	    @Override
	    protected void postModify(int id, UpdateInfo obj, Model model) {
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
    	UpdateInfo updateInfo=form.toObj();
		try{
			try{
				if(!form.url.isEmpty()){
					String url = UpLoads.upload(form.url,UploadType.APK);
					updateInfo.setUrl(url);
//					updateInfo.setHash(SHA256Util.class.newInstance().getHash(url));
				}
			}catch(UploadException e){
				errors.addError(new ObjectError("upload", "�ϴ�ʧ�ܣ�"));
				return;
			}
            service.saveOrUpdate(updateInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends IdForm<UpdateInfo> {
        @NotBlank(message = "version不能为空")
        private String version;
        private MultipartFile url;
        private Integer pushId;
        private String hash;
        

        public String getHash() {
			return hash;
		}

		public void setHash(String hash) {
			this.hash = hash;
		}

		@Override
        public UpdateInfo newObj() {
            return new UpdateInfo();
        }

        @Override
        public void onlineulateObj(UpdateInfo updateInfo) {
        	updateInfo.setVersion(version);
        	updateInfo.setPushId(pushId);
        	updateInfo.setHash(hash);
        	
        }

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public MultipartFile getUrl() {
			return url;
		}

		public void setUrl(MultipartFile url) {
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
