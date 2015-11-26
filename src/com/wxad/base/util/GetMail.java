package com.wxad.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.wxad.online.common.Config;
import com.wxad.online.domain.DiscountInfo;
import com.wxad.online.domain.OnlineGroupInfo;
import com.wxad.online.domain.OnlineStatisticsInfo;
import com.wxad.online.domain.RegisterInfo;
import com.wxad.online.domain.SalesmanAndChannelInfo;
import com.wxad.online.domain.SalesmanDiscountInfo;
import com.wxad.online.domain.SalesmanStatisticsInfo;

public class GetMail {

	public static void main(String[] args) {
		try {
			Date dNow = new Date(); // 当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(dNow);// 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间
			String relativeDir = new SimpleDateFormat("yyyyMMdd")
					.format(dBefore);
			String datetime = new SimpleDateFormat("yyyy-MM-dd")
					.format(dBefore);
			String relativeDirM = new SimpleDateFormat("yyyyMM")
					.format(dBefore);

			
			
			List<String> fileUrl = new ArrayList<String>();
			
			for(int i=1;i<24;i++){
				String fileName = "f:/union/9/"+i;
				fileUrl.add(fileName);
			}

			for(String fileNameM : fileUrl){
				File fileMD = new File(fileNameM);
	
				File[] fileList = fileMD.listFiles();
	
				for (File file : fileList) {
					System.out.println("fileName : " + file.getName());
					String urlName = file.getPath();
	
					if (urlName.contains("getconfig")) {
						ReadFile(urlName, relativeDir);
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}

	public static void ReadFile(String filename, String dateTime) {
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

			List<String> registerInfoList = new ArrayList<String>();

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

						if (registerInfoObject.has("email")
								&& null != registerInfoObject.get("email")) {
							String email = registerInfoObject.get("email")
									.toString();
							if (email.length() < 1000) {

								String[] emailArray = email.split(",");
								for (int i = 0; i < emailArray.length; i++) {
									
									String emailStr = emailArray[i];
									if(emailStr.contains("@")){
										saveFile(emailStr);
									}
								}
							}

						} 

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
	
	public static void saveFile(String email){
		FileChannel fc = null;

		String fileNameM = "D:/union/";
		try {
			File fileM = new File(fileNameM);
			if (!fileM.exists()) {
				fileM.mkdirs();
			}

			String fileName =  fileM + "/mail.txt";
			File file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fc = new RandomAccessFile(fileName, "rw").getChannel(); // RandomAccessFile不支持只写模式，因为把参数设为“w”是非法的
			Charset chrst = Charset.forName("UTF-8");
			fc.position(fc.size()); // 定位到文件末尾

			fc.write(chrst.encode(email + "\n"));

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
	}

}
