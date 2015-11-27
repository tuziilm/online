package com.wxad.online.statistics.analyzer;

import com.wxad.online.statistics.common.AbstractLogFileHandler;
import com.wxad.online.statistics.common.DataHolder;
import com.wxad.online.statistics.common.UploadStatusData;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadStatusAnalyzer extends AbstractUploadStatusDailyAnalyzer<UploadStatusData, UploadStatusAnalyzer.PreProcessLineEntry> {
    private List<UploadStatusData> uploadStatusDatas = new ArrayList<>();

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
			super.load(line);
			return this;
		}

        @Override
        public void write(int fIdx) {
            UploadStatusData data = new UploadStatusData(packageName, action, state, uuid, msg, version);
            uploadStatusDatas.add(data);
        }
    }

    @Override
	public DataHolder<UploadStatusData> packData() {
        final List<UploadStatusData> datas = uploadStatusDatas;

        return new DataHolder<UploadStatusData>() {
            @Override
            public String sql() {
                return "insert into update_status(package_name, action, state, uuid, msg, version, gmt_create) values(?,?,?,?,?,?,now())";
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
                pstmt.setString(4, data.getUuid());
                pstmt.setString(5, data.getMsg());
                pstmt.setInt(6, data.getVersion());
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
