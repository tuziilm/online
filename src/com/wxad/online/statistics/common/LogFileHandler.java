package com.wxad.online.statistics.common;

public interface LogFileHandler<T extends ValidLineEntry> {
    void handleLine(int fIdx, T entry);
}
