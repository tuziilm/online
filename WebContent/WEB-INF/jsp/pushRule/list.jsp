<%@include file="../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="push规则列表管理" scope="request"/>
<c:set var="_underPushRule" value="active" scope="request"/>
<c:set var="_activePushRule" value="active" scope="request"/>
<c:set var="_module" value="pushrule" scope="request"/>
<c:import url="../theme/${_theme}/header.jsp"></c:import>
<script type="text/javascript" src="${basePath}static/theme/${_theme}/global.js"></script>
<!-- main content -->
		<div class="page-header"><h1>push规则</h1></div>
        <br/><br/>
		<div id="list">
			<table class="table table-bordered table-striped">
				<c:choose>
					<c:when test="${not hasDatas}">
						<tr>
							<td>没有任务记录!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th></th>
							<th>编号</th>
							<th>apkNum</th>
                            <th>pushUrl</th>
							<th>deviceUrl</th>
                            <th>requestRate</th>
                            <th>size</th>
                            <th>ram</th>
							<th>操作</th>
						</tr>
						<c:forEach var="data" items="${datas}" varStatus="it">
							<tr>
								<td class="checkbox_td">
									<input type="checkbox" name="ids" value="${data.id}"/>
								</td>
								<td>${fn:escapeXml(data.id)}</td>
								<td>${fn:escapeXml(data.apkNum)}</td>
                                <td>${fn:escapeXml(data.pushUrl)}</td>
								<td>${fn:escapeXml(data.deviceUrl)}</td>
                                <td>${fn:escapeXml(data.requestRate)}</td>
                                <td>${fn:escapeXml(data.size)}</td>
                                <td>${fn:escapeXml(data.ram)}</td>
								<td class="operation operand1">
									<a class="btn btn-small btn-info" onclick="javascript:showDetail(${it.count},this);return false;"><i class="icon-plus-sign icon-white"></i>详情</a>
								</td>
							</tr>
							<tr id="detail_${it.count }" style="display: none">
								<td></td>
								<td colspan="11">
									<ul>
                                        <li><strong>channel：</strong>${fn:escapeXml(data.channel)}</li>
                                        <li><strong>isTablet：</strong>${fn:escapeXml(data.isTablet)}</li>
                                        <li><strong>systemVersion：</strong>${fn:escapeXml(data.systemVersion)}</li>
                                         <li><strong>sdkVersion：</strong>${fn:escapeXml(data.sdkVersion)}</li>
                                        <li><strong>model：</strong>${fn:escapeXml(data.model)}</li>
                                         <li><strong>resolution：</strong>${fn:escapeXml(data.resolution)}</li>
										<li><strong>rom：</strong>${fn:escapeXml(data.rom )}</li>
										<li><strong>hasSim：</strong>${fn:escapeXml(data.hasSim)}</li>
                                         <li><strong>isRoot：</strong>${fn:escapeXml(data.isRoot)}</li>
										<li><strong>hardware：</strong>${fn:escapeXml(data.hardware )}</li>
										<li>
                                            <strong>国家：</strong>
                                            <c:forEach var="country" items="${data.countriesObject}" varStatus="vs">
                                                <span><c:if test="${not vs.first}">,</c:if>${countryMap[country].chineseName}</span>
                                            </c:forEach>
                                        </li>
										<li><strong>isMatching：</strong>${fn:escapeXml(data.isMatching)}</li>
										<li><strong>Version：</strong>${fn:escapeXml(data.version)}</li>
										<li><strong>测试模式：</strong>${fn:escapeXml(data.testmodel)}</li>
									</ul>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="row-fluid">
			<div class="span4 toolbar">
				<c:import url="../theme/${_theme}/toolbar.jsp">
					<c:param name="create">${basePath}pushRule/create</c:param>
                    <c:param name="delete">${basePath}pushRule/delete</c:param>
					<c:param name="modify">${basePath}pushRule/modify</c:param>
				</c:import>
			</div>
			<div class="span8 paginator">
				<c:import url="../theme/${_theme}/paginator.jsp"></c:import>
			</div>
		</div>
<!-- end main content -->
<c:import url="../theme/${_theme}/footer.jsp"></c:import>