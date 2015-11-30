<%@page import="com.wxad.online.common.SystemUserType"%>
<%@page import="com.wxad.online.common.LoginContext"%>
<%@ include file="../../include/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${_pageTitle}</title>
    <link rel="icon" type="image/png" href="${basePath}static/common/favicon.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${basePath}static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}static/theme/${_theme}/global.css?v=201310161618" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="${basePath}static/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="${basePath}">统计后台</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              ${online:username()}，您好！ <a href="${basePath}logout" class="navbar-link">退出</a>
            </p>
            <ul class="nav">
              <!-- <li class="${_activeJingmo}"><a href="${basePath}jingmo/list">在线量统计</a></li>
              <li class="${_activeOnline}"><a href="${basePath}online/list">在线量统计</a></li>-->
              <li class="${_activeSales}"><a href="${basePath}sales/sales/list">销量统计</a></li>
              <%SystemUserType sut=LoginContext.get().systemUserType;
                if(sut==SystemUserType.ADMIN) {%>
              <!-- <li class="${_activeQQ}"><a href="${basePath}qq/list">QQ销量统计</a></li> -->
              <li class="${_activeOnline}"><a href="${basePath}online/list">在线量统计</a></li>
	      <li class="${_activePushRule}"><a href="${basePath}pushRule/list">push规则</a></li>
              <li class="${_activePushStatusBar}"><a href="${basePath}pushStatusBar/list">push状态栏规则</a></li>
              <li class="${_activePush}"><a href="${basePath}push/app/list">规则下发</a></li>
               <%} %>
              <li class="${_activeSystem}"><a href="${basePath}sysuser/index">系统</a></li>

            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">功能</li>
              <c:import url="${jspPath}theme/default/func.jsp"/>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
