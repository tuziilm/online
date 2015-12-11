package com.wxad.online.statistics.analyzer;

import com.wxad.online.statistics.common.AbstractLogFileHandler;
import com.wxad.online.statistics.common.Config;
import com.wxad.online.statistics.common.LineEntry;
import com.wxad.online.statistics.common.UploadLineEntry;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractUploadStatusDailyAnalyzer<T,PPLE extends AbstractUploadStatusDailyAnalyzer.PreProcessLineEntry> extends AbstractAnalyzer<T>{

    public AbstractUploadStatusDailyAnalyzer(Date date) {
        super(date, new NDay("LINK_NODE_DAY_1", Config.getPreNDaysUploadTxtFiles(1, date, 2),Config.getPreNDaysStrings(1, date)));
    }

    public abstract static class PreProcessLineEntry extends UploadLineEntry{
        public String date;

        @Override
        public PreProcessLineEntry load(String line) {
            super.load(line);
            return this;
        }
        public abstract void write(int fIdx);
    }

    public void process() throws IOException {
        System.out.println("��������");
//        File dir = new File(workDir+"/"+nDay.dayStrings[0]+"/uploadStatus.txt");
//        if(dir.exists()) {
//            File[] files = dir.listFiles();
//            AbstractLogFileHandler logFileHandler = processLogFileHandler();
//            if(logFileHandler!=null) {
//                logFileHandler.handleFile(files);
//            }
//            FileUtils.deleteDirectory(dir);
//        }
    }

    protected abstract PPLE loadPreProcessLineEntry(String line);

    protected abstract AbstractLogFileHandler processLogFileHandler();

    /**
     * Ԥ����
     */
    protected void preprocess() throws IOException {
        System.out.println("Ԥ�������");
        AbstractLogFileHandler handler = new AbstractLogFileHandler<PPLE>(){
            public int count=0;

            @Override
            public void postHandleMultiFiles() {
                System.out.println("count:"+count);
            }

            @Override
            public PPLE loadLineEntry(String line) {
                return loadPreProcessLineEntry(line);
            }

            @Override
            public void handleLine(int fIdx, PPLE entry) {
                if(entry.isValid()){
                    entry.write(fIdx);
                    count++;
                    if(count%100000==0){
                        System.out.println("count:" +count);
                    }
                }
            }
        };
        handler.handleFile(nDay.logFiles);
    }
}
