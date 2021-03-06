package com.wxad.online.mvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxad.base.util.DesUtil;
import com.wxad.base.util.Utils;
import com.wxad.online.common.Config;
import com.wxad.online.common.Country;
import com.wxad.online.common.IpSeeker;
import com.wxad.online.common.LoginContext;
import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesmanStatisticsInfo;
import com.wxad.online.service.SalesmanStatisticsInfoService;

/**
 * �����ϴ�
 * 
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping(value = "/salesman")
public class SalesmanStatisticsInfoController
		extends
		ListController<SalesmanStatisticsInfo, SalesmanStatisticsInfoService, com.wxad.online.mvc.SalesmanStatisticsInfoController.Query> {
	public SalesmanStatisticsInfoController() {
		super("salesman");
	}

	private static final Logger logger = Logger
			.getLogger(SalesmanStatisticsInfoController.class);

	@Resource
	public void SalesmanStatisticsInfoService(
			SalesmanStatisticsInfoService salesmanStatisticsInfoService) {
		this.service = salesmanStatisticsInfoService;
	}

	@Override
	protected boolean preList(int page, Paginator paginator, Query query,
			Model model) {
		model.addAttribute("countries", Country.countries);
		model.addAttribute("countryMap", Country.shortcut2CountryMap);
		query.datetime = query.datetime.replace("/", "-");
		query.startTime = query.startTime.replace("/", "-");
		if (query.country.equals("all")) {
			query.country = null;
		}
		paginator.setNeedTotal(true);
		return super.preList(page, paginator, query, model);
	}

	@Override
	protected void postList(int page, Paginator paginator, Query query,
			Model model) {
		int count = service.countAll(paginator);
		model.addAttribute("count", count);
		super.postList(page, paginator, query, model);
	}

	public static class Query extends com.wxad.online.common.Query {
		private String startTime;
		private String channel;
		private String datetime;
		private String country;
		private String salesman;

		
		public Query() {
			this.datetime = DateFormatUtils.format(
					DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
			this.startTime = DateFormatUtils.format(
					DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
			if (LoginContext.isAdmin()) {
				this.channel = null;
			} else if(LoginContext.isOperator()) {
				this.channel = null;
				this.salesman = LoginContext.getUsername();
			} else {
				this.channel = LoginContext.getUserChannel();
			}

			setCountry("all");
		}
		
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
			addItem("channel", channel);
		}

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
			addItem("datetime", datetime);
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
			addItem("startTime", startTime);
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
