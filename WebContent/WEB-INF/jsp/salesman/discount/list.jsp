<%@include file="../../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="AppInfo列表" scope="request"/>
<c:set var="_underSalesmandiscount" value="active" scope="request"/>
<c:set var="_activeSalesmandiscount" value="active" scope="request"/>
<c:set var="_module" value="salesman" scope="request"/>
<c:import url="../../theme/${_theme}/header.jsp"></c:import>
<script type="text/javascript" src="${basePath}static/theme/${_theme}/global.js"></script>
<!-- main content -->
		<div class="page-header"><h1>业务员扣量比例列表</h1></div>
        <br/><br/>
		<div id="list">
			<table class="table table-bordered table-striped">
				<c:choose>
					<c:when test="${not hasDatas}">
						<tr>
							<td>没有删除应用记录!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th></th>
							<th>编号</th>
							<th>渠道号</th>
                            <th>比例</th>
						</tr>
						<c:forEach var="data" items="${datas}" varStatus="it">
							<tr>
								<td class="checkbox_td">
									<input type="checkbox" name="ids" value="${data.id}"/>
								</td>
								<td>${fn:escapeXml(data.id)}</td>
								<td>${fn:escapeXml(data.channel)}</td>
								<td>${fn:escapeXml(data.discount)}</td>
				                </td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="row-fluid">
			<div class="span4 toolbar">
				<c:import url="../../theme/${_theme}/toolbar.jsp">
					<c:param name="create">${basePath}salesman/discount/create</c:param>
                    <c:param name="delete">${basePath}salesman/discount/delete</c:param>
					<c:param name="modify">${basePath}salesman/discount/modify</c:param>
				</c:import>
			</div>
			<div class="span8 paginator">
				<c:import url="../../theme/${_theme}/paginator.jsp"></c:import>
			</div>
		</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>