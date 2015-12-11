package com.wxad.online.statistics.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-11-26
 * Time: ÉÏÎç11:50
 */
public class UploadData {
    private String key;
    private String date;
    private Integer count;

    public UploadData( String key, String date, Integer count) {
        this.key = key;
        this.date = date;
        this.count = count;
    }
    public class KeyEntry{
        public String channel;
        public String country;

        public KeyEntry(String channel, String country) {
            this.channel = channel;
            this.country = country;
        }
    }

    public KeyEntry getKeyEntry(){
        String[] fields = key.split(Config.SEP);
        return new KeyEntry(fields[0], fields[1]);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
