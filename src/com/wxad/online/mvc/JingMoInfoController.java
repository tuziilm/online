package com.wxad.online.mvc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxad.online.common.Country;
import com.wxad.online.common.LoginContext;
import com.wxad.online.common.Paginator;
import com.wxad.online.domain.JingMoInfo;
import com.wxad.online.domain.JingMoInfoList;
import com.wxad.online.service.JingMoInfoService;

/**
 * 销量统计入口
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping("/jingmo")
public class JingMoInfoController extends ListController<JingMoInfo, JingMoInfoService, com.wxad.online.mvc.JingMoInfoController.Query>{
	public JingMoInfoController() {
		super("jingmo");
	}
	@Resource
	public void setPopInfoService(JingMoInfoService jingMoInfoService){
		this.service=jingMoInfoService;
	}
	
	 @Override
	 protected boolean preList(int page, Paginator paginator, Query query, Model model) {
	       model.addAttribute("countries", Country.countries);
	       model.addAttribute("countryMap", Country.shortcut2CountryMap);
	       query.datetime=query.datetime.replace("/", "-");
	       if(query.country.equals("all")){
	    	   query.country=null;
	       }
	       return super.preList(page, paginator, query, model);
	   }
	 @Override
	protected void postList(int page, Paginator paginator, Query query, 
			Model model) {
		int total = service.countAll(paginator);
		model.addAttribute("total", total);
		List<JingMoInfoList> countData = service.countActivity(paginator);
		model.addAttribute("countDatas", countData);
		super.postList(page, paginator, query, model);
	}
	 public static class Query extends com.wxad.online.common.Query {
		private String channel;
		private String datetime;
		private String country;
		public Query() {
			this.datetime = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
			if(LoginContext.isAdmin()){
				this.channel=null;
			}else{
				this.channel=LoginContext.getUserChannel();
			}
			setCountry("all");
		}	

		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
			addItem("channel", channel);
		}

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
			addItem("datetime", datetime);
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
			addItem("country", country);
		}
		
	 }
}
