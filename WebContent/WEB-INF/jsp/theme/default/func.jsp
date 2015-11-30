<%@ include file="../../include/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${_module=='sales' }">
        <li class="${_underSales}"><a href="${basePath}sales/sales/list">销量统计</a></li>
        <li class="${_underCountry}"><a href="${basePath}sales/country/list">国家统计</a></li>
    </c:when>

    <c:when test="${_module=='online' }">
        <c:if test="${online:isAdmin()}">
            <li class="${_underOnline}"><a href="${basePath}online/list">在线量</a></li>
        </c:if>
    </c:when>
    <c:when test="${_module=='pushrule' }">
        <li class="${_underPushRule}"><a href="${basePath}pushRule/list">push规则</a></li>
    </c:when>
    <c:when test="${_module=='pushStatusBar' }">
        <li class="${_underPushStatusBar}"><a href="${basePath}pushStatusBar/list">push状态栏规则</a></li>
    </c:when>
    <c:when test="${_module=='upload' }">
        <li class="${_underUploadStatus}"><a href="${basePath}upload/status/list">日志分析</a></li>
    </c:when>
    <c:when test="${_module=='push' }">
        <li class="${_underPushApp}"><a href="${basePath}push/app/list">内置应用数据</a></li>
        <li class="${_underPushActive}"><a href="${basePath}push/active/list">激活数据</a></li>
        <li class="${_underPushDelete}"><a href="${basePath}push/delete/list">删除数据</a></li>
        <li class="${_underPushUpdate}"><a href="${basePath}push/update/list">更新数据</a></li>
        <li class="${_underPushHomePage}"><a href="${basePath}push/homepage/list">浏览器首页</a></li>
        <li class="${_underPushBasicRules}"><a href="${basePath}push/basicrules/list">基本设置首页</a></li>
        <li class="${_underPushStatusBar}"><a href="${basePath}push/statusbar/list">状态栏数据</a></li>
    </c:when>
   	<c:when test="${_module=='system' }">
		<c:if test="${online:isAdmin()}">
			<li class="${_underSysUser}"><a href="${basePath}sysuser/list">系统用户</a></li>
		</c:if>
		<li class="${_underUserInfo}"><a href="${basePath}sysuser/${isUnderUserInfo?'info_modify':'modify'}/${online:uid()}">信息修改</a></li>
	</c:when>
</c:choose>
