package com.wxad.online.mvc;

import com.wxad.online.common.LoginContext;
import com.wxad.online.common.Paginator;
import com.wxad.online.domain.SalesStatisticsInfo;
import com.wxad.online.domain.SalesStatisticsInfoDateTimeSummary;
import com.wxad.online.service.SalesStatisticsInfoService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "sales/sales")
public class SalesStatisticsInfoController
		extends
		ListController<SalesStatisticsInfo, SalesStatisticsInfoService, com.wxad.online.mvc.SalesStatisticsInfoController.Query> {
	public SalesStatisticsInfoController() {
		super("sales/sales");
	}

	private static final Logger logger = Logger
			.getLogger(SalesStatisticsInfoController.class);

	@Resource
	public void SalesStatisticsInfoService(
			SalesStatisticsInfoService SalesStatisticsInfoService) {
		this.service = SalesStatisticsInfoService;
	}

	@Override
	protected boolean preList(int page, Paginator paginator, Query query,
			Model model) {
//		paginator.setNeedTotal(true);
		query.datetime = query.datetime.replace("/", "");
		query.startTime = query.startTime.replace("/", "");

		return super.preList(page, paginator, query, model);
	}

	@Override
	protected void postList(int page, Paginator paginator, Query query,
			Model model) {
		
		int total = service.countAll(paginator);
		model.addAttribute("total", total);
		List<SalesStatisticsInfoDateTimeSummary> countData = service.selectDateTimeTotal(paginator);
		model.addAttribute("countDatas", countData);
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
					DateUtils.addDays(new Date(), -1), "yyyyMMdd");
			this.startTime = DateFormatUtils.format(
					DateUtils.addDays(new Date(), -1), "yyyyMMdd");
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
