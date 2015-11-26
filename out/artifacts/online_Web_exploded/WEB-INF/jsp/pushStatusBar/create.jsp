<%@include file="../include/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="_pageTitle" value="创建push规则" scope="request" />
<c:set var="_underPushStatusBar" value="active" scope="request" />
<c:set var="_activePushStatusBar" value="active" scope="request" />
<c:set var="_module" value="pushStatusBar" scope="request" />
<c:import url="../theme/${_theme}/header.jsp"></c:import>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css" />
<!-- main content -->
<div class="page-header">
	<h1>创建/修改push状态栏规则</h1>
</div>
<div id="pageContent">
	<c:import url="../theme/${_theme}/errors.jsp"></c:import>
	<form action="${basePath}pushStatusBar/save" method="post"
		class="form-horizontal" enctype="multipart/form-data">
		<input name="id" type="hidden" value="${form.id}"> <input
			name="_queryString" type="hidden" value="${param.queryString}">

		<div class="control-group required-field">
			<label class="control-label">pushInterval:</label>
			<div class="controls">
				<input name="pushInterval"
					value="${fn:escapeXml(form.pushInterval)}" type="text"
					class="input-large"> <span class="remark">push的时间间隔，单位为分钟</span>
			</div>
		</div>
		
		<div class="control-group required-field">
			<label class="control-label">requestInterval:</label>
			<div class="controls">
				<input name="requestInterval"
					value="${fn:escapeXml(form.requestInterval)}" type="text"
					class="input-large"> <span class="remark">请求频率，单位为天</span>
			</div>
		</div>
		
		<div class="control-group required-field">
			<label class="control-label">activeTime:</label>
			<div class="controls">
				<input name="activeTime"
					value="${fn:escapeXml(form.activeTime)}" type="text"
					class="input-large"> <span class="remark">激活时间</span>
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">pushUrl:</label>
			<div class="controls">
				<input name="pushUrl" value="${fn:escapeXml(form.pushUrl)}"
					type="text" class="input-large"> <span class="remark">业务请求地址</span>
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">国家:</label>
			<div class="controls">
				<div class="control-search-bar">
					<input id="_country_kw" name="_country_kw" class="input-medium">
					<input class="btn" type="button"
						onclick="javascript:searchSelected('_country_kw','_countries');"
						value="搜索"> <input class="btn" type="button"
						onclick="javascript:showSelected('_countries');" value="选择项">
					<input type="checkbox"
						onchange="javascript:toggleAllSelected('_countries', this);">全选/全不选
				</div>
				<div id="_countries">
					<c:forEach var="country" items="${countries}">
						<span class="selLabel"><input
							${online:contains(form.countriesObject, country.shortcut)?"checked=\"checked\"":""}
							type="checkbox" name="countriesObject"
							value="${country.shortcut}"><span>${country.chineseName}</span></span>
					</c:forEach>
				</div>
			</div>
		</div>


		<div class="control-group required-field">
			<label class="control-label">size:</label>
			<div class="controls">
				<input name="size" value="${fn:escapeXml(form.size)}" type="text"
					class="input-large"> <span class="remark">屏幕大小</span>
			</div>
		</div>


		<div class="control-group required-field">
			<label class="control-label">ram:</label>
			<div class="controls">
				<input name="ram" value="${fn:escapeXml(form.ram)}" type="text"
					class="input-large"> <span class="remark">ram大小</span>
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">channel:</label>
			<div class="controls">
				<input name="channel" value="${fn:escapeXml(form.channel)}"
					type="text" class="input-large"> <span class="remark">渠道</span>
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">isTablet:</label>
			<div class="controls">
				<select id="sel_isTablet" name="isTablet" class="input-small">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				<script>
					document.getElementById("sel_isTablet").value = "${empty form.isTablet?'0':form.isTablet}"
				</script>
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">version:</label>
			<div class="controls">
				<input name="version" value="${fn:escapeXml(form.version)}"
					type="text" class="input-large"> <span class="remark">匹配版本号</span>
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">rom:</label>
			<div class="controls">
				<input name="rom" value="${fn:escapeXml(form.rom)}" type="text"
					class="input-large"> <span class="remark">rom大小</span>
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">isMatching:</label>
			<div class="controls">
				<select id="sel_isMatching" name="isMatching" class="input-small">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				<script>
					document.getElementById("sel_isMatching").value = "${empty form.isMatching?'0':form.isMatching}"
				</script>
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">是否为测试模式:</label>
			<div class="controls">
				<select id="sel_isTest" name="isTest" class="input-small">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				<script>
					document.getElementById("sel_isTest").value = "${empty form.isTest?'0':form.isTest}"
				</script>
			</div>
		</div>

		<div class="form-actions">
			<input class="btn btn-primary" type="submit" value="保存">
			<button type="button" class="btn" onclick="javascript:history.go(-1)">取消</button>
		</div>
	</form>
</div>
<!-- end main content -->
<c:import url="../theme/${_theme}/footer.jsp"></c:import>
<script src="${basePath}static/jquery/jquery-ui.js"></script>
<script src="${basePath}static/jquery/jquery.ui.datepicker-zh-TW.js"></script>
<script>
	function searchSelected(kwElemId, resultDivId) {
		var kw = $("#" + kwElemId).val();
		$("#" + resultDivId + " .selLabel").each(function() {
			if (($("span", this).html() + "").indexOf(kw) == -1) {
				$(this).hide();
			} else {
				$(this).show();
			}
		});
	}
	function showSelected(resultDivId) {
		$("#" + resultDivId + " .selLabel").each(function() {
			if ($("input[type=checkbox]", this)[0].checked) {
				$(this).show();
			} else {
				$(this).hide();
			}
		});
	}
	function toggleAllSelected(resultDivId, elem) {
		$("#" + resultDivId + " .selLabel").each(
				function() {
					if ($(this).is(":visible")) {
						$($("input[type=checkbox]", this)[0]).attr("checked",
								elem.checked);
					}
				});
	}
	showSelected("_countries");
</script>
