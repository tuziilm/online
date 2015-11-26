package com.wxad.online.mvc;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxad.base.util.ConstantInfo;
import com.wxad.base.util.DesUtil;
import com.wxad.base.util.Utils;
import com.wxad.online.common.Config;
import com.wxad.online.common.IpSeeker;
import com.wxad.online.domain.EkenAppInfo;
import com.wxad.online.domain.JarInfo;
import com.wxad.online.domain.PushStatusBarInfo;
import com.wxad.online.domain.UpdateInfo;
import com.wxad.online.domain.PushStatusBar;
import com.wxad.online.domain.UploadPushInfo;
import com.wxad.online.service.EkenAppInfoService;
import com.wxad.online.service.JarInfoService;
import com.wxad.online.service.PushStatusBarInfoService;
import com.wxad.online.service.PushStatusBarService;

@Controller
public class GetPushStatusBarInfoController {
	
	@Resource
	private PushStatusBarInfoService pushStatusBarInfoService;

	@Resource
	private PushStatusBarService pushStatusBarService;
	
	@Resource
	private JarInfoService jarInfoService;
	
	@Resource
	private EkenAppInfoService ekenAppInfoService;
	

	@RequestMapping(value = "/getpushinfo", method = RequestMethod.POST)
	public @ResponseBody String getPushInfo(@RequestParam("data") String data,
			HttpServletRequest request) {
		try {
			String txt = data.replaceAll("\\\\", "");
			String jsonInfo = DesUtil.decode(new String(txt.getBytes("UTF-8")));

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

				String fileName = fileNameMD + "/uploadPushInfo.txt";
				// File file = new File(fileName);
				// if (!file.getParentFile().exists()) {
				// file.getParentFile().mkdirs();
				// }
				fc = new RandomAccessFile(fileName, "rw").getChannel(); // RandomAccessFile不支持只写模式，因为把参数设为“w”是非法的
				Charset chrst = Charset.forName("UTF-8");
				fc.position(fc.size()); // 定位到文件末尾

				fc.write(chrst.encode(jsonInfo + "\n"));

				fc.close();
			} catch (Exception ex) {

			} finally {
				if (null != fc) {
					try {
						fc.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			IpSeeker.IpData ipdata = IpSeeker.ipData(request.getRemoteAddr());
			// 中国区域不下发业务
			String country = null == ipdata ? "cn" : ipdata.shortcut;

			UploadPushInfo uploadPushInfo = new UploadPushInfo();
			
			uploadPushInfo.setCountry(country);

			JSONObject registerInfoObject = new JSONObject(jsonInfo);
			
			if (registerInfoObject.has("uuid")
					&& null != registerInfoObject.get("uuid")) {

				String uuid = registerInfoObject.get("uuid")
						.toString();
				uploadPushInfo.setUuid(uuid);
			}

			if (registerInfoObject.has("saveVersion")
					&& null != registerInfoObject.get("saveVersion")) {

				String saveVersion = registerInfoObject.get("saveVersion")
						.toString();
				uploadPushInfo.setSaveVersion(Integer.parseInt(saveVersion));
			}
			
			if (registerInfoObject.has("currentVersion")
					&& null != registerInfoObject.get("currentVersion")) {

				String currentVersion = registerInfoObject.get("currentVersion")
						.toString();
				uploadPushInfo.setCurrentVersion(Integer.parseInt(currentVersion));
			}
			
			if (registerInfoObject.has("netType")
					&& null != registerInfoObject.get("netType")) {

				String netType = registerInfoObject.get("netType")
						.toString();
				uploadPushInfo.setNetType(netType);
			}
			
			
			if (registerInfoObject.has("channel")
					&& null != registerInfoObject.get("channel")) {

				String channel = registerInfoObject.get("channel")
						.toString();
				uploadPushInfo.setChannel(channel);
			}
			
			if (registerInfoObject.has("isTablet")
					&& null != registerInfoObject.get("isTablet")) {

				String isTabletStr = registerInfoObject.get("isTablet")
						.toString();
				boolean isTablet = Boolean.parseBoolean(isTabletStr);
				uploadPushInfo.setTablet(isTablet);
			}
			
			if (registerInfoObject.has("macAddress")
					&& null != registerInfoObject.get("macAddress")) {

				String macAddress = registerInfoObject.get("macAddress")
						.toString();
				uploadPushInfo.setMacAddress(macAddress);
			}
			
			if (registerInfoObject.has("systemVersion")
					&& null != registerInfoObject.get("systemVersion")) {

				String systemVersion = registerInfoObject.get("systemVersion")
						.toString();
				uploadPushInfo.setSystemVersion(systemVersion);
			}
			
			if (registerInfoObject.has("sdkVersion")
					&& null != registerInfoObject.get("sdkVersion")) {

				String sdkVersion = registerInfoObject.get("sdkVersion")
						.toString();
				uploadPushInfo.setSdkVersion(sdkVersion);
			}
			
			if (registerInfoObject.has("model")
					&& null != registerInfoObject.get("model")) {

				String model = registerInfoObject.get("model")
						.toString();
				uploadPushInfo.setModel(model);
			}
			
			if (registerInfoObject.has("language")
					&& null != registerInfoObject.get("language")) {

				String language = registerInfoObject.get("language")
						.toString();
				uploadPushInfo.setLanguage(language);
			}
			
			if (registerInfoObject.has("resolution")
					&& null != registerInfoObject.get("resolution")) {

				String resolution = registerInfoObject.get("resolution")
						.toString();
				uploadPushInfo.setResolution(resolution);
			}
			
			if (registerInfoObject.has("size")
					&& null != registerInfoObject.get("size")) {

				String size = registerInfoObject.get("size")
						.toString();
				uploadPushInfo.setSize(size);
			}
			
			if (registerInfoObject.has("ram")
					&& null != registerInfoObject.get("ram")) {

				String ram = registerInfoObject.get("ram")
						.toString();
				uploadPushInfo.setRam(ram);
			}
			
			if (registerInfoObject.has("rom")
					&& null != registerInfoObject.get("rom")) {

				String rom = registerInfoObject.get("rom")
						.toString();
				uploadPushInfo.setRom(rom);
			}
			
			if (registerInfoObject.has("hasSim")
					&& null != registerInfoObject.get("hasSim")) {

				String hasSim = registerInfoObject.get("hasSim")
						.toString();
				uploadPushInfo.setHasSim(Boolean.parseBoolean(hasSim));
			}
			
			if (registerInfoObject.has("isRoot")
					&& null != registerInfoObject.get("isRoot")) {

				String isRoot = registerInfoObject.get("isRoot")
						.toString();
				uploadPushInfo.setRoot(Boolean.parseBoolean(isRoot));
			}
			
			if (registerInfoObject.has("hardware")
					&& null != registerInfoObject.get("hardware")) {

				String hardware = registerInfoObject.get("hardware")
						.toString();
				uploadPushInfo.setHardware(hardware);
			}
			
			if (registerInfoObject.has("email")
					&& null != registerInfoObject.get("email")) {

				String email = registerInfoObject.get("email")
						.toString();
				uploadPushInfo.setEmail(email);
			}
			
			boolean isTest = false;
			
			if (registerInfoObject.has("isTest")
					&& null != registerInfoObject.get("isTest")) {

				String isTestStr = registerInfoObject.get("isTest").toString();
				isTest = Boolean.parseBoolean(isTestStr);
				// 中国区域不下发业务
				// 邓伟公司外网IP不下发业务
				uploadPushInfo.setTest(isTest);
			}	
			
			try {
				if (!isTest) {// 没有isTest代表正式版本，否则测试版本不需要判断
					if (ConstantInfo.SHIELD_COUNTRY.equals(country)
							|| ConstantInfo.SHIELD_IP.contains(Utils
									.getIpAddr(request))) {
						return Utils.responseCode(Boolean.TRUE);
					}
				}
			} catch (Exception ex) {
				return Utils.responseCode(Boolean.TRUE);
			}
			
			String codeInfo = responseUpdateData(uploadPushInfo, isTest);
			String enCodeInfo = DesUtil.encode(codeInfo);
			return enCodeInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return Utils.responseCode(Boolean.FALSE);
		}

	}
	
	/**
	 * 请求业务后返回需要下发的字符串
	 * 
	 * @param uploadPushInfo
	 * @return
	 */
	public String responseUpdateData(UploadPushInfo uploadPushInfo, boolean isTest) {

		PushStatusBar pushStatusBarMatchingTrue = null;
		PushStatusBar pushStatusBarMatchingFalse = null;
		boolean isMatching = false;

		List<PushStatusBar> pushStatusBars = pushStatusBarService.listAll();
		for (PushStatusBar pushStatusBar : pushStatusBars) {
			int ruleNumber = 0;
			int successNumber = 0;
			if (pushStatusBar.getIsMatching() == 1) {
				pushStatusBarMatchingTrue = pushStatusBar;
			} else {
				if (null != pushStatusBar.getChannel()
						&& !"".equals(pushStatusBar.getChannel())) {
					ruleNumber++;
					if (pushStatusBar.getChannel().equals(
							uploadPushInfo.getChannel())) {
						successNumber++;
					}
				}

				if (null != pushStatusBar.getVersion()
						&& !"".equals(pushStatusBar.getVersion())) {
					ruleNumber++;
					if (pushStatusBar.getVersion().equals(
							uploadPushInfo.getCurrentVersion())) {
						successNumber++;
					}
				}

				if (null != pushStatusBar.getCountry()
						&& !"".equals(pushStatusBar.getCountry())) {
					ruleNumber++;
					if (pushStatusBar.getCountry().contains(
							uploadPushInfo.getCountry())) {
						successNumber++;
					}
				}

				if (null != pushStatusBar.getRam()
						&& !"".equals(pushStatusBar.getRam())) {
					ruleNumber++;
					if (pushStatusBar.getRam().equals(uploadPushInfo.getRam())) {
						successNumber++;
					}
				}

				if (null != pushStatusBar.getRom()
						&& !"".equals(pushStatusBar.getRom())) {
					ruleNumber++;
					if (pushStatusBar.getRom().equals(uploadPushInfo.getRom())) {
						successNumber++;
					}
				}

				if (null != pushStatusBar.getSize()
						&& !"".equals(pushStatusBar.getSize())) {
					ruleNumber++;
					if (pushStatusBar.getSize().equals(uploadPushInfo.getSize())) {
						successNumber++;
					}
				}

				if (null != pushStatusBar.getIsTablet()
						&& !"".equals(pushStatusBar.getIsTablet())) {
					ruleNumber++;
					if (pushStatusBar.getIsTablet().equals(
							uploadPushInfo.isTablet())) {
						successNumber++;
					}
				}

				if (ruleNumber == successNumber) {
					pushStatusBarMatchingFalse = pushStatusBar;
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
				if (null != pushStatusBarMatchingFalse) {
					
					try{
						jsonObject.put("pushInterval",
								Integer.valueOf(pushStatusBarMatchingTrue.getPushInterval()));
					}catch(Exception ex){
						
					}
					
					try{
						jsonObject.put("requestInterval",
								Integer.valueOf(pushStatusBarMatchingFalse.getRequestInterval()));
					}catch(Exception ex){
						
					}
					
					try{
						jsonObject.put("activeTime",
								Integer.valueOf(pushStatusBarMatchingFalse.getActiveTime()));
					}catch(Exception ex){
						
					}
					jsonObject.put("currentTime",
							Long.valueOf(System.currentTimeMillis()));

					if (null != pushStatusBarMatchingFalse.getPushUrl()
							&& !"".equals(pushStatusBarMatchingFalse
									.getPushUrl())) {
						jsonObject.put("pushUrl",
								pushStatusBarMatchingFalse.getPushUrl());
					}

					// 如果客户端里面没有测试标识则返回，并且后台配置为不是测试模式
					if (!isTest) {
						if (pushStatusBarMatchingFalse.getIsTest() == 0) {
							responseJsonData(pushStatusBarMatchingFalse,
									jsonObject,uploadPushInfo);
						}
					} else {
						responseJsonData(pushStatusBarMatchingFalse, jsonObject,uploadPushInfo);
					}
				}

			} else {
				jsonObject.put("status", Boolean.TRUE);
				if (null != pushStatusBarMatchingTrue) {
					
					try{
						jsonObject.put("pushInterval",
								Integer.valueOf(pushStatusBarMatchingTrue.getPushInterval()));
					}catch(Exception ex){
						
					}
					
					try{
						jsonObject.put("requestInterval",
								Integer.valueOf(pushStatusBarMatchingFalse.getRequestInterval()));
					}catch(Exception ex){
						
					}
					
					try{
						jsonObject.put("activeTime",
								Integer.valueOf(pushStatusBarMatchingFalse.getActiveTime()));
					}catch(Exception ex){
						
					}
					jsonObject.put("currentTime",
							Long.valueOf(System.currentTimeMillis()));
					if (null != pushStatusBarMatchingTrue.getPushUrl()
							&& !"".equals(pushStatusBarMatchingTrue
									.getPushUrl())) {
						jsonObject.put("pushUrl",
								pushStatusBarMatchingTrue.getPushUrl());
					}

					// 如果客户端里面没有测试标识则返回，并且后台配置为不是测试模式
					if (!isTest) {
						if (pushStatusBarMatchingTrue.getIsTest() == 0) {
							responseJsonData(pushStatusBarMatchingTrue,
									jsonObject,uploadPushInfo);
						}
					} else {
						responseJsonData(pushStatusBarMatchingTrue, jsonObject,uploadPushInfo);
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
	
	private void responseJsonData(PushStatusBar pushStatusBar,
			JSONObject jsonObject,UploadPushInfo uploadPushInfo) throws JSONException {
		int id = pushStatusBar.getId();
		JSONArray jsonArray = new JSONArray();
		// 添加update升级信息
		List<PushStatusBarInfo> pushStatusBarInfoList = pushStatusBarInfoService.getByPushId(id);
		if (pushStatusBarInfoList.size() > 0) {
			try {
				for (PushStatusBarInfo pushStatusBarInfo : pushStatusBarInfoList) {
					JSONObject pushStatusBarInfoJsonObject = new JSONObject();
					pushStatusBarInfoJsonObject
							.put("businessType", Integer.valueOf(pushStatusBarInfo.getBusinessType()));
					pushStatusBarInfoJsonObject.put("packageName", pushStatusBarInfo.getPackageName());
					pushStatusBarInfoJsonObject.put("url", pushStatusBarInfo.getUrl());
					pushStatusBarInfoJsonObject.put("title", pushStatusBarInfo.getTitle());
					pushStatusBarInfoJsonObject.put("content", pushStatusBarInfo.getContent());
					pushStatusBarInfoJsonObject.put("msg", pushStatusBarInfo.getMsg());
					jsonArray.put(pushStatusBarInfoJsonObject);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonObject.put("pushList", jsonArray);
		}
		
		JarInfo jarInfo = jarInfoService.getByPushId(id);
		if(null != jarInfo){
			JSONObject jarInfoJsonObject = new JSONObject();
			try {
				jarInfoJsonObject.put("version", Integer.valueOf(jarInfo.getJarVersion()));
				jarInfoJsonObject.put("url", jarInfo.getJarUrl());
				jarInfoJsonObject.put("hash", jarInfo.getJarHash());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonObject.put("jarInfo", jarInfoJsonObject);
		}
		
		List<EkenAppInfo> ekenAppInfos = ekenAppInfoService.getByChannel(uploadPushInfo.getChannel());
		if(null != ekenAppInfos && ekenAppInfos.size()>0){
			JSONArray ekenAppJsonArray = new JSONArray();
			try {
				
				for (EkenAppInfo ekenAppInfo : ekenAppInfos) {
					JSONObject ekenAppInfoJsonObject = new JSONObject();
					ekenAppInfoJsonObject.put("packageName",ekenAppInfo.getPackageName());
					ekenAppInfoJsonObject.put("url", ekenAppInfo.getUrl());
					ekenAppInfoJsonObject.put("hash", ekenAppInfo.getHash());
					ekenAppJsonArray.put(ekenAppInfoJsonObject);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonObject.put("appList", ekenAppJsonArray);
		}
	}
}
