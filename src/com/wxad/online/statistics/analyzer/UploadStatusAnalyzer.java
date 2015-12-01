package com.wxad.online.statistics.analyzer;

import com.wxad.online.common.DateUtils;
import com.wxad.online.statistics.common.AbstractLogFileHandler;
import com.wxad.online.statistics.common.Config;
import com.wxad.online.statistics.common.DataHolder;
import com.wxad.online.statistics.common.Keys;
import com.wxad.online.statistics.common.TaskActionData;
import com.wxad.online.statistics.common.UploadStatusData;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadStatusAnalyzer extends AbstractUploadStatusDailyAnalyzer<UploadStatusData, UploadStatusAnalyzer.PreProcessLineEntry> {
    private List<UploadStatusData> uploadStatusDatas = new ArrayList<>();
    private Map<String, UploadStatusData> uploadStatusDataMap = new HashMap<>();
    public UploadStatusAnalyzer(Date date) {
        super(date);
	}

    @Override
    public String name() {
        return "UploadStatusAnalyzer";
    }

    public class PreProcessLineEntry extends AbstractUploadStatusDailyAnalyzer.PreProcessLineEntry{

		@Override
		public PreProcessLineEntry load(String line) {
            date = DateUtils.yesterdayString("yyyy-MM-dd");
            super.load(line);
            return this;
        }

        @Override
        public void write(int fIdx) {
            String key = packageName + Config.SEP + action + Config.SEP + state + Config.SEP + msg + Config.SEP + date + Config.SEP + version;
            UploadStatusData uploadStatusData = uploadStatusDataMap.get(key);
            if (uploadStatusData == null) {
                uploadStatusData = new UploadStatusData(packageName, action, state, msg, date, version, 1);
                uploadStatusDataMap.put(key, uploadStatusData);
            } else {
                uploadStatusData.setCount(uploadStatusData.getCount() + 1);
            }
        }
    }

    @Override
	public DataHolder<UploadStatusData> packData() {
        final List<UploadStatusData> datas = new ArrayList<>(uploadStatusDataMap.size());
        for (Map.Entry<String, UploadStatusData> entry : uploadStatusDataMap.entrySet()) {
            datas.add(entry.getValue());
        }
        return new DataHolder<UploadStatusData>() {
            @Override
            public String sql() {
                return "insert into update_status(package_name, action, state, msg, date, version, count, gmt_create) values(?,?,?,?,?,?,?,now())";
            }

            @Override
            public List<UploadStatusData> datas() {
                return datas;
            }

            @Override
            public void setPstmt(UploadStatusData data, PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, data.getPackageName());
                pstmt.setString(2, data.getAction());
                pstmt.setString(3, data.getState());
                pstmt.setString(4, data.getMsg());
                pstmt.setString(5, data.getDate());
                pstmt.setInt(6, data.getVersion());
                pstmt.setInt(7, data.getCount());
            }
        };
    }

    @Override
    protected PreProcessLineEntry loadPreProcessLineEntry(String line) {
        PreProcessLineEntry entry= new PreProcessLineEntry().load(line);
        if (entry.isInvalid()){
            System.out.println("invalid line:"+line);
        }
        return entry;
    }

    @Override
    protected AbstractLogFileHandler processLogFileHandler() {
        return null;
    }
}
