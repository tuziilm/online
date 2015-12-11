package com.wxad.online.statistics;

import com.wxad.online.statistics.analyzer.Analyzer;
import com.wxad.online.statistics.analyzer.LinkNodePvUvAnalyzer;
import com.wxad.online.statistics.analyzer.TaskActionDataAnalyzer;
import com.wxad.online.statistics.analyzer.UploadDataAnalyzer;
import com.wxad.online.statistics.analyzer.UploadStatusAnalyzer;
import com.wxad.online.statistics.common.ChartPvUvData;
import com.wxad.online.statistics.common.DataHolder;
import com.wxad.online.statistics.common.DatabaseHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Date;

public final class LogStatisticsRunner {
	public final static void persistToDatabase(Analyzer analyzer) throws Exception {
		try {
			System.out.printf("[%s]analyzing....\n", analyzer.name());
			DataHolder<ChartPvUvData> data = analyzer.analyze();
			System.out.printf("[%s]ready for persist result to database\n", analyzer.name());
			DatabaseHelper.persistToDatabase(data);
		} catch (SQLException e) {
			System.out.printf("[%s]failed to persist result to database\n", analyzer.name());
			e.printStackTrace();
		}
		System.out.printf("[%s]work done.\n", analyzer.name());
	}
//	public static void excute(String[] args) throws Exception {
	public void excute() throws Exception {
        Date date = new Date();
//        if(args.length>0){
//            date = DateUtils.parseDate(args[0], "yyyy-MM-dd");
//        }
        String type="all";
//        if(args.length>1){
//            type=args[1];
//        }
        Analyzer[] analyzers = new Analyzer[0];
        switch (type){
            case "all":
                analyzers = new Analyzer[]{new LinkNodePvUvAnalyzer(date), new TaskActionDataAnalyzer(date), new UploadStatusAnalyzer(date),new UploadDataAnalyzer(date)};
                break;
            case "pvuv":
                analyzers = new Analyzer[]{new LinkNodePvUvAnalyzer(date)};
                break;
            case "task":
                analyzers = new Analyzer[]{new TaskActionDataAnalyzer(date)};
                break;
            case "upload":
                analyzers = new Analyzer[]{new UploadStatusAnalyzer(date)};
            case "uploadData":
                analyzers = new Analyzer[]{new UploadDataAnalyzer(date)};
        }
        for(Analyzer analyzer: analyzers) {
            persistToDatabase(analyzer);
        }
    }
    public static void main(String[] args) throws JSONException {
        Date date = new Date();
        String type="uploadData";
//        String type="task";
        Analyzer[] analyzers = new Analyzer[0];
        switch (type){
            case "all":
                analyzers = new Analyzer[]{new LinkNodePvUvAnalyzer(date), new TaskActionDataAnalyzer(date), new UploadStatusAnalyzer(date),new UploadDataAnalyzer(date)};
                break;
            case "pvuv":
                analyzers = new Analyzer[]{new LinkNodePvUvAnalyzer(date)};
                break;
            case "task":
                analyzers = new Analyzer[]{new TaskActionDataAnalyzer(date)};
                break;
            case "upload":
                analyzers = new Analyzer[]{new UploadStatusAnalyzer(date)};
            case "uploadData":
                analyzers = new Analyzer[]{new UploadDataAnalyzer(date)};
        }
        for(Analyzer analyzer: analyzers) {
            try {
                persistToDatabase(analyzer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
