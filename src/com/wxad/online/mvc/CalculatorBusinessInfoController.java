package com.wxad.online.mvc;

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
import com.wxad.online.common.IpSeeker;
import com.wxad.online.domain.CalculatorBusinessInfo;
import com.wxad.online.service.CalculatorBusinessInfoService;

@Controller
public class CalculatorBusinessInfoController {
	
	@Resource
	private CalculatorBusinessInfoService calculatorBusinessInfoService;
	
	@RequestMapping(value = "/calculator/business", method = RequestMethod.POST)
	public @ResponseBody String uploadData(@RequestParam("data") String data,HttpServletRequest request) {
		
		IpSeeker.IpData ipdata = IpSeeker.ipData(request.getRemoteAddr());
		// 中国区域不下发业务
		String country = null == ipdata ? "cn" : ipdata.shortcut;
		
		if (ConstantInfo.SHIELD_COUNTRY.equals(country)
				|| ConstantInfo.SHIELD_IP.contains(Utils
						.getIpAddr(request))) {
			return Utils.responseCode(Boolean.TRUE);
		}
		
		CalculatorBusinessInfo calculatorBusinessInfo = calculatorBusinessInfoService.getById(1);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		JSONObject responseObject = new JSONObject();
		try {
			
			jsonObject.put("version", Integer.valueOf(calculatorBusinessInfo.getVersion()));
			jsonObject.put("version", Integer.valueOf(calculatorBusinessInfo.getVersion()));
			jsonObject.put("url", calculatorBusinessInfo.getUrl());
			jsonObject.put("packageName", calculatorBusinessInfo.getPackageName());
			jsonObject.put("hash", calculatorBusinessInfo.getHash());
			jsonObject.put("name", calculatorBusinessInfo.getName());
			jsonObject.put("activity", calculatorBusinessInfo.getActivity());
			jsonArray.put(jsonObject);
			responseObject.put("appList", jsonArray);
			responseObject.put("status", Boolean.TRUE);
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String codeInfo = responseObject.toString();
		String responseInfo = DesUtil.encode(codeInfo);
		return responseInfo;
	}
}
