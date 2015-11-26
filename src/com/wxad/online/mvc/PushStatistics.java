package com.wxad.online.mvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wxad.online.common.Config;
import com.wxad.online.common.IpSeeker;
import com.wxad.online.domain.PushStatisticsInfo;
import com.wxad.online.service.PushStatisticsInfoService;

@Controller
public class PushStatistics {
	
	@Resource
	private PushStatisticsInfoService pushStatisticsInfoService;
	
	@RequestMapping(value = "/pushstatistics")
	public String uploadData(@RequestParam("data") String data,
			HttpServletRequest request) {
		String datakey = data;
		
		try {
			
			IpSeeker.IpData ipdata = IpSeeker.ipData(request.getRemoteAddr());
			// 中国区域不下发业务
			String country = null == ipdata ? "cn" : ipdata.shortcut;
			
			JSONObject jsonInfo = new JSONObject();
			try {
				jsonInfo.put("country", country);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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

				String fileName = fileNameMD + "/"+data+".txt";
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
					fc.close();
				}
			}
		} catch (Exception ex) {

		}
		
		PushStatisticsInfo pushStatisticsInfo = pushStatisticsInfoService.getByDataKey(datakey);
		
		return "redirect:"+pushStatisticsInfo.getValue();
	}

}
