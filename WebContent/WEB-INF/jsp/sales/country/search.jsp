<%@ include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css"/>
<div id="search">
    <form id="search_form" action="${basePath}sales/country/list" method="post"
          class="form-inline pull-right">
        <label>开始时间:</label>
        <input value="${online:defVal(param.startTime,online:yesterdayString("yyyy/MM/dd"))}" type="text"
               name="startTime" class="input-small" id="startTime"/>
        <label>结束时间:</label>
        <input value="${online:defVal(param.datetime,online:yesterdayString("yyyy/MM/dd"))}" type="text" name="datetime" class="input-small" id="datetime"/>
        <input type="submit" class="btn" value="查询"/>
    </form>
</div>
