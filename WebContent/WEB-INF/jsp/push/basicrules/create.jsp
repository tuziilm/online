<%@include file="../../include/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="_pageTitle" value="创建基本设置首页" scope="request" />
<c:set var="_underPushBasicRules" value="active" scope="request" />
<c:set var="_activePush" value="active" scope="request" />
<c:set var="_module" value="push" scope="request" />
<c:import url="../../theme/${_theme}/header.jsp"></c:import>

<!-- main content -->
<div class="page-header">
	<h1>创建/修改基本规则首页</h1>
</div>
<div id="pageContent">
	<c:import url="../../theme/${_theme}/errors.jsp"></c:import>
	<form action="${basePath}push/basicrules/save" method="post"
		class="form-horizontal"  enctype="multipart/form-data">
		<input name="id" type="hidden" value="${form.id}"> <input
			name="_queryString" type="hidden" value="${param.queryString}">
			<div class="control-group required-field">
			<label class="control-label">渠道号:</label>
			<div class="controls">
				<input name="channel" value="${fn:escapeXml(form.channel)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">基准值:</label>
			<div class="controls">
				<input name="benchmark" value="${fn:escapeXml(form.benchmark)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">比例:</label>
			<div class="controls">
				<input name="proportion" value="${fn:escapeXml(form.proportion)}"
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
