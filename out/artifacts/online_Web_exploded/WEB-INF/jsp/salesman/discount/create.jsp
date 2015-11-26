<%@page import="com.wxad.online.common.SystemUserType"%>
<%@page import="com.wxad.online.common.LoginContext"%>
<%@include file="../../include/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="_pageTitle" value="创建业务员扣量比例信息" scope="request" />
<c:set var="_underSalesmandiscount" value="active"
	scope="request" />
<c:set var="_activeSalesmandiscount" value="active"
	scope="request" />
<c:set var="_module" value="salesman" scope="request" />
<c:import url="../../theme/${_theme}/header.jsp"></c:import>

<!-- main content -->
<div class="page-header">
	<h1>创建/修改扣量信息</h1>
</div>
<div id="pageContent">
	<c:import url="../../theme/${_theme}/errors.jsp"></c:import>
	<form action="${basePath}salesman/discount/save"
		method="post" class="form-horizontal" enctype="multipart/form-data">
		<input name="id" type="hidden" value="${form.id}"> <input
			name="_queryString" type="hidden" value="${param.queryString}">
		<div class="control-group required-field">
			<label class="control-label">业务员:</label>
			<div class="controls">
				<input name="salesman" value="${fn:escapeXml(form.salesman)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">渠道号:</label>
			<div class="controls">
				<input name="channel" value="${fn:escapeXml(form.channel)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">比例:</label>
			<div class="controls">
				<input name="discount" value="${fn:escapeXml(form.discount)}"
					type="text" class="input-large">
			</div>
		</div>
		
		<div class="form-actions">
			<input class="btn btn-primary" type="submit" value="保存">
			<button type="button" class="btn" onclick="javascript:history.go(-1)">取消</button>
		</div>
	</form>
</div>
<!-- end main content -->
<c:import url="../../theme/${_theme}/footer.jsp"></c:import>