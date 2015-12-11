<%@ include file="../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css"/>
<div id="search">
    <form id="search_form" action="${basePath}online/list" method="post"
          class="form-inline pull-right">
        <label>channel:</label>
        <select name="channel" id="channel_sel" class="input-small">
            <option value="">全部</option>
            <option value="all">所有</option>
            <c:forEach items="${users}" var="user">
                <c:if test="${not empty user.channel}">
                    <option value="${user.channel}">${user.channel}</option>
                </c:if>
            </c:forEach>
        </select>
        <script type="text/javascript">
            document.getElementById("channel_sel").value = '${online:defVal(param.channel,"")}';
        </script>
        <label>国家:</label>
        <select name="country" id="country_sel" class="input-small">
            <option value="">全部</option>
            <option value="all">所有</option>
            <c:forEach items="${countries}" var="c">
                <option value="${c.shortcut}">${c.firstLetterOfChineseName}-${c.chineseName}</option>
            </c:forEach>
        </select>
        <script type="text/javascript">
            document.getElementById("country_sel").value = '${online:defVal(param.country,"")}';
        </script>
        <label>开始时间:</label>
        <input value="${online:defVal(param.startTime,online:yesterdayString("yyyy/MM/dd"))}" type="text"
               name="startTime" class="input-small" id="startTime"/>
        <label>结束时间:</label>
        <input value="${online:defVal(param.datetime,online:yesterdayString("yyyy/MM/dd"))}" type="text" name="datetime" class="input-small" id="datetime"/>
        <input type="submit" class="btn" value="查询"/>
    </form>
</div>
