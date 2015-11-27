<%@ include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css"/>
<div id="search">
    <form id="search_form" action="" method="post"
          class="form-inline pull-right">
        <label>名称:</label>
        <select name="packageName" id="pkgName_sel" class="input-small">
            <option value="">全部</option>
            <c:forEach items="${apps}" var="app">
                <option value="${app.packageName}">${app.name}</option>
            </c:forEach>
        </select>
        <script type="text/javascript">
            document.getElementById("pkgName_sel").value = '${online:defVal(param.packageName,"")}';
        </script>
        <label>操作:</label>
        <select name="action" id="action_sel" class="input-small">
            <option value="">全部</option>
            <option value="active">active</option>
            <option value="delete">delete</option>
            <option value="download">download</option>
            <option value="install">install</option>
        </select>
        <script type="text/javascript">
            document.getElementById("action_sel").value = '${online:defVal(param.action,"")}';
        </script>
		<label>开始时间:</label>
        <input value="${online:defVal(param.startTime,online:yesterdayString("yyyy/MM/dd"))}" type="text"
               name="startTime" class="input-small" id="startTime"/>
        <label>结束时间:</label>
        <input value="${online:defVal(param.endTime,online:yesterdayString("yyyy/MM/dd"))}" type="text" name="endTime"
               class="input-small" id="endTime"/>
        <input type="button" class="btn" value="查询" onclick="javascript:doQuery()"/>
    </form>
</div>
