package com.wxad.online.statistics.common;

import com.wxad.online.common.JsonSupport;
import org.codehaus.jackson.JsonNode;

import java.io.IOException;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-11-26
 * Time: ÉÏÎç9:51
 */
public class UploadDataLineEntry extends ValidLineEntry {
    public String channel;
    public String country;
    public String uuid;

    public UploadDataLineEntry load(String line){
        JsonNode jo = null;
        try {
            jo = JsonSupport.mapper.readTree(line);
            uuid = jo.get("uuid").getValueAsText();
            channel=jo.get("channel").getValueAsText();
            country=jo.get("country")==null?"unknow":jo.get("country").getValueAsText();
        } catch (Exception e) {
            setInvalid();
        }
        if(isInvalid()){
            System.out.println("invalid line: "+line);
        }
        return this;
    }

}
