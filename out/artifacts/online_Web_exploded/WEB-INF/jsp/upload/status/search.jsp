<%@ include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css"/>
<div id="search">
    <form id="search_form" action="" method="post"
          class="form-inline pull-right">
        <label>名称:</label>
        <select name="packageName" id="pkgName_sel" class="input-small">
            <option value="">全部</option>
            <c:forEach items="${pkgs}" var="pkg">
                <option value="${pkg.pkgName}">${pkg.appName}</option>
            </c:forEach>
        </select>
        <script type="text/javascript">
            document.getElementById("pkgName_sel").value = '${online:defVal(param.packageName,"")}';
        </script>
		<label>开始时间:</label>
        <input value="${online:defVal(param.startTime,online:yesterdayString("yyyy/MM/dd"))}" type="text"
               name="startTime" class="input-small" id="startTime"/>
        <label>结束时间:</label>
        <input value="${online:defVal(param.endTime,online:yesterdayString("yyyy/MM/dd"))}" type="text" name="endTime"
               class="input-small" id="endTime"/>
        <input type="button" class="btn" value="查询" onclick="javascript:doQuery();"/>
    </form>
</div>
