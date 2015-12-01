<%@include file="../../include/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="_pageTitle" value="创建appInfo" scope="request" />
<c:set var="_underPushApp" value="active" scope="request" />
<c:set var="_activePush" value="active" scope="request" />
<c:set var="_module" value="push" scope="request" />
<c:import url="../../theme/${_theme}/header.jsp"></c:import>

<!-- main content -->
<div class="page-header">
	<h1>创建/修改AppInfo</h1>
</div>
<div id="pageContent">
	<c:import url="../../theme/${_theme}/errors.jsp"></c:import>
	<form action="${basePath}push/app/save" method="post"
		class="form-horizontal" enctype="multipart/form-data">
		<input name="id" type="hidden" value="${form.id}"> <input
			name="_queryString" type="hidden" value="${param.queryString}">
		<div class="control-group required-field">
			<label class="control-label">packageName:</label>
			<div class="controls">
				<input name="packageName" value="${fn:escapeXml(form.packageName)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">name:</label>
			<div class="controls">
				<input name="name" value="${fn:escapeXml(form.name)}" type="text"
					   class="input-large">
			</div>
		</div>
		<!-- <div class="control-group required-field">
			<label class="control-label">url:</label>
			<div class="controls">
				<input name="url" type="file"/>
			</div>
		</div> -->
		<div class="control-group required-field">
			<label class="control-label">url:</label>
			<div class="controls">
				<input name="url" value="${fn:escapeXml(form.url)}" type="text"
					class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">hash:</label>
			<div class="controls">
				<input name="hash" value="${fn:escapeXml(form.hash)}" type="text"
					class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">type:</label>
			<div class="controls">
				<input name="type" value="${fn:escapeXml(form.type)}" type="text"
					class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">version:</label>
			<div class="controls">
				<input name="version" value="${fn:escapeXml(form.version)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">activity:</label>
			<div class="controls">
				<input name="activity" value="${fn:escapeXml(form.activity)}"
					type="text" class="input-large">
			</div>
		</div>
		<div class="control-group required-field">
			<label class="control-label">pushId:</label>
			<div class="controls">
				<select id="push_sel" name="pushId" class="input-large" onchange="javaScript:changeInfo(this.selectedIndex)"> 
					<c:forEach var="push" items="${pushRuleList}">
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
					<li><span>安裝个数：</span><span id="_pr_apkNum">${pushRuleMap[form.pushId].apkNum}</span></li>
					<!--<li><span>pushUrl：</span><span id="_pr_pushUrl">${pushRuleMap[form.pushId].pushUrl}</span></li>
					<li><span>deviceUrl：</span><span id="_pr_deviceUrl">${pushRuleMap[form.pushId].deviceUrl}</span></li>
					<li><span>requestRate：</span><span id="_pr_requestRate">${pushRuleMap[form.pushId].requestRate}</span></li>-->
					<li><span>国家：</span><span id="_pr_country">${pushRuleMap[form.pushId].country}</span></li>
					<li><span>屏幕大小：</span><span id="_pr_size">${pushRuleMap[form.pushId].size}</span></li>
					<li><span>ram大小：</span><span id="_pr_ram">${pushRuleMap[form.pushId].ram}</span></li>
					<li><span>渠道号：</span><span id="_pr_channel">${pushRuleMap[form.pushId].channel}</span></li>
					<li><span>是否是平板：</span><span id="_pr_isTablet">${pushRuleMap[form.pushId].isTablet}</span></li>
					<li><span>系统版本：</span><span id="_pr_systemVersion">${pushRuleMap[form.pushId].systemVersion}</span></li>
					<li><span>SDK版本：</span><span id="_pr_sdkVersion">${pushRuleMap[form.pushId].sdkVersion}</span></li>
					<li><span>设置型号：</span><span id="_pr_model">${pushRuleMap[form.pushId].model}</span></li>
					<li><span>屏幕分辨率：</span><span id="_pr_resolution">${pushRuleMap[form.pushId].resolution}</span></li>
					<li><span>ram大小：</span><span id="_pr_rom">${pushRuleMap[form.pushId].rom}</span></li>
					<li><span>是否有SIM卡：</span><span id="_pr_hasSim">${pushRuleMap[form.pushId].hasSim}</span></li>
					<li><span>是否root：</span><span id="_pr_isRoot">${pushRuleMap[form.pushId].isRoot}</span></li>
					<li><span>hardware：</span><span id="_pr_hardware">${pushRuleMap[form.pushId].hardware}</span></li>
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
<c:forEach var="pageRule" items="${pushRuleList}" varStatus="vs">
pageRules.push(${pageRule.jsonString});
</c:forEach>
	function changeInfo(idx){
		var pr = pageRules[idx];
		reset();
		$("#_pr_apkNum").html(pr.apkNum);
		//$("#_pr_pushUrl").html(pr.pushUrl);
		//$("#_pr_deviceUrl").html(pr.deviceUrl);
		//$("#_pr_requestRate").html(pr.requestRate);
		$("#_pr_country").html(pr.country);
		$("#_pr_size").html(pr.size);
		$("#_pr_ram").html(pr.ram);
		$("#_pr_channel").html(pr.channel);
		$("#_pr_isTablet").html(pr.isTablet);
		$("#_pr_systemVersion").html(pr.systemVersion);
		$("#_pr_sdkVersion").html(pr.sdkVersion);
		$("#_pr_model").html(pr.model);
		$("#_pr_resolution").html(pr.resolution);
		$("#_pr_rom").html(pr.rom);
		$("#_pr_hasSim").html(pr.hasSim);
		$("#_pr_isRoot").html(pr.isRoot);
		$("#_pr_hardware").html(pr.hardware);
		$("#_pr_isMatching").html(pr.isMatching);
	}
	function reset(){
		$("#_pr_apkNum").html("");
		//$("#_pr_pushUrl").html("");
		//$("#_pr_deviceUrl").html("");
		//$("#_pr_requestRate").html("");
		$("#_pr_country").html("");
		$("#_pr_size").html("");
		$("#_pr_ram").html("");
		$("#_pr_channel").html("");
		$("#_pr_isTablet").html("");
		$("#_pr_systemVersion").html("");
		$("#_pr_sdkVersion").html("");
		$("#_pr_model").html("");
		$("#_pr_resolution").html("");
		$("#_pr_rom").html("");
		$("#_pr_hasSim").html("");
		$("#_pr_isRoot").html("");
		$("#_pr_hardware").html("");
		$("#_pr_isMatching").html("");
	}
	$.ready(changeInfo(0));
</script>