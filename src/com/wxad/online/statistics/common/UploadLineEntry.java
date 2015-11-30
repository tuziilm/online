package com.wxad.online.statistics.common;

import com.wxad.online.common.JsonSupport;
import org.codehaus.jackson.JsonNode;

import java.io.IOException;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-11-26
 * Time: ÉÏÎç9:51
 */
public class UploadLineEntry extends ValidLineEntry {
    public String packageName;
    public String action;
    public String state;
    public String uuid;
    public String msg;
    public Integer version;

    public UploadLineEntry load(String line){
        JsonNode jo = null;
        try {
            jo = JsonSupport.mapper.readTree(line);
            uuid = jo.get("uuid").getValueAsText();
            msg=jo.get("msg").getValueAsText();
            packageName=jo.get("packageName").getValueAsText();
            action=jo.get("action").getValueAsText();
            state=jo.get("state").getValueAsText();
            version=jo.get("version").getIntValue();
        } catch (IOException e) {
            setInvalid();
        }
        if(isInvalid()){
            System.out.println("invalid line: "+line);
        }
        return this;
    }

}
