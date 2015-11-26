<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="浏览器首页列表" scope="request"/>
<c:set var="_underPushHomepage" value="active" scope="request"/>
<c:set var="_activePush" value="active" scope="request"/>
<c:set var="_module" value="push" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<script type="text/javascript" src="${basePath}static/theme/${_theme}/global.js"></script>
<!-- main content -->
		<div class="page-header"><h1>浏览器首页列表</h1></div>
        <br/><br/>
		<div id="list">
			<table class="table table-bordered table-striped">
				<c:choose>
					<c:when test="${not hasDatas}">
						<tr>
							<td>没有设置浏览器首页!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th></th>
							<th>编号</th>
                            <th>url</th>
                            <th>version</th>
                            <th>pushId</th>
						</tr>
						<c:forEach var="data" items="${datas}" varStatus="it">
							<tr>
								<td class="checkbox_td">
									<input type="checkbox" name="ids" value="${data.id}"/>
								</td>
								<td>${fn:escapeXml(data.id)}</td>
                                <td>${fn:escapeXml(data.url)}</td>
                                <td>${fn:escapeXml(data.version)}</td>
                                <td>${fn:escapeXml(data.pushId)}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="row-fluid">
			<div class="span4 toolbar">
				<c:import url="../../theme/${_theme}/toolbar.jsp">
					<c:param name="create">${basePath}push/homepage/create</c:param>
                    <c:param name="delete">${basePath}push/homepage/delete</c:param>
					<c:param name="modify">${basePath}push/homepage/modify</c:param>
				</c:import>
			</div>
			<div class="span8 paginator">
				<c:import url="../../theme/${_theme}/paginator.jsp"></c:import>
			</div>
		</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>