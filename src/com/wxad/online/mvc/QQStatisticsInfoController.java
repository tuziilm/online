package com.wxad.online.mvc;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wxad.online.common.Country;
import com.wxad.online.common.LoginContext;
import com.wxad.online.common.Paginator;
import com.wxad.online.domain.QQStatisticsInfo;
import com.wxad.online.service.QQStatisticsInfoService;

/**
 * 数据上传
 * 
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping(value = "/qq")
public class QQStatisticsInfoController
		extends
		ListController<QQStatisticsInfo, QQStatisticsInfoService, com.wxad.online.mvc.QQStatisticsInfoController.Query> {
	public QQStatisticsInfoController() {
		super("qq");
	}

	private static final Logger logger = Logger
			.getLogger(QQStatisticsInfoController.class);

	@Resource
	public void QQStatisticsInfoService(
			QQStatisticsInfoService QQStatisticsInfoService) {
		this.service = QQStatisticsInfoService;
	}

	@Override
	protected boolean preList(int page, Paginator paginator, Query query,
			Model model) {
		model.addAttribute("countries", Country.countries);
		model.addAttribute("countryMap", Country.shortcut2CountryMap);
		query.datetime = query.datetime.replace("/", "-");
		query.startTime = query.startTime.replace("/", "-");
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

		public Query() {
			this.datetime = DateFormatUtils.format(
					DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
			this.startTime = DateFormatUtils.format(
					DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
			if (LoginContext.isAdmin()) {
				this.channel = null;
			} else {
				this.channel = LoginContext.getUserChannel();
			}
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
	}

}
