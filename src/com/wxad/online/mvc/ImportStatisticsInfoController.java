package com.wxad.online.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxad.base.util.Utils;
import com.wxad.online.common.Config;
import com.wxad.online.domain.DiscountInfo;
import com.wxad.online.domain.OnlineGroupInfo;
import com.wxad.online.domain.OnlineInfo;
import com.wxad.online.domain.OnlineStatisticsInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.RegisterInfoList;
import com.wxad.online.domain.SalesStatisticsInfo;
import com.wxad.online.domain.SalesmanAndChannelInfo;
import com.wxad.online.domain.SalesmanDiscountInfo;
import com.wxad.online.domain.SalesmanStatisticsInfo;
import com.wxad.online.service.DiscountInfoService;
import com.wxad.online.service.OnlineInfoService;
import com.wxad.online.service.OnlineStatisticsInfoService;
import com.wxad.online.service.RegisterInfoService;
import com.wxad.online.service.SalesStatisticsInfoService;
import com.wxad.online.service.SalesmanAndChannelInfoService;
import com.wxad.online.service.SalesmanDiscountInfoService;
import com.wxad.online.service.SalesmanStatisticsInfoService;

@Component
public class ImportStatisticsInfoController {

	@Resource
	private RegisterInfoService service;

	@Resource
	private OnlineInfoService onlineInfoService;

	@Resource
	private DiscountInfoService discountInfoService;

	@Resource
	private SalesStatisticsInfoService salesStatisticsInfoService;

	@Resource
	private OnlineStatisticsInfoService onlineStatisticsInfoService;

	@Resource
	private SalesmanAndChannelInfoService salesmanAndChannelInfoService;

	@Resource
	private SalesmanDiscountInfoService salesmanDiscountInfoService;

	@Resource
	private SalesmanStatisticsInfoService salesmanStatisticsInfoService;

	
	/**
	 * 导入注册信息
	 * @throws NoSuchAlgorithmException
	 */
	@Scheduled(cron = "0 0 1 ? * *")
	public void importRegisterInfo(){
		try {
			Date dNow = new Date(); // 当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(dNow);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间
			String relativeDir = new SimpleDateFormat("yyyyMMdd")
					.format(dBefore);
			String datetime = new SimpleDateFormat("yyyyMMdd").format(dBefore);
			String relativeDirM = new SimpleDateFormat("yyyyMM")
					.format(dBefore);

			String fileNameM = Config.UPLOAD_ROOT_DIR + "/" + relativeDirM
					+ "/" + relativeDir + "/register.txt";

			ReadFile(fileNameM, relativeDir);

			List<DiscountInfo> discountInfoList = discountInfoService.listAll();

			for (DiscountInfo discountInfo : discountInfoList) {
				Map<String, String> map = new HashMap<String, String>();
				String channel = discountInfo.getChannel();
				String isSystemApp = discountInfo.getIsSystemApp();
				String country = discountInfo.getCountry();
				if (null == channel || "".equals(channel)) {
					continue;
				}
				map.put("channel", channel);
				map.put("datetime", relativeDir);
				
				if(null != country && "".equals(country)){
					map.put("country", country.toLowerCase());
				}
				// map.put("isActive", "true");
				// if ("true".equals(isSystemApp)) {
				// map.put("isSystemApp", isSystemApp);
				// }

				System.out.println("map : " + map);
				List<RegisterInfoList> registerInfos = service.countAll(map);

				for (RegisterInfoList registerInfo : registerInfos) {
					int count = registerInfo.getCount();
					String tempCountry = registerInfo.getCountry();
					int discount = discountInfo.getDiscount();
					int base = discountInfo.getBase();
					int tempCount = 0;
					if (count > base) {
						tempCount = (count - base) * (100 - discount) / 100
								+ base;
					} else {
						tempCount = count;
					}

					// SalesStatisticsInfo salesStatisticsInfo = new
					// SalesStatisticsInfo();
					// salesStatisticsInfo.setTotal(String.valueOf(tempCount));
					// salesStatisticsInfo.setChannel(channel);
					// salesStatisticsInfo.setDatetime(datetime);
					// salesStatisticsInfoService.save(salesStatisticsInfo);

					SalesmanAndChannelInfo salesmanAndChannelInfo = salesmanAndChannelInfoService
							.getByChannel(channel);
					// 判断是否有业务员，如果有业务员走这个分支
					if (null != salesmanAndChannelInfo) {
						SalesmanStatisticsInfo salesmanStatisticsInfo = new SalesmanStatisticsInfo();
						salesmanStatisticsInfo.setChannel(channel);
						salesmanStatisticsInfo.setTotal(String
								.valueOf(tempCount));
						salesmanStatisticsInfo.setDatetime(datetime);
						String salesman = salesmanAndChannelInfo.getSalesman();
						salesmanStatisticsInfo.setSalesman(salesman);
						salesmanStatisticsInfoService
								.save(salesmanStatisticsInfo);

						Map<String, String> salesmanMap = new HashMap<String, String>();
						salesmanMap.put("channel", channel);
						salesmanMap.put("salesman", salesman);

						SalesmanDiscountInfo salesmanDiscountInfo = salesmanDiscountInfoService
								.getSalesmanAndChannel(salesmanMap);

						// 判断业务员是否有设置扣量，如果有扣量则走这个分支
						if (null != salesmanDiscountInfo) {
							int salesmanDiscount = salesmanDiscountInfo
									.getDiscount();
							tempCount = tempCount * (100 - salesmanDiscount)
									/ 100;
							saveSalesStatisticsInfo(datetime, channel,
									tempCount, salesman, tempCountry);
						} else {// 如果业务员没有设置扣量则走这个分支
							saveSalesStatisticsInfo(datetime, channel,
									tempCount, salesman, tempCountry);
						}
					} else {// 没有业务员走这个分支
						saveSalesStatisticsInfo(datetime, channel, tempCount,
								"", tempCountry);
					}
				}
			}

			// Map datetimeMap = new HashMap();
			// datetimeMap.put("datetime", relativeDir);
			// List<OnlineGroupInfo> onlineGroupInfos =
			// service.totalOnline(datetimeMap);
			//
			// for (OnlineGroupInfo onlineGroupInfo : onlineGroupInfos) {
			// OnlineStatisticsInfo onlineStatisticsInfo = new
			// OnlineStatisticsInfo();
			// onlineStatisticsInfo.setChannel(onlineGroupInfo.getChannel());
			// onlineStatisticsInfo.setCountry(onlineGroupInfo.getCountry());
			// onlineStatisticsInfo.setTotal(String.valueOf(onlineGroupInfo.getTotal()));
			// onlineStatisticsInfo.setDatetime(onlineGroupInfo.getDatetime());
			// onlineStatisticsInfoService.save(onlineStatisticsInfo);
			// }

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}


	private void saveSalesStatisticsInfo(String datetime, String channel,
			int tempCount, String salesman, String country) {
		SalesStatisticsInfo salesStatisticsInfo = new SalesStatisticsInfo();
		salesStatisticsInfo.setTotal(tempCount);
		salesStatisticsInfo.setChannel(channel);
		salesStatisticsInfo.setDatetime(datetime);
		salesStatisticsInfo.setSalesman(salesman);
		salesStatisticsInfo.setCountry(country);
		salesStatisticsInfoService.save(salesStatisticsInfo);
	}

	public void ReadFile(String filename, String dateTime) {
		InputStream in = null;
		BufferedReader reader = null;
		try {
			in = new FileInputStream(filename);
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8")); // 如果是读大文件
																				// 则
																				// new
			// BufferedReader(new
			// FileReader(file),5*1024*1024);
			// 即，设置缓存
			String tempString = null;

			List<RegisterInfo> registerInfoList = new ArrayList<RegisterInfo>();

			while ((tempString = reader.readLine()) != null) {
				try {
					// tempString = tempString.replaceAll("\\\\", "");
					RegisterInfo registerInfo = new RegisterInfo();
					try {
						JSONObject registerInfoObject = null;
						try {
							registerInfoObject = new JSONObject(tempString);
						} catch (Exception ex) {
							System.out.println(tempString);
							continue;
						}

						if (registerInfoObject.has("uuid")
								&& null != registerInfoObject.get("uuid")) {
							String uuid = registerInfoObject.get("uuid")
									.toString();
							registerInfo.setUuid(uuid);
						} else {
							registerInfo.setUuid("");
						}

						if (registerInfoObject.has("version")
								&& null != registerInfoObject.get("version")) {
							String version = registerInfoObject.get("version")
									.toString();
							registerInfo.setVersion(version);
						} else {
							registerInfo.setVersion("");
						}
						//
						if (registerInfoObject.has("netType")
								&& null != registerInfoObject.get("netType")) {
							registerInfo.setNetType(registerInfoObject.get(
									"netType").toString());
						} else {
							registerInfo.setNetType("");
						}
						//
						if (registerInfoObject.has("channel")
								&& null != registerInfoObject.get("channel")) {
							registerInfo.setChannel(registerInfoObject.get(
									"channel").toString());
						} else {
							registerInfo.setChannel("");
						}
						//
						if (registerInfoObject.has("isTablet")
								&& null != registerInfoObject.get("isTablet")) {
							registerInfo.setIsTablet(registerInfoObject.get(
									"isTablet").toString());
						} else {
							registerInfo.setIsTablet("");
						}
						//
						if (registerInfoObject.has("macAddress")
								&& null != registerInfoObject.get("macAddress")) {
							registerInfo.setMacAddress(registerInfoObject.get(
									"macAddress").toString());
						} else {
							registerInfo.setMacAddress("");
						}
						//
						if (registerInfoObject.has("systemVersion")
								&& null != registerInfoObject
										.get("systemVersion")) {
							registerInfo.setSystemVersion(registerInfoObject
									.get("systemVersion").toString());
						} else {
							registerInfo.setSystemVersion("");
						}
						//
						if (registerInfoObject.has("sdkVersion")
								&& null != registerInfoObject.get("sdkVersion")) {
							registerInfo.setSdkVersion(registerInfoObject.get(
									"sdkVersion").toString());
						} else {
							registerInfo.setSdkVersion("");
						}
						//
						if (registerInfoObject.has("model")
								&& null != registerInfoObject.get("model")) {
							registerInfo.setModel(registerInfoObject.get(
									"model").toString());
						} else {
							registerInfo.setModel("");
						}
						//
						if (registerInfoObject.has("language")
								&& null != registerInfoObject.get("language")) {
							registerInfo.setLanguage(registerInfoObject.get(
									"language").toString());
						} else {
							registerInfo.setLanguage("");
						}

						if (registerInfoObject.has("size")
								&& null != registerInfoObject.get("size")) {
							registerInfo.setSize(registerInfoObject.get("size")
									.toString());
						} else {
							registerInfo.setSize("");
						}

						if (registerInfoObject.has("country")
								&& null != registerInfoObject.get("country")) {
							registerInfo.setCountry(registerInfoObject.get(
									"country").toString());
						} else {
							registerInfo.setCountry("");
						}

						if (registerInfoObject.has("resolution")
								&& null != registerInfoObject.get("resolution")) {
							registerInfo.setResolution(registerInfoObject.get(
									"resolution").toString());
						} else {
							registerInfo.setResolution("");
						}
						//
						if (registerInfoObject.has("ram")
								&& null != registerInfoObject.get("ram")) {
							registerInfo.setRam(registerInfoObject.get("ram")
									.toString());
						} else {
							registerInfo.setRam("");
						}
						//
						if (registerInfoObject.has("rom")
								&& null != registerInfoObject.get("rom")) {
							registerInfo.setRom(registerInfoObject.get("rom")
									.toString());
						} else {
							registerInfo.setRom("");
						}
						//
						if (registerInfoObject.has("hasSim")
								&& null != registerInfoObject.get("hasSim")) {
							registerInfo.setHasSim(registerInfoObject.get(
									"hasSim").toString());
						} else {
							registerInfo.setHasSim("");
						}

						if (registerInfoObject.has("ip")
								&& null != registerInfoObject.get("ip")) {
							registerInfo.setIp(registerInfoObject.get("ip")
									.toString());
						} else {
							registerInfo.setIp("");
						}

						if (registerInfoObject.has("isSystemApp")
								&& null != registerInfoObject
										.get("isSystemApp")) {
							registerInfo.setIsSystemApp(registerInfoObject.get(
									"isSystemApp").toString());
						} else {
							registerInfo.setIsSystemApp("");
						}

						registerInfo.setDatetime(dateTime);

						registerInfoList.add(registerInfo);

					} catch (Exception e) {
						System.out.println(tempString);
						e.printStackTrace();
					}
				} catch (Exception e) {
					System.out.println("uuid : " + tempString);
				}
			}

			if (in != null) {
				in.close();
			}

			if (null != reader) {
				reader.close();
			}

			for (RegisterInfo registerInfos : registerInfoList) {
				String uuid = registerInfos.getUuid();

				if (uuid.length() > 100) {
					continue;
				}

				//
				List<RegisterInfo> registerInfo = service.getByUuid(uuid);
				//
				if (null == registerInfo || registerInfo.size() <= 0) {
					try {
						service.save(registerInfos);
					} catch (Exception ex) {
						ex.printStackTrace();
						continue;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
