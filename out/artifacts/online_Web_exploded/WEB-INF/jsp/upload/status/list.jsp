<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="静默日志查询" scope="request"/>
<c:set var="_underUploadStatus" value="active" scope="request"/>
<c:set var="_activeUploadStatus" value="active" scope="request"/>
<c:set var="_module" value="statistics" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<!-- main content -->
	<div class="page-header"><h1>静默日志查询</h1></div>
	<c:import url="search.jsp">
	</c:import>
		<div id="list">
			<table class="table table-bordered table-striped">
				<c:choose>
					<c:when test="${not hasDatas}">
						<tr>
							<td>没有数据!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th></th>
							<th>日期</th>
							<th>名称</th>
							<th>操作</th>
							<th>状态</th>
							<th>版本</th>
							<th>信息</th>
						</tr>
						<c:forEach var="data" items="${datas}">
							<tr>
								<td class="checkbox_td">
									<input type="checkbox" name="ids" value="${data.id}"/>
								</td>
								<td>${data.id}</td>
								<td>${data.date}</td>
								<td>${pkgMap[data.pkgName].appName==null?data.pkgName:pkgMap[data.pkgName].appName}</td>
								<td>${data.action}</td>
								<td>${data.state}</td>
								<td>${data.version}</td>
								<td>${data.msg}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="row-fluid">
            <div class="span4 toolbar">
				</div>
			<div class="span8 paginator">
				<c:import url="../../theme/${_theme}/paginator.jsp"></c:import>
			</div>
		</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp">
</c:import>
<script src="${basePath}static/jquery/jquery-ui.js"></script>
<script src="${basePath}static/jquery/jquery.ui.datepicker-zh-TW.js"></script>
<script>
$(function() {
    $( "#startTime" ).datepicker( $.datepicker.regional[ "zh-TW" ] );
    $( "#endTime" ).datepicker( $.datepicker.regional[ "zh-TW" ] );
});
function doQuery(){
	document.getElementById("search_form").action="${basePath}upload/status/list";
	document.getElementById("search_form").submit();
}
</script>