package com.wxad.online.statistics.analyzer;

import com.wxad.online.statistics.common.AbstractLogFileHandler;
import com.wxad.online.statistics.common.Config;
import com.wxad.online.statistics.common.UploadDataLineEntry;

import java.io.IOException;
import java.util.Date;

public abstract class AbstractUploadDataDailyAnalyzer<T,PPLE extends AbstractUploadDataDailyAnalyzer.PreProcessLineEntry> extends AbstractAnalyzer<T>{

    public AbstractUploadDataDailyAnalyzer(Date date) {
        super(date, new NDay("LINK_NODE_DAY_1", Config.getPreNDaysUploadTxtFiles(1, date ,1),Config.getPreNDaysStrings(1, date)));
    }

    public abstract static class PreProcessLineEntry extends UploadDataLineEntry {
        public String date;

        @Override
        public PreProcessLineEntry load(String line) {
            super.load(line);
            return this;
        }
        public abstract void write(int fIdx);
    }

    public void process() throws IOException {
        System.out.println("进来处理");
    }

    protected abstract PPLE loadPreProcessLineEntry(String line);

    protected abstract AbstractLogFileHandler processLogFileHandler();

    /**
     * 预处理
     */
    protected void preprocess() throws IOException {
        System.out.println("预处理操作");
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
