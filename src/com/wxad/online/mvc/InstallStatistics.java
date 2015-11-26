package com.wxad.online.mvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxad.base.util.DesUtil;
import com.wxad.base.util.Utils;
import com.wxad.online.common.Config;
import com.wxad.online.domain.UploadDataInfo;

@Controller
@RequestMapping(value = "/install")
public class InstallStatistics {
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	public @ResponseBody String uploadData(UploadDataInfo data,
			HttpServletRequest request) {
		try {
			String txt = data.getData().replaceAll("\\\\", "");
			String jsonInfo = DesUtil.decode(new String(txt.getBytes("UTF-8")));

			FileChannel fc = null;
			try {
				Date date = new Date();
				String relativeDir = new SimpleDateFormat("yyyyMMdd")
						.format(date);
				String relativeDirM = new SimpleDateFormat("yyyyMM")
				.format(date);
				
				String fileNameM = Config.UPLOAD_ROOT_DIR +"/"+relativeDirM;
				
				File fileM = new File(fileNameM);
				if(! fileM.exists()){
					fileM.mkdirs();
				}
				
				String fileNameMD = fileNameM+"/"+relativeDir;
				
				File fileMD = new File(fileNameMD);
				if(! fileMD.exists()){
					fileMD.mkdirs();
				}
				
				String fileName = fileNameMD+ "/installStatistics.txt";
				
//				File file = new File(fileName);
//				if(! file.getParentFile().exists()){
//					file.getParentFile().mkdirs();
//				}
				
				fc = new RandomAccessFile(fileName, "rw").getChannel(); // RandomAccessFile不支持只写模式，因为把参数设为“w”是非法的
				Charset chrst = Charset.forName("UTF-8");
				fc.position(fc.size()); // 定位到文件末尾

				fc.write(chrst.encode(jsonInfo + "\n"));

				fc.close();
			} catch (Exception ex) {
				ex.printStackTrace();
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
}
