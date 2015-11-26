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
public class ImportOnlineStatisticsInfoController {

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
	@Scheduled(cron = "0 0 2 ? * *")
	public void importOnlineInfo() {
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
					+ "/" + relativeDir + "/uploadData.txt";

			OnlineReadFile(fileNameM, relativeDir);

			List<DiscountInfo> discountInfoList = discountInfoService.listAll();

			Map<String, String> map = new HashMap<String, String>();
			map.put("datetime", relativeDir);

			System.out.println("map : " + map);
			List<OnlineGroupInfo> onlineInfos = onlineInfoService.countAll(map);

			for (OnlineGroupInfo onlineGroupInfo : onlineInfos) {
				OnlineStatisticsInfo onlineStatisticsInfo = new OnlineStatisticsInfo();
				onlineStatisticsInfo.setChannel(onlineGroupInfo.getChannel());
				onlineStatisticsInfo.setCountry(onlineGroupInfo.getCountry());
				onlineStatisticsInfo.setTotal(String.valueOf(onlineGroupInfo
						.getTotal()));
				onlineStatisticsInfo.setDatetime(onlineGroupInfo.getDatetime());
				onlineStatisticsInfoService.save(onlineStatisticsInfo);
			}

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

	public void OnlineReadFile(String filename, String dateTime) {
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

			List<OnlineInfo> onlineInfoList = new ArrayList<OnlineInfo>();

			while ((tempString = reader.readLine()) != null) {
				try {
					// tempString = tempString.replaceAll("\\\\", "");
					OnlineInfo onlineInfo = new OnlineInfo();
					try {
						JSONObject onlineInfoObject = null;
						try {
							onlineInfoObject = new JSONObject(tempString);
						} catch (Exception ex) {
							System.out.println(tempString);
							continue;
						}

						if (onlineInfoObject.has("uuid")
								&& null != onlineInfoObject.get("uuid")) {
							String uuid = onlineInfoObject.get("uuid")
									.toString();
							onlineInfo.setUuid(uuid);
						} else {
							onlineInfo.setUuid("");
						}

						if (onlineInfoObject.has("version")
								&& null != onlineInfoObject.get("version")) {
							String version = onlineInfoObject.get("version")
									.toString();
							onlineInfo.setVersion(version);
						} else {
							onlineInfo.setVersion("");
						}
						//
						if (onlineInfoObject.has("netType")
								&& null != onlineInfoObject.get("netType")) {
							onlineInfo.setNetType(onlineInfoObject.get(
									"netType").toString());
						} else {
							onlineInfo.setNetType("");
						}
						//
						if (onlineInfoObject.has("channel")
								&& null != onlineInfoObject.get("channel")) {
							onlineInfo.setChannel(onlineInfoObject.get(
									"channel").toString());
						} else {
							onlineInfo.setChannel("");
						}
						//
						if (onlineInfoObject.has("isTablet")
								&& null != onlineInfoObject.get("isTablet")) {
							onlineInfo.setIsTablet(onlineInfoObject.get(
									"isTablet").toString());
						} else {
							onlineInfo.setIsTablet("");
						}
						//
						if (onlineInfoObject.has("macAddress")
								&& null != onlineInfoObject.get("macAddress")) {
							onlineInfo.setMacAddress(onlineInfoObject.get(
									"macAddress").toString());
						} else {
							onlineInfo.setMacAddress("");
						}
						//
						if (onlineInfoObject.has("systemVersion")
								&& null != onlineInfoObject
										.get("systemVersion")) {
							onlineInfo.setSystemVersion(onlineInfoObject.get(
									"systemVersion").toString());
						} else {
							onlineInfo.setSystemVersion("");
						}
						//
						if (onlineInfoObject.has("sdkVersion")
								&& null != onlineInfoObject.get("sdkVersion")) {
							onlineInfo.setSdkVersion(onlineInfoObject.get(
									"sdkVersion").toString());
						} else {
							onlineInfo.setSdkVersion("");
						}
						//
						if (onlineInfoObject.has("model")
								&& null != onlineInfoObject.get("model")) {
							onlineInfo.setModel(onlineInfoObject.get("model")
									.toString());
						} else {
							onlineInfo.setModel("");
						}
						//
						if (onlineInfoObject.has("language")
								&& null != onlineInfoObject.get("language")) {
							onlineInfo.setLanguage(onlineInfoObject.get(
									"language").toString());
						} else {
							onlineInfo.setLanguage("");
						}

						if (onlineInfoObject.has("size")
								&& null != onlineInfoObject.get("size")) {
							onlineInfo.setSize(onlineInfoObject.get("size")
									.toString());
						} else {
							onlineInfo.setSize("");
						}

						if (onlineInfoObject.has("country")
								&& null != onlineInfoObject.get("country")) {
							onlineInfo.setCountry(onlineInfoObject.get(
									"country").toString());
						} else {
							onlineInfo.setCountry("");
						}

						if (onlineInfoObject.has("resolution")
								&& null != onlineInfoObject.get("resolution")) {
							onlineInfo.setResolution(onlineInfoObject.get(
									"resolution").toString());
						} else {
							onlineInfo.setResolution("");
						}
						//
						if (onlineInfoObject.has("ram")
								&& null != onlineInfoObject.get("ram")) {
							onlineInfo.setRam(onlineInfoObject.get("ram")
									.toString());
						} else {
							onlineInfo.setRam("");
						}
						//
						if (onlineInfoObject.has("rom")
								&& null != onlineInfoObject.get("rom")) {
							onlineInfo.setRom(onlineInfoObject.get("rom")
									.toString());
						} else {
							onlineInfo.setRom("");
						}
						//
						if (onlineInfoObject.has("hasSim")
								&& null != onlineInfoObject.get("hasSim")) {
							onlineInfo.setHasSim(onlineInfoObject.get("hasSim")
									.toString());
						} else {
							onlineInfo.setHasSim("");
						}

						if (onlineInfoObject.has("ip")
								&& null != onlineInfoObject.get("ip")) {
							onlineInfo.setIp(onlineInfoObject.get("ip")
									.toString());
						} else {
							onlineInfo.setResolution("");
						}

						onlineInfo.setDatetime(dateTime);

						onlineInfoList.add(onlineInfo);

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

			for (OnlineInfo onlineInfo : onlineInfoList) {
				String uuid = onlineInfo.getUuid();

				if (uuid.length() > 100) {
					continue;
				}

				//
				List<OnlineInfo> onlineInfos = onlineInfoService.getByUuid(uuid);
				//
				if (null == onlineInfos || onlineInfos.size() <= 0) {
					try {
						onlineInfoService.save(onlineInfo);
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
