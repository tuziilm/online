package com.wxad.online.mvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxad.base.util.DesUtil;
import com.wxad.base.util.Utils;
import com.wxad.online.common.Config;
import com.wxad.online.common.Country;
import com.wxad.online.common.IpSeeker;
import com.wxad.online.common.LoginContext;
import com.wxad.online.common.Paginator;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.RegisterInfoList;
import com.wxad.online.domain.UploadDataInfo;
import com.wxad.online.service.RegisterInfoService;

/**
 * 数据上传
 * 
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping(value = "/rs")
public class RegisterInfoController
		extends
		ListController<RegisterInfo, RegisterInfoService, com.wxad.online.mvc.RegisterInfoController.Query> {
	public RegisterInfoController() {
		super("rs");
	}

	private static final Logger logger = Logger
			.getLogger(RegisterInfoController.class);

	@Resource
	public void RegisterInfoService(RegisterInfoService registerInfoService) {
		this.service = registerInfoService;
	}

	@RequestMapping(value = "/registerinfo", method = RequestMethod.POST)
	public @ResponseBody String registerInfo(@RequestParam("data") String data,
			HttpServletRequest request) {

		try {
			String txt = data.replaceAll("\\\\", "");
			String jsonInfo = DesUtil.decode(new String(txt.getBytes("UTF-8")));

			String ip = request.getRemoteAddr();
			
			// 中国区域不下发业务
			String country = "cn";

			String hostAddressStr = "10.10.10";
			String hostAddressStr1 = "192.168.0";
			if (!ip.startsWith(hostAddressStr)
					&& !ip.startsWith(hostAddressStr1)) {
				IpSeeker.IpData ipdata = IpSeeker.ipData(ip);
				country = null == ipdata ? "cn" : ipdata.shortcut;
			}

			JSONObject jsonObj = new JSONObject(jsonInfo);
			jsonObj.put("country", country);
			jsonObj.put("ip", ip);
			
			
			FileChannel fc = null;
			try {
				Date date = new Date();
				String relativeDir = new SimpleDateFormat("yyyyMMdd")
						.format(date);
				String relativeDirM = new SimpleDateFormat("yyyyMM")
						.format(date);
				String fileNameM = Config.UPLOAD_ROOT_DIR + "/" + relativeDirM;

				File fileM = new File(fileNameM);
				if (!fileM.exists()) {
					fileM.mkdirs();
				}

				String fileNameMD = fileNameM + "/" + relativeDir;

				File fileMD = new File(fileNameMD);
				if (!fileMD.exists()) {
					fileMD.mkdirs();
				}

				String fileName = fileNameMD + "/register.txt";

				fc = new RandomAccessFile(fileName, "rw").getChannel(); // RandomAccessFile不支持只写模式，因为把参数设为“w”是非法的
				Charset chrst = Charset.forName("UTF-8");
				fc.position(fc.size()); // 定位到文件末尾

				fc.write(chrst.encode(jsonObj.toString() + "\n"));

				fc.close();
			} catch (Exception ex) {

			} finally {
				if (null != fc) {
					fc.close();
				}
			}

			return Utils.responseCode(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			return Utils.responseCode(Boolean.FALSE);
		}
	}

	@Override
	protected boolean preList(int page, Paginator paginator, Query query,
			Model model) {
		model.addAttribute("countries", Country.countries);
		model.addAttribute("countryMap", Country.shortcut2CountryMap);
		query.datetime = query.datetime.replace("/", "-");
		if (query.country.equals("all")) {
			query.country = null;
		}
		return super.preList(page, paginator, query, model);
	}

	@Override
	protected void postList(int page, Paginator paginator, Query query,
			Model model) {
		super.postList(page, paginator, query, model);
	}

	public static class Query extends com.wxad.online.common.Query {
		private String channel;
		private String datetime;
		private String country;

		public Query() {
			this.datetime = DateFormatUtils.format(
					DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
			if (LoginContext.isAdmin()) {
				this.channel = null;
			} else {
				this.channel = LoginContext.getUserChannel();
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
