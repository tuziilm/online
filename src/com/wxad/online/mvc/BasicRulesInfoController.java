package com.wxad.online.mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxad.online.common.IdForm;
import com.wxad.online.common.Paginator;
import com.wxad.online.common.Query;
import com.wxad.online.domain.BasicRulesInfo;
import com.wxad.online.service.BasicRulesInfoService;
import com.wxad.online.service.PushRuleInfoService;

@Controller
@RequestMapping("/push/basicrules")
public class BasicRulesInfoController
		extends
		CRUDController<BasicRulesInfo, BasicRulesInfoService, BasicRulesInfoController.Form, Query.NameQuery> {
	@Resource
	private PushRuleInfoService pushRuleInfoService;

	public BasicRulesInfoController() {
		super("push/basicrules");
	}

	@Resource
	public void setBasicRulesInfoService(BasicRulesInfoService updateInfoService) {
		this.service = updateInfoService;
	}

	@Override
	protected void postCreate(Model model) {
		model.addAttribute("pushRuleList",
				pushRuleInfoService.getAllPushRulesCache());
	}

	@Override
	protected void postModify(int id, BasicRulesInfo obj, Model model) {
		postCreate(model);
	}

	@Override
	protected void onSaveError(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		postCreate(model);
	}

	@Override
	protected boolean preList(int page, Paginator paginator,
			Query.NameQuery query, Model model) {
		paginator.setNeedTotal(true);
		return super.preList(page, paginator, query, model);
	}

	@Override
	public void innerSave(Form form, BindingResult errors, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		BasicRulesInfo basicRulesInfo = form.toObj();
		try {
			service.saveOrUpdate(basicRulesInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		}
	}

	public static class Form extends IdForm<BasicRulesInfo> {
		@NotBlank(message = "channel不能为空")
		private String channel;
		@Range(min=0,max=100,message = "比例必须是0-100")
		private int proportion;
		@Range(min=0,max=10000,message = "基准值范围0-10000")
		private int benchmark;

		@Override
		public BasicRulesInfo newObj() {
			return new BasicRulesInfo();
		}

		@Override
		public void onlineulateObj(BasicRulesInfo basicRulesInfo) {
			basicRulesInfo.setChannel(channel);
			basicRulesInfo.setProportion(proportion);
			basicRulesInfo.setBenchmark(benchmark);
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public int getProportion() {
			return proportion;
		}

		public void setProportion(int proportion) {
			this.proportion = proportion;
		}
		
		public int getBenchmark() {
			return benchmark;
		}

		public void setBenchmark(int benchmark) {
			this.benchmark = benchmark;
		}
	}
}
