package com.wxad.online.persistence;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.UploadStatistics;

public interface UploadStatisticsMapper extends BaseMapper<UploadStatistics>{

    int getSum(Paginator paginator);
}