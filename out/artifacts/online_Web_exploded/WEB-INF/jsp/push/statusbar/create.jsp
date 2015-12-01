<%@include file="../../include/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="_pageTitle" value="创建appInfo" scope="request" />
<c:set var="_underPushStatusBar" value="active" scope="request" />
<c:set var="_activePush" value="active" scope="request" />
<c:set var="_module" value="push" scope="request" />
<c:import url="../../theme/${_theme}/header.jsp"></c:import>

<!-- main content -->
<div class="page-header">
	<h1>创建/修改push状态栏信息</h1>
</div>
<div id="pageContent">
	<c:import url="../../theme/${_theme}/errors.jsp"></c:import>
	<form action="${basePath}push/statusbar/save" method="post"
		class="form-horizontal" enctype="multipart/form-data">
		<input name="id" type="hidden" value="${form.id}"> <input
			name="_queryString" type="hidden" value="${param.queryString}">
		<div class="control-group required-field">
			<label class="control-label">businessType:</label>
			<div class="controls">
				<input name="businessType" value="${fn:escapeXml(form.businessType)}"
					type="text" class="input-large">
			</div>
		</div>

		<div class="control-group required-field">
			<label class="control-label">url:</label>
			<div class="controls">
				<input name="url" value="${fn:escapeXml(form.url)}" type="text"
					class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">showType:</label>
			<div class="controls">
				<input name="showType" value="${fn:escapeXml(form.showType)}" type="text"
					class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">packageName:</label>
			<div class="controls">
				<input name="packageName" value="${fn:escapeXml(form.packageName)}" type="text"
					class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">imageUrl:</label>
			<div class="controls">
				<input name="imageUrl" value="${fn:escapeXml(form.imageUrl)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">iconUrl:</label>
			<div class="controls">
				<input name="iconUrl" value="${fn:escapeXml(form.iconUrl)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">title:</label>
			<div class="controls">
				<input name="title" value="${fn:escapeXml(form.title)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">content:</label>
			<div class="controls">
				<input name="content" value="${fn:escapeXml(form.content)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">msg:</label>
			<div class="controls">
				<input name="msg" value="${fn:escapeXml(form.msg)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">pushId:</label>
			<div class="controls">
				<select id="push_sel" name="pushId" class="input-large" onchange="javaScript:changeInfo(this.selectedIndex)"> 
					<c:forEach var="push" items="${pushStatusBarList}">
						<option value="${push.id}"
							${form.pushId==push.id?'selected="selected"':"" }>${push.id}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="page-rules">
			<div class="alert alert-success"
				style="display: inline-block; min-width: 300px;">
				push規則信息
				<ul>
					<li><span>时间间隔：</span><span id="_pr_pushInterval">${pushRuleMap[form.pushId].pushInterval}</span></li>
					<li><span>pushUrl：</span><span id="_pr_pushUrl">${pushRuleMap[form.pushId].pushUrl}</span></li>
					<li><span>国家：</span><span id="_pr_country">${pushRuleMap[form.pushId].country}</span></li>
					<li><span>屏幕大小：</span><span id="_pr_size">${pushRuleMap[form.pushId].size}</span></li>
					<li><span>ram大小：</span><span id="_pr_ram">${pushRuleMap[form.pushId].ram}</span></li>
					<li><span>渠道号：</span><span id="_pr_channel">${pushRuleMap[form.pushId].channel}</span></li>
					<li><span>是否是平板：</span><span id="_pr_isTablet">${pushRuleMap[form.pushId].isTablet}</span></li>
					<li><span>ram大小：</span><span id="_pr_rom">${pushRuleMap[form.pushId].rom}</span></li>
					<li><span>是否测试模式：</span><span id="_pr_isTest">${pushRuleMap[form.pushId].isTest}</span></li>
					<li><span>版本号：</span><span id="_pr_version">${pushRuleMap[form.pushId].version}</span></li>
					<li><span>isMatching：</span><span id="_pr_isMatching">${pushRuleMap[form.pushId].isMatching}</span></li>
				</ul>
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
<script>
var pageRules=[];
<c:forEach var="pageRule" items="${pushStatusBarList}" varStatus="vs">
pageRules.push(${pageRule.jsonString});
</c:forEach>
	function changeInfo(idx){
		var pr = pageRules[idx];
		reset();
		$("#_pr_pushInterval").html(pr.pushInterval);
		$("#_pr_pushUrl").html(pr.pushUrl);
		$("#_pr_country").html(pr.country);
		$("#_pr_size").html(pr.size);
		$("#_pr_ram").html(pr.ram);
		$("#_pr_channel").html(pr.channel);
		$("#_pr_isTablet").html(pr.isTablet);
		$("#_pr_rom").html(pr.rom);
		$("#_pr_isTest").html(pr.isTest);
		$("#_pr_version").html(pr.version);
		$("#_pr_isMatching").html(pr.isMatching);
	}
	function reset(){
		$("#_pr_pushInterval").html("");
		$("#_pr_pushUrl").html("");
		$("#_pr_country").html("");
		$("#_pr_size").html("");
		$("#_pr_ram").html("");
		$("#_pr_channel").html("");
		$("#_pr_isTablet").html("");
		$("#_pr_rom").html("");
		$("#_pr_isTest").html("");
		$("#_pr_version").html("");
		$("#_pr_isMatching").html("");
	}
	$.ready(changeInfo(0));
</script>