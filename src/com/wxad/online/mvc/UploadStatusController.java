package com.wxad.online.mvc;

import com.wxad.online.common.Paginator;
import com.wxad.online.domain.AppInfo;
import com.wxad.online.domain.UploadStatus;
import com.wxad.online.service.AppInfoService;
import com.wxad.online.service.UploadStatusService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Author: <a href="tuziilm@163.com">Tuziilm</a>
* Date: 15-11-27
* Time: ÉÏÎç11:36
*/
@Controller
@RequestMapping("/upload/status")
public class UploadStatusController extends ListController<UploadStatus,UploadStatusService,UploadStatusController.Query> {

    @Resource
    private AppInfoService appInfoService;

    public UploadStatusController() {
        super("upload/status");
    }

    public void setUploadStatusService(UploadStatusService uploadStatusService) {
        this.service = uploadStatusService;
    }

    @Override
    protected boolean preList(int page, Paginator paginator, Query query, Model model) {
        List<AppInfo> appInfoList = (List<AppInfo>)appInfoService.getAppInfosCache();
        model.addAttribute("apps", appInfoList);
        return super.preList(page, paginator, query, model);
    }

    public static class Query extends com.wxad.online.common.Query {
        private String startTime;
        private String endTime;
        private String packageName;
        private String action;
        private String state;
        private String version;

        public Query() {
            this.startTime = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
            this.endTime = DateFormatUtils.format(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime.replaceAll("/", "-");
            this.addItem("startTime", startTime);
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime.replaceAll("/", "-");
            this.addItem("endTime", endTime);
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
            this.addItem("packageName", packageName);
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
            this.addItem("action", action);
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
            this.addItem("state", state);
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
            this.addItem("version", version);
        }
    }
}
