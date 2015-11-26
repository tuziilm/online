package com.wxad.online.mvc;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxad.base.util.DesUtil;

@Controller
public class GetJarInfo {
	
	@RequestMapping(value = "/downloadurl", method = RequestMethod.POST)
	public @ResponseBody String uploadData() {
		JSONObject responseObject = new JSONObject();
		try {
			responseObject.put("version", Integer.valueOf(4));
			responseObject.put("url", "http://www.wxaddxx.com:88/push/apk/classes.dex");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String codeInfo = responseObject.toString();
		String responseInfo = DesUtil.encode(codeInfo);
		return responseInfo;
	}
}
