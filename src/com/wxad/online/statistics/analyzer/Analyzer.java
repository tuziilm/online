package com.wxad.online.statistics.analyzer;

import com.wxad.online.statistics.common.DataHolder;

public interface Analyzer<T> {
    String name();
    DataHolder<T> analyze() throws Exception;
}
