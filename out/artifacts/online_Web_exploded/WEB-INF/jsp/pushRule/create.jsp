<%@include file="../include/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="_pageTitle" value="创建push规则" scope="request"/>
<c:set var="_underPushRule" value="active" scope="request"/>
<c:set var="_activePushRule" value="active" scope="request"/>
<c:set var="_module" value="pushrule" scope="request"/>
<c:import url="../theme/${_theme}/header.jsp"></c:import>
<link rel="stylesheet" href="${basePath}static/jquery/jquery-ui.css" />
<!-- main content -->
		<div class="page-header"><h1>创建/修改push规则</h1></div>
		<div id="pageContent">
			<c:import url="../theme/${_theme}/errors.jsp"></c:import>
			<form action="${basePath}pushRule/save" method="post" class="form-horizontal" enctype="multipart/form-data">
				<input name="id" type="hidden" value="${form.id}">
				<input name="_queryString" type="hidden" value="${param.queryString}">
                <div class="control-group required-field">
                    <label class="control-label">apkNum:</label>
                    <div class="controls">
                        <input name="apkNum" value="${fn:escapeXml(form.apkNum)}" type="text" class="input-large">
                        <span class="remark">安装个数</span>
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">pushUrl:</label>
                    <div class="controls">
                        <input name="pushUrl" value="${fn:escapeXml(form.pushUrl)}" type="text" class="input-large">
                   	    <span class="remark">业务请求地址</span>	
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">feedbackUrl:</label>
                    <div class="controls">
                        <input name="feedbackUrl" value="${fn:escapeXml(form.feedbackUrl)}" type="text" class="input-large">
                   	    <span class="remark">反馈地址</span>	
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">deviceUrl:</label>
                    <div class="controls">
                        <input name="deviceUrl" value="${fn:escapeXml(form.deviceUrl)}" type="text" class="input-large">
                   	    <span class="remark">业务请求地址</span>	
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">requestRate:</label>
                    <div class="controls">
                        <input name="requestRate" value="${fn:escapeXml(form.requestRate)}" type="text" class="input-large">
                   	    <span class="remark">业务请求频率</span>	
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">Version:</label>
                    <div class="controls">
                        <input name="version" value="${fn:escapeXml(form.version)}" type="text" class="input-large">
                        <span class="remark">应用版本号</span>
                    </div>
                </div>
				<div class="control-group required-field">
				  <label class="control-label">size:</label>
				  <div class="controls">
                      <select id="sel_size" name="size" class="input-small">
                          <option value="7">7</option>
                          <option value="7.5">7.5</option>
                          <option value="8">8</option>
                          <option value="9.7">9.7</option>
                      </select>
                      <script>document.getElementById("sel_size").value="${empty form.size?'7':form.size}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">ram:</label>
				  <div class="controls">
                      <select id="sel_ram" name="ram" class="input-small">
                          <option value="256">256</option>
                          <option value="512">512</option>
                          <option value="1024">1024</option>
                          <option value="2048">2048</option>
                      </select>
                      <script>document.getElementById("sel_ram").value="${empty form.ram?'256':form.ram}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">channel:</label>
				  <div class="controls">
                      <select id="sel_channel" name="channel" class="input-small">
                          <option value="新版OTA">新版OTA</option>
                          <option value="普惠">普惠</option>
                          <option value="汇品">汇品</option>
                          <option value="CalendarService">CalendarService</option>
                      </select>
                      <script>document.getElementById("sel_channel").value="${empty form.channel?'新版OTA':form.channel}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">isTablet:</label>
				  <div class="controls">
                      <select id="sel_isTablet" name="isTablet" class="input-small">
                          <option value="0">否</option>
                          <option value="1">是</option>
                      </select>
                      <script>document.getElementById("sel_isTablet").value="${empty form.isTablet?'0':form.isTablet}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
                    <label class="control-label">国家:</label>
                    <div class="controls">
                        <div class="control-search-bar">
                            <input id="_country_kw" name="_country_kw" class="input-medium">
                            <input class="btn" type="button" onclick="javascript:searchSelected('_country_kw','_countries');" value="搜索">
                            <input class="btn" type="button" onclick="javascript:showSelected('_countries');" value="选择项">
                            <input type="checkbox" onchange="javascript:toggleAllSelected('_countries', this);">全选/全不选
                        </div>
                        <div id="_countries">
                            <c:forEach var="country" items="${countries}">
                                <span class="selLabel"><input ${online:contains(form.countriesObject, country.shortcut)?"checked=\"checked\"":""} type="checkbox" name="countriesObject" value="${country.shortcut}"><span>${country.chineseName}</span></span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">systemVersion:</label>
                    <div class="controls">
                        <input name="systemVersion" value="${fn:escapeXml(form.systemVersion)}" type="text" class="input-large">
                        <span class="remark">系统版本</span>
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">sdkVersion:</label>
                    <div class="controls">
                        <input name="sdkVersion" value="${fn:escapeXml(form.sdkVersion)}" type="text" class="input-large">
                        <span class="remark">sdk版本</span>
                    </div>
                </div>
                <div id="_cg_mtitle" class="control-group required-field">
                    <label class="control-label">model:</label>
                    <div class="controls">
                        <input id="_model" name="model" value="${fn:escapeXml(form.model)}" type="text" class="input-large">
                        <span class="remark">设置型号</span>
                    </div>
                </div>
                <div class="control-group required-field">
                    <label class="control-label">resolution:</label>
                    <div class="controls">
                        <input name="resolution" value="${fn:escapeXml(form.resolution)}" type="text" class="input-large">
                        <span class="remark">屏幕分辨率</span>
                    </div>
                </div>
				<div class="control-group required-field">
				  <label class="control-label">rom:</label>
				  <div class="controls">
				    <input name="rom" value="${fn:escapeXml(form.rom)}" type="text" class="input-large">
				    <span class="remark">rom大小</span>
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">hasSim:</label>
				  <div class="controls">
                      <select id="sel_hasSim" name="hasSim" class="input-small">
                          <option value="0">否</option>
                          <option value="1">是</option>
                      </select>
                      <script>document.getElementById("sel_hasSim").value="${empty form.hasSim?'0':form.hasSim}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">isRoot:</label>
				  <div class="controls">
                      <select id="sel_isRoot" name="isRoot" class="input-small">
                          <option value="0">否</option>
                          <option value="1">是</option>
                      </select>
                      <script>document.getElementById("sel_isRoot").value="${empty form.isRoot?'0':form.isRoot}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
                    <label class="control-label">hardware:</label>
                    <div class="controls">
                        <input name="hardware" value="${fn:escapeXml(form.hardware)}" type="text" class="input-large">
                        <span class="remark">包含wmt也直接下发status=true，不下发业务信息</span>
                    </div>
                </div>
				<div class="control-group required-field">
				  <label class="control-label">isMatching:</label>
				  <div class="controls">
                      <select id="sel_isMatching" name="isMatching" class="input-small">
                          <option value="0">否</option>
                          <option value="1">是</option>
                      </select>
                      <script>document.getElementById("sel_isMatching").value="${empty form.isMatching?'0':form.isMatching}"</script>
				  </div>
				</div>
				<div class="control-group required-field">
				  <label class="control-label">是否为测试模式:</label>
				  <div class="controls">
                      <select id="sel_testmodel" name="testmodel" class="input-small">
                          <option value="0">否</option>
                          <option value="1">是</option>
                      </select>
                      <script>document.getElementById("sel_testmodel").value="${empty form.testmodel?'0':form.testmodel}"</script>
				  </div>
				</div>
				
				
				<div class="control-group required-field">
                    <label class="control-label">maxInstallCount:</label>
                    <div class="controls">
                        <input name="maxInstallCount" value="${fn:escapeXml(form.maxInstallCount)}" type="text" class="input-large">
                        <span class="remark">最大安装失败次数(一个apk安装失败多少次就不再安装了)</span>
                    </div>
                </div>
                
                <div class="control-group required-field">
                    <label class="control-label">deleteEkenRate:</label>
                    <div class="controls">
                        <input name="deleteEkenRate" value="${fn:escapeXml(form.deleteEkenRate)}" type="text" class="input-large">
                        <span class="remark">删除eken的概率</span>
                    </div>
                </div>
                
                <div class="control-group required-field">
				  <label class="control-label">是否需要获得邮箱:</label>
				  <div class="controls">
                      <select id="sel_getEmail" name="getEmail" class="input-small">
                          <option value="0">否</option>
                          <option value="1">是</option>
                      </select>
                      <script>document.getElementById("sel_getEmail").value="${empty form.getEmail?'0':form.getEmail}"</script>
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
function searchSelected(kwElemId, resultDivId){
    var kw = $("#"+kwElemId).val();
    $("#"+resultDivId+" .selLabel").each(function(){
        if(($("span",this).html()+"").indexOf(kw)==-1){
            $(this).hide();
        }else{
            $(this).show();
        }
    });
}
function showSelected(resultDivId){
    $("#"+resultDivId+" .selLabel").each(function(){
        if($("input[type=checkbox]",this)[0].checked){
            $(this).show();
        }else{
            $(this).hide();
        }
    });
}
function toggleAllSelected(resultDivId, elem){
    $("#"+resultDivId+" .selLabel").each(function(){
        if($(this).is(":visible")){
            $($("input[type=checkbox]",this)[0]).attr("checked",elem.checked);
        }
    });
}
showSelected("_countries");
</script>
