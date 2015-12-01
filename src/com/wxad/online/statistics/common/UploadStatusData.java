package com.wxad.online.statistics.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-11-26
 * Time: ÉÏÎç11:50
 */
public class UploadStatusData {
    private String packageName;
    private String action;
    private String state;
    private String msg;
    private String date;
    private Integer version;
    private Integer count;

    public UploadStatusData(String packageName, String action, String state, String msg, String date, Integer version,Integer count) {
        this.packageName = packageName;
        this.action = action;
        this.state = state;
        this.msg = msg;
        this.date = date;
        this.version = version;
        this.count = count;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
