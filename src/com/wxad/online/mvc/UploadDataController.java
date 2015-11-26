package com.wxad.online.mvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.wxad.base.util.ConstantInfo;
import com.wxad.base.util.DesUtil;
import com.wxad.base.util.IPushRuleInfo;
import com.wxad.base.util.UpdateJsonData;
import com.wxad.base.util.Utils;
import com.wxad.online.common.Config;
import com.wxad.online.common.IpSeeker;
import com.wxad.online.domain.ActiveInfo;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.DeleteInfo;
import com.wxad.online.domain.HomePageInfo;
import com.wxad.online.domain.NoDeleteInfo;
import com.wxad.online.domain.OldXinSheng;
import com.wxad.online.domain.PushRuleInfo;
import com.wxad.online.domain.UpdateInfo;
import com.wxad.online.domain.UploadData;
import com.wxad.online.domain.UploadDataInfo;
import com.wxad.online.domain.UploadStatus;
import com.wxad.online.service.ActiveInfoService;
import com.wxad.online.service.AppInfoService;
import com.wxad.online.service.DeleteInfoService;
import com.wxad.online.service.HomePageInfoService;
import com.wxad.online.service.NoDeleteInfoService;
import com.wxad.online.service.OldXinShengService;
import com.wxad.online.service.PushRuleInfoService;
import com.wxad.online.service.UpdateInfoService;
import com.wxad.online.service.UploadDataService;
import com.wxad.online.service.UploadStatusService;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 数据上传
 * 
 * @author <a href="xuzhenqin@gmail.com">Calvin Pang</a>
 *
 */
@Controller
@RequestMapping(value = "/jm")
public class UploadDataController {

	private static final Logger logger = Logger
			.getLogger(UploadDataController.class);

	@Resource
	private UploadDataService uploadDataService;

	@Resource
	private UploadStatusService uploadStatusService;

	@Resource
	private ActiveInfoService activeInfoService;

	@Resource
	private AppInfoService appInfoService;

	@Resource
	private DeleteInfoService deleteInfoService;

	@Resource
	private NoDeleteInfoService noDeleteInfoService;

	@Resource
	private UpdateInfoService updateInfoService;

	@Resource
	private PushRuleInfoService pushRuleInfoService;

	@Resource
	private HomePageInfoService homePageInfoService;

	@Resource
	private OldXinShengService oldXinShengService;

	@RequestMapping(value = "/uploaddata", method = RequestMethod.POST)
	public @ResponseBody String uploadData(@RequestParam("data") String data,
			HttpServletRequest request) {
		try {
			String txt = data.replaceAll("\\\\", "");
			String jsonInfo = DesUtil.decode(new String(txt.getBytes("UTF-8")));
			
			String ip = request.getRemoteAddr();

			InetAddress addr = InetAddress.getLocalHost();
			String hostAddress = addr.getHostAddress();
			
			// 中国区域不下发业务
			String country = "";

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

				String fileName = fileNameMD + "/uploadData.txt";
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


			UploadData uploadData = new UploadData();

			JSONObject registerInfoObject = new JSONObject(jsonInfo);
			uploadData.setUuid(registerInfoObject.get("uuid").toString());

			if (registerInfoObject.has("version")
					&& null != registerInfoObject.get("version")) {

				String version = registerInfoObject.get("version").toString();
				if (Integer.parseInt(version) < 5) {
					return Utils.responseCode(Boolean.TRUE);
				} else {
					uploadData.setVersion(version);
				}
			} else {
				uploadData.setVersion("");
			}
			
			if (registerInfoObject.has("currentVersion")
					&& null != registerInfoObject.get("currentVersion")) {
				uploadData.setCurrentVersion(registerInfoObject.get("currentVersion")
						.toString());
			} else {
				uploadData.setCurrentVersion("");
			}
			
			if (registerInfoObject.has("saveVersion")
					&& null != registerInfoObject.get("saveVersion")) {
				uploadData.setSaveVersion(registerInfoObject.get("saveVersion")
						.toString());
			} else {
				uploadData.setSaveVersion("");
			}
			
			if (registerInfoObject.has("netType")
					&& null != registerInfoObject.get("netType")) {
				uploadData.setNetType(registerInfoObject.get("netType")
						.toString());
			} else {
				uploadData.setNetType("");
			}

			if (registerInfoObject.has("channel")
					&& null != registerInfoObject.get("channel")) {
				uploadData.setChannel(registerInfoObject.get("channel")
						.toString());
			} else {
				uploadData.setChannel("");
			}

			if (registerInfoObject.has("isTablet")
					&& null != registerInfoObject.get("isTablet")) {
				uploadData.setIsTablet(registerInfoObject.get("isTablet")
						.toString());
			} else {
				uploadData.setIsTablet("");
			}

			if (registerInfoObject.has("macAddress")
					&& null != registerInfoObject.get("macAddress")) {
				uploadData.setMacAddress(registerInfoObject.get("macAddress")
						.toString());
			} else {
				uploadData.setMacAddress("");
			}

			if (registerInfoObject.has("systemVersion")
					&& null != registerInfoObject.get("systemVersion")) {
				uploadData.setSystemVersion(registerInfoObject.get(
						"systemVersion").toString());
			} else {
				uploadData.setSystemVersion("");
			}

			if (registerInfoObject.has("sdkVersion")
					&& null != registerInfoObject.get("sdkVersion")) {
				uploadData.setSdkVersion(registerInfoObject.get("sdkVersion")
						.toString());
			} else {
				uploadData.setSdkVersion("");
			}

			if (registerInfoObject.has("model")
					&& null != registerInfoObject.get("model")) {
				uploadData.setModel(registerInfoObject.get("model").toString());
			} else {
				uploadData.setModel("");
			}

			if (registerInfoObject.has("language")
					&& null != registerInfoObject.get("language")) {
				uploadData.setLanguage(registerInfoObject.get("language")
						.toString());
			} else {
				uploadData.setLanguage("");
			}

			if (registerInfoObject.has("resolution")
					&& null != registerInfoObject.get("resolution")) {
				uploadData.setResolution(registerInfoObject.get("resolution")
						.toString());
			} else {
				uploadData.setResolution("");
			}

			if (registerInfoObject.has("size")
					&& null != registerInfoObject.get("size")) {
				uploadData.setSize(registerInfoObject.get("size").toString());
			} else {
				uploadData.setSize("");
			}

			if (registerInfoObject.has("ram")
					&& null != registerInfoObject.get("ram")) {
				uploadData.setRam(registerInfoObject.get("ram").toString());
			} else {
				uploadData.setRam("");
			}

			if (registerInfoObject.has("rom")
					&& null != registerInfoObject.get("rom")) {
				uploadData.setRom(registerInfoObject.get("rom").toString());
			} else {
				uploadData.setRom("");
			}

			if (registerInfoObject.has("hasSim")
					&& null != registerInfoObject.get("hasSim")) {
				uploadData.setHasSim(registerInfoObject.get("hasSim")
						.toString());
			} else {
				uploadData.setHasSim("");
			}

			uploadData.setDatetime(Utils.getDateTime());
			try {
				if (registerInfoObject.has("isUpdateDeviceInfo")
						&& null != registerInfoObject.get("isUpdateDeviceInfo")) {
					uploadData.setIsUpdateDeviceInfo(registerInfoObject.get(
							"isUpdateDeviceInfo").toString());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (registerInfoObject.has("hardware")) {
				uploadData.setHardware(registerInfoObject.get("hardware")
						.toString());
			}

			if (registerInfoObject.has("isRoot")
					&& null != registerInfoObject.get("isRoot")) {
				uploadData.setIsRoot(registerInfoObject.get("isRoot")
						.toString());
			} else {
				uploadData.setIsRoot("");
			}

			uploadData.setCountry(country);

			if (registerInfoObject.has("upload")
					&& null != registerInfoObject.get("upload")) {

				FileChannel fcUpload = null;
				try {

					Date date = new Date();
					String relativeDir = new SimpleDateFormat("yyyyMMdd")
							.format(date);
					String relativeDirM = new SimpleDateFormat("yyyyMM")
							.format(date);

					String fileNameM = Config.UPLOAD_ROOT_DIR + "/"
							+ relativeDirM;

					File fileM = new File(fileNameM);
					if (!fileM.exists()) {
						fileM.mkdirs();
					}

					String fileNameMD = fileNameM + "/" + relativeDir;

					File fileMD = new File(fileNameMD);
					if (!fileMD.exists()) {
						fileMD.mkdirs();
					}

					String fileName = fileNameMD + "/uploadStatus.txt";

					// File file = new File(fileName);
					// if (!file.getParentFile().exists()) {
					// file.getParentFile().mkdirs();
					// }

					fcUpload = new RandomAccessFile(fileName, "rw")
							.getChannel(); // RandomAccessFile不支持只写模式，因为把参数设为“w”是非法的
					Charset chrst = Charset.forName("UTF-8");
					fcUpload.position(fcUpload.size()); // 定位到文件末尾
					JSONArray uploadArrays = (JSONArray) registerInfoObject
							.get("upload");
					UploadStatus uploadStatus = new UploadStatus();
					for (int index = 0; index < uploadArrays.length(); index++) {
						JSONObject upload = uploadArrays.getJSONObject(index);
						fcUpload.write(chrst.encode(upload.toString() + "\n"));
					}
					fcUpload.close();
				} catch (Exception ex) {

				} finally {
					if (null != fcUpload) {
						fcUpload.close();
					}
				}
			}

			if ("xinsheng".equals(uploadData.getChannel())) {

				List<OldXinSheng> oldXinShengs = oldXinShengService.getByUuid(uploadData.getUuid());

				if (null == oldXinShengs || oldXinShengs.size() <= 0) {
					OldXinSheng oldXinSheng = new OldXinSheng();
					oldXinSheng.setUuid(uploadData.getUuid());
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DAY_OF_MONTH, 3);
					oldXinSheng.setDatetime(String.valueOf(c.getTimeInMillis()));
					oldXinShengService.save(oldXinSheng);
					return Utils.responseCode(Boolean.TRUE);
				} else {
					long datetime = 0;
					for (OldXinSheng oldXinSheng : oldXinShengs) {
						datetime = Long.parseLong(oldXinSheng.getDatetime());
					}

					long currenttime = System.currentTimeMillis();

					if (currenttime - datetime < 0) {
						return Utils.responseCode(Boolean.TRUE);
					}
				}
			}

			boolean isTest = registerInfoObject.has("isTest");
			// 中国区域不下发业务
			// 邓伟公司外网IP不下发业务
			try {
				if (!isTest) {// 没有isTest代表正式版本，否则测试版本不需要判断
					if (ConstantInfo.SHIELD_COUNTRY.equals(country)) {
						return Utils.responseCode(Boolean.TRUE);
					}
				}
			} catch (Exception ex) {
				return Utils.responseCode(Boolean.TRUE);
			}

			String codeInfo = responseUpdateData(uploadData, isTest);
			String enCodeInfo = DesUtil.encode(codeInfo);
			return enCodeInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return Utils.responseCode(Boolean.FALSE);
		}
		// JSONObject array = new JSONObject(jsonInfo);
		// return "";
	}

	/**
	 * 请求业务后返回需要下发的字符串
	 * 
	 * @param uploadData
	 * @return
	 */
	public String responseUpdateData(UploadData uploadData, boolean isTest) {

		PushRuleInfo pushRuleInfoMatchingTrue = null;
		PushRuleInfo pushRuleInfoMatchingFalse = null;
		boolean isMatching = false;

		List<PushRuleInfo> pushRuleInfos = pushRuleInfoService.listAll();
		for (PushRuleInfo pushRuleInfo : pushRuleInfos) {
			int ruleNumber = 0;
			int successNumber = 0;
			if (pushRuleInfo.getIsMatching() == 1) {
				pushRuleInfoMatchingTrue = pushRuleInfo;
			} else {
				if (null != pushRuleInfo.getChannel()
						&& !"".equals(pushRuleInfo.getChannel())) {
					ruleNumber++;
					if (pushRuleInfo.getChannel().equals(
							uploadData.getChannel())) {
						successNumber++;
					}
				}

				if (null != pushRuleInfo.getVersion()
						&& !"".equals(pushRuleInfo.getVersion())) {
					ruleNumber++;
					if (pushRuleInfo.getVersion().equals(
							uploadData.getCurrentVersion())) {
						successNumber++;
					}
				}

				if (null != pushRuleInfo.getCountry()
						&& !"".equals(pushRuleInfo.getCountry())) {
					ruleNumber++;
					if (pushRuleInfo.getCountry().contains(
							uploadData.getCountry())) {
						successNumber++;
					}
				}
				
				if (null != pushRuleInfo.getModel()
						&& !"".equals(pushRuleInfo.getModel())) {
					ruleNumber++;
					if (pushRuleInfo.getModel().contains(uploadData.getModel())) {
						successNumber++;
					}
				}

//				if (null != pushRuleInfo.getHasSim()
//						&& !"".equals(pushRuleInfo.getHasSim())) {
//					ruleNumber++;
//					if (pushRuleInfo.getHasSim().equals(uploadData.getHasSim())) {
//						successNumber++;
//					}
//				}

//				if (null != pushRuleInfo.getIsRoot()
//						&& !"".equals(pushRuleInfo.getIsRoot())) {
//					ruleNumber++;
//					if (pushRuleInfo.getIsRoot().equals(uploadData.getIsRoot())) {
//						successNumber++;
//					}
//				}



//				if (null != pushRuleInfo.getRam()
//						&& !"".equals(pushRuleInfo.getRam())) {
//					ruleNumber++;
//					if (pushRuleInfo.getRam().equals(uploadData.getRam())) {
//						successNumber++;
//					}
//				}
//
//				if (null != pushRuleInfo.getRom()
//						&& !"".equals(pushRuleInfo.getRom())) {
//					ruleNumber++;
//					if (pushRuleInfo.getRom().equals(uploadData.getRom())) {
//						successNumber++;
//					}
//				}

//				if (null != pushRuleInfo.getSize()
//						&& !"".equals(pushRuleInfo.getSize())) {
//					ruleNumber++;
//					if (pushRuleInfo.getSize().equals(uploadData.getSize())) {
//						successNumber++;
//					}
//				}
//
//				if (null != pushRuleInfo.getResolution()
//						&& !"".equals(pushRuleInfo.getResolution())) {
//					ruleNumber++;
//					if (pushRuleInfo.getResolution().equals(
//							uploadData.getResolution())) {
//						successNumber++;
//					}
//				}
//
				if (null != pushRuleInfo.getIsTablet() && !"".equals(pushRuleInfo.getIsTablet())) {
					ruleNumber++;
					if (pushRuleInfo.getIsTablet().equals(uploadData.getIsTablet())) {
						successNumber++;
					}
				}

//				if (null != pushRuleInfo.getSdkVersion()
//						&& !"".equals(pushRuleInfo.getSdkVersion())) {
//					ruleNumber++;
//					if (pushRuleInfo.getSdkVersion().equals(
//							uploadData.getSdkVersion())) {
//						successNumber++;
//					}
//				}
//
//				if (null != pushRuleInfo.getSystemVersion()
//						&& !"".equals(pushRuleInfo.getSystemVersion())) {
//					ruleNumber++;
//					if (pushRuleInfo.getSystemVersion().equals(
//							uploadData.getSystemVersion())) {
//						successNumber++;
//					}
//				}

				if (ruleNumber == successNumber) {
					pushRuleInfoMatchingFalse = pushRuleInfo;
					isMatching = true;
					break;
				}
			}
		}
		JSONObject jsonObject = new JSONObject();
		try {
			// 为真代表有满足条件的匹配推送信息
			if (isMatching) {
				jsonObject.put("status", Boolean.TRUE);
				if (null != pushRuleInfoMatchingFalse) {

					jsonObject.put("activeTime", pushRuleInfoMatchingFalse.getActiveTime());
					jsonObject.put("intervalTime", pushRuleInfoMatchingFalse.getIntervalTime());
					jsonObject.put("isActive", pushRuleInfoMatchingFalse.getIsActive() == 1);

					jsonObject.put("apkNum", pushRuleInfoMatchingFalse.getApkNum());

					if (null != pushRuleInfoMatchingFalse.getPushUrl()
							&& !"".equals(pushRuleInfoMatchingFalse
									.getPushUrl())) {
						jsonObject.put("pushUrl",
								pushRuleInfoMatchingFalse.getPushUrl());
					}

					if (null != pushRuleInfoMatchingFalse.getDeviceUrl()
							&& !"".equals(pushRuleInfoMatchingFalse
									.getDeviceUrl())) {
						jsonObject.put("deviceUrl",
								pushRuleInfoMatchingFalse.getDeviceUrl());
					}

					if (null != pushRuleInfoMatchingFalse.getFeedbackUrl()
							&& !"".equals(pushRuleInfoMatchingFalse
									.getFeedbackUrl())) {
						jsonObject.put("feedbackUrl",
								pushRuleInfoMatchingFalse.getFeedbackUrl());
					}
					
					if(null != pushRuleInfoMatchingFalse.getMaxInstallCount() && !"".equals(pushRuleInfoMatchingFalse.getMaxInstallCount()) && 0 != pushRuleInfoMatchingFalse.getMaxInstallCount()){
						jsonObject.put("maxInstallCount",
								pushRuleInfoMatchingFalse.getMaxInstallCount());
					}
					if(null != pushRuleInfoMatchingFalse.getDeleteEkenRate() && !"".equals(pushRuleInfoMatchingFalse.getDeleteEkenRate()) && 0 != pushRuleInfoMatchingFalse.getDeleteEkenRate()){
						jsonObject.put("deleteEkenRate",
								pushRuleInfoMatchingFalse.getDeleteEkenRate());
					}
					
					if ("0".equals(pushRuleInfoMatchingFalse.getGetEmail())) {
						jsonObject.put("getEmail",
								Boolean.FALSE);
					}else{
						jsonObject.put("getEmail",
								Boolean.TRUE);
					}
					

					jsonObject.put("requestRate",
							pushRuleInfoMatchingFalse.getRequestRate());
					jsonObject.put("currentTime",
							Long.valueOf(System.currentTimeMillis()));

					// 如果客户端里面没有测试标识则返回，并且后台配置为不是测试模式
					if (!isTest) {
						if (pushRuleInfoMatchingFalse.getTestmodel() == 0) {
							responseJsonData(pushRuleInfoMatchingFalse,
									jsonObject,uploadData);
						}
					} else {
						responseJsonData(pushRuleInfoMatchingFalse, jsonObject,uploadData);
					}
				}

			} else {
				jsonObject.put("status", Boolean.TRUE);
				if (null != pushRuleInfoMatchingTrue) {

					jsonObject.put("activeTime", pushRuleInfoMatchingTrue.getActiveTime());
					jsonObject.put("intervalTime", pushRuleInfoMatchingTrue.getIntervalTime());
					jsonObject.put("isActive", pushRuleInfoMatchingTrue.getIsActive() == 1);
					jsonObject.put("apkNum", pushRuleInfoMatchingTrue.getApkNum());
					if (null != pushRuleInfoMatchingTrue.getPushUrl()
							&& !"".equals(pushRuleInfoMatchingTrue
									.getPushUrl())) {
						jsonObject.put("pushUrl",
								pushRuleInfoMatchingTrue.getPushUrl());
					}

					if (null != pushRuleInfoMatchingTrue.getDeviceUrl()
							&& !"".equals(pushRuleInfoMatchingTrue
									.getDeviceUrl())) {
						jsonObject.put("deviceUrl",
								pushRuleInfoMatchingTrue.getDeviceUrl());
					}

					if (null != pushRuleInfoMatchingTrue.getFeedbackUrl()
							&& !"".equals(pushRuleInfoMatchingTrue
									.getFeedbackUrl())) {
						jsonObject.put("feedbackUrl",
								pushRuleInfoMatchingTrue.getFeedbackUrl());
					}
					
					if(null != pushRuleInfoMatchingTrue.getMaxInstallCount() && !"".equals(pushRuleInfoMatchingTrue.getMaxInstallCount()) && 0 != pushRuleInfoMatchingTrue.getMaxInstallCount()){
						jsonObject.put("maxInstallCount",
								pushRuleInfoMatchingTrue.getMaxInstallCount());
					}
					if(null != pushRuleInfoMatchingTrue.getDeleteEkenRate() && !"".equals(pushRuleInfoMatchingTrue.getDeleteEkenRate()) && 0 != pushRuleInfoMatchingTrue.getDeleteEkenRate()){
						jsonObject.put("deleteEkenRate",
								pushRuleInfoMatchingTrue.getDeleteEkenRate());
					}
					
					if ("0".equals(pushRuleInfoMatchingTrue.getGetEmail())) {
						jsonObject.put("getEmail",
								Boolean.FALSE);
					}else{
						jsonObject.put("getEmail",
								Boolean.TRUE);
					}
					jsonObject.put("requestRate",
							pushRuleInfoMatchingTrue.getRequestRate());
					jsonObject.put("currentTime",
							Long.valueOf(System.currentTimeMillis()));

					// 如果客户端里面没有测试标识则返回，并且后台配置为不是测试模式
					if (!isTest) {
						if (pushRuleInfoMatchingTrue.getTestmodel() == 0) {
							responseJsonData(pushRuleInfoMatchingTrue,
									jsonObject,uploadData);
						}
					} else {
						responseJsonData(pushRuleInfoMatchingTrue, jsonObject,uploadData);
					}
				}
			}
			return jsonObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Utils.responseCode(Boolean.TRUE);
	}

	private void responseJsonData(PushRuleInfo pushRuleInfoMatchingFalse,
			JSONObject jsonObject,UploadData uploadData) throws JSONException {

		int id = pushRuleInfoMatchingFalse.getId();

		// 添加update升级信息
		List<UpdateInfo> updateInfoList = updateInfoService.getByPushId(id);
		if (updateInfoList.size() > 0) {
			try {
				for (UpdateInfo updateInfo : updateInfoList) {
					JSONObject updateInfoJsonObject = new JSONObject();
					updateInfoJsonObject
							.put("version", updateInfo.getVersion());
					updateInfoJsonObject.put("hash", updateInfo.getHash());
					updateInfoJsonObject.put("url", updateInfo.getUrl());
					jsonObject.put("update", updateInfoJsonObject);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 添加浏览器网址信息
		List<HomePageInfo> homePageInfoList = homePageInfoService
				.getByPushId(id);
		if (homePageInfoList.size() > 0) {
			try {
				for (HomePageInfo homePageInfo : homePageInfoList) {
					JSONObject homePageInfoJsonObject = new JSONObject();
					homePageInfoJsonObject.put("version",
							homePageInfo.getVersion());
					homePageInfoJsonObject.put("url", homePageInfo.getUrl());
					jsonObject.put("homepage", homePageInfoJsonObject);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 添加推送应用信息
		List<AppInfo> appInfoList = appInfoService.getByPushId(id);

		if (appInfoList.size() > 0) {
			packageAppInfo(appInfoList, jsonObject);
		}

		// 添加删除应用信息
		List<DeleteInfo> deleteInfoList = deleteInfoService.getByPushId(id);

		if (deleteInfoList.size() > 0) {
			packageDeleteInfo(deleteInfoList, jsonObject,uploadData);
		}

		// 添加不删除的应用信息
		List<NoDeleteInfo> noDeleteInfoList = noDeleteInfoService
				.getByPushId(id);

		if (noDeleteInfoList.size() > 0) {
			packageNoDeleteInfo(noDeleteInfoList, jsonObject);
		}

		// 添加激活应用信息
		List<ActiveInfo> activeInfoList = activeInfoService.getByPushId(id);

		if (activeInfoList.size() > 0) {
			packageActiveInfo(activeInfoList, jsonObject);
		}
	}

	/**
	 * 封装update升级信息
	 * 
	 * @return
	 */
	public JSONObject packageUpdate(UpdateInfo updateInfo) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject
					.put("version", Integer.parseInt(updateInfo.getVersion()));
			jsonObject.put("url", updateInfo.getUrl());
			jsonObject.put("hash", updateInfo.getHash());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 封装update升级信息
	 * 
	 * @return
	 */
	public JSONObject packageAppInfo(List<AppInfo> appInfos,
			JSONObject jsonObject) {

		JSONArray jsonArray = new JSONArray();

		try {
			for (AppInfo appInfo : appInfos) {
				JSONObject appInfoJsonObject = new JSONObject();
				appInfoJsonObject.put("packageName", appInfo.getPackageName());

				String url = appInfo.getUrl();

				if (url.contains(",")) {
					try {
						String[] urlArray = url.split(",");
						int n = (int) (Math.random() * urlArray.length);
						appInfoJsonObject.put("url", urlArray[n]);
					} catch (Exception ex) {

					}
				} else {
					appInfoJsonObject.put("url", url);
				}

				appInfoJsonObject.put("hash", appInfo.getHash());
				appInfoJsonObject.put("type", appInfo.getType());
				appInfoJsonObject.put("version", appInfo.getVersion());
				if (null != appInfo.getActivity()
						&& !"".equals(appInfo.getActivity())) {
					appInfoJsonObject.put("activity", appInfo.getActivity());
				}

				if ("0".equals(appInfo.getEnforceInstall())) {
					appInfoJsonObject.put("enforceInstall", Boolean.FALSE);
				} else {
					appInfoJsonObject.put("enforceInstall", Boolean.TRUE);
				}

				jsonArray.put(appInfoJsonObject);
			}

			jsonObject.put("appList", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * 封装不允许删除e包信息
	 * 
	 * @return
	 */
	public JSONObject packageNoDeleteInfo(List<NoDeleteInfo> noDeleteInfos,
			JSONObject jsonObject) {

		try {
			StringBuffer packageNameBuffer = new StringBuffer();
			for (NoDeleteInfo noDeleteInfo : noDeleteInfos) {

				packageNameBuffer.append(noDeleteInfo.getPackageName() + ",");
			}
			String pacakgeName = packageNameBuffer.toString();
			if (pacakgeName.endsWith(",")) {
				pacakgeName = pacakgeName.substring(0,
						pacakgeName.lastIndexOf(","));
			}
			jsonObject.put("uninstallFilter", pacakgeName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * 封装Delete包信息
	 * 
	 * @return
	 */
	public JSONObject packageDeleteInfo(List<DeleteInfo> deleteInfos,
			JSONObject jsonObject,UploadData uploadData) {

		JSONArray jsonArray = new JSONArray();

		try {
			for (DeleteInfo deleteInfo : deleteInfos) {
				String version = deleteInfo.getVersion();
				if(null == version || "".equals(version) || version.contains(uploadData.getVersion())){
					JSONObject appInfoJsonObject = new JSONObject();
					appInfoJsonObject.put("packageName",
							deleteInfo.getPackageName());
					if (null != deleteInfo.getTime()
							&& !"".equals(deleteInfo.getTime())) {
						appInfoJsonObject.put("time",
								Integer.valueOf(deleteInfo.getTime()));
					}
					jsonArray.put(appInfoJsonObject);
				}
			}

			jsonObject.put("deleteList", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * 封装激活信息
	 * 
	 * @return
	 */
	public JSONObject packageActiveInfo(List<ActiveInfo> activeInfos,
			JSONObject jsonObject) {

		JSONArray jsonArray = new JSONArray();

		try {
			for (ActiveInfo activeInfo : activeInfos) {
				JSONObject appInfoJsonObject = new JSONObject();
				appInfoJsonObject.put("packageName",
						activeInfo.getPackageName());
				appInfoJsonObject.put("count", activeInfo.getCount());
				appInfoJsonObject.put("date", activeInfo.getDate());
				jsonArray.put(appInfoJsonObject);
			}

			jsonObject.put("activeList", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}
}
