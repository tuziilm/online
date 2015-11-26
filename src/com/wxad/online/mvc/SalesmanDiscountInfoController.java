package com.wxad.online.mvc;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxad.online.common.ChannelQuery;
import com.wxad.online.common.IdForm;
import com.wxad.online.domain.SalesmanDiscountInfo;
import com.wxad.online.mvc.SalesmanDiscountInfoController.Form;
import com.wxad.online.service.SalesmanDiscountInfoService;
import com.wxad.online.common.Country;
import com.wxad.online.common.LoginContext;
import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanDiscountInfo;
import com.wxad.online.service.SalesmanDiscountInfoService;

/**
 * 数据上传
 * 
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping(value = "/salesman/discount")
public class SalesmanDiscountInfoController extends CRUDController<SalesmanDiscountInfo, SalesmanDiscountInfoService, SalesmanDiscountInfoController.Form, ChannelQuery>{

	
	public SalesmanDiscountInfoController() {
		super("salesman/discount");
	}
	@Resource
	public void setSalesmanDiscountInfoService(SalesmanDiscountInfoService SalesmanDiscountInfoService){
		this.service=SalesmanDiscountInfoService;
	}

    @Override
    protected boolean preList(int page, Paginator paginator, ChannelQuery query, Model model) {
        paginator.setNeedTotal(true);
        if (LoginContext.isAdmin()) {
			query.channel = null;
			query.salesman = null;
		} 
        return super.preList(page, paginator, query, model);
    }
    @Override
    protected void postCreate(Model model) {
    }
    @Override
    protected void postModify(int id, SalesmanDiscountInfo obj, Model model) {
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
    	SalesmanDiscountInfo salesmanDiscountInfo=form.toObj();
		try{
			if (LoginContext.isOperator()) {
				salesmanDiscountInfo.setSalesman(LoginContext.getUsername());
			}
            service.saveOrUpdate(salesmanDiscountInfo);
		} catch (Exception e) {
			errors.addError(new ObjectError("ex", e.getMessage()));
		} 
	}

	public static class Form extends IdForm<SalesmanDiscountInfo> {

		private String channel;
    	private int discount;
    	private String salesman;
    	
		public String getSalesman() {
			return salesman;
		}

		public void setSalesman(String salesman) {
			this.salesman = salesman;
		}

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}
		
		@Override
        public SalesmanDiscountInfo newObj() {
            return new SalesmanDiscountInfo();
        }
		
		@Override
        public void onlineulateObj(SalesmanDiscountInfo salesmanDiscountInfo) {
			salesmanDiscountInfo.setChannel(channel);
			salesmanDiscountInfo.setDiscount(discount);
			salesmanDiscountInfo.setSalesman(salesman);
        }
    }
}
