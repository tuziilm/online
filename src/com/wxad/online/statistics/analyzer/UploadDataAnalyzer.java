package com.wxad.online.statistics.analyzer;

import com.wxad.online.common.DateUtils;
import com.wxad.online.statistics.common.AbstractLogFileHandler;
import com.wxad.online.statistics.common.Config;
import com.wxad.online.statistics.common.DataHolder;
import com.wxad.online.statistics.common.Keys;
import com.wxad.online.statistics.common.UploadData;
import com.wxad.online.statistics.common.UploadStatusData;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UploadDataAnalyzer extends AbstractUploadDataDailyAnalyzer<UploadData, UploadDataAnalyzer.PreProcessLineEntry> {
    private Map<String, UploadData> uploadDataMap = new HashMap<>();
    private Set<String> uuidSet = new HashSet<>();
    public UploadDataAnalyzer(Date date) {
        super(date);
	}

    @Override
    public String name() {
        return "UploadDataAnalyzer";
    }

    public class PreProcessLineEntry extends AbstractUploadDataDailyAnalyzer.PreProcessLineEntry{

		@Override
		public PreProcessLineEntry load(String line) {
            date = DateUtils.yesterdayString("yyyy-MM-dd");
            super.load(line);
            return this;
        }

        @Override
        public void write(int fIdx) {
            boolean exist = uuidSet.contains(uuid);
            String[] keys = Keys.toKeys(channel, country);
            for(String key : keys){
                UploadData uploadData = uploadDataMap.get(key);
                if (uploadData == null) {
                    if(!exist) {
                        uuidSet.add(uuid);
                        uploadData = new UploadData(key, date, 1);
                        uploadDataMap.put(key, uploadData);
                    }
                } else {
                    if(!exist){
                        uploadData.setCount(uploadData.getCount() + 1);
                        uuidSet.add(uuid);
                    }
                }
            }
        }
    }

    @Override
	public DataHolder<UploadData> packData() {
        final List<UploadData> datas = new ArrayList<>(uploadDataMap.size());
        for (Map.Entry<String, UploadData> entry : uploadDataMap.entrySet()) {
            datas.add(entry.getValue());
        }
        return new DataHolder<UploadData>() {
            @Override
            public String sql() {
                return "insert into onlinestatistics(channel, datetime, total,country) values(?,?,?,?) on duplicate key update total = ?";
            }

            @Override
            public List<UploadData> datas() {
                return datas;
            }

            @Override
            public void setPstmt(UploadData data, PreparedStatement pstmt) throws SQLException {
                UploadData.KeyEntry keyEntry = data.getKeyEntry();
                pstmt.setString(1, keyEntry.channel);
                pstmt.setString(2, data.getDate());
                pstmt.setInt(3, data.getCount());
                pstmt.setString(4, keyEntry.country);
                pstmt.setInt(5, data.getCount());
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
