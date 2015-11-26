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
    private String uuid;
    private String msg;
    private Integer version;

    public UploadStatusData(String packageName, String action, String state, String uuid, String msg, Integer version) {
        this.packageName = packageName;
        this.action = action;
        this.state = state;
        this.uuid = uuid;
        this.msg = msg;
        this.version = version;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
