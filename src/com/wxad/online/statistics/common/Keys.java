package com.wxad.online.statistics.common;

public class Keys {
    public final static String[] toKeys(String first, String second, String third){
        return new String[]{first + Config.SEP + second + Config.SEP + third,
                "all" + Config.SEP + second + Config.SEP + third,
                first + Config.SEP + "all" + Config.SEP + third,
                first + Config.SEP + second + Config.SEP + "all",
                "all" + Config.SEP + "all" + Config.SEP + third,
                "all" + Config.SEP + second + Config.SEP + "all",
                first + Config.SEP + "all" + Config.SEP + "all",
                "all" + Config.SEP + "all" + Config.SEP + "all"
        };
    }
    public final static String[] toKeys(String first, String second){
        return new String[]{first + Config.SEP + second,
                "all" + Config.SEP + second,
                first + Config.SEP + "all",
                "all" + Config.SEP + "all"
        };
    }
}
