<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>招聘试题定制服务系统</title>
    <link rel="stylesheet" href="<%= basePath %>css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%= basePath %>css/default.css" type="text/css">
    <link rel="stylesheet" href="<%= basePath %>css/index.css" type="text/css">
    <style>
      a:hover{font-weight:bold;}
    </style>
</head>
<body>
      <div class="header">
          <a class="about">关于我们</a>
          <ul class="breadcrumb">
              <li>您好,</li>
              <li><a href="p_center.jsp">${user_login.user_name }</a></li>
              <li><a href="login.jsp">&nbsp;[退出]</a></li>
          </ul>

      </div>
      <div class="content">
          <div class="page-header">
              <h1>招聘试题定制服务系统</h1>
          </div>
          <div class="page-header1">
          </div>
          <div class="main">
              <div class="main_left">
                  <ul class="menu">
                  <li><a href="p_center.jsp">个人中心</a></li>
                  <li><a href="Show_All.do">试题管理</a></li>
                  <li><a href="select_Major.do">试题定制</a></li>
                  <li><a href="showcart.jsp">试题筐</a></li>
                  <li><a href="Select_exam.do">历史记录</a></li>
              </ul></div>

              <div class="main_right">
                  <h1>关于我们</h1>
                  <p>.</p>
                  <br>
                
              </div>
          </div>
      </div>
      <div class="footer">
          <div class="bt">@2016 招聘试题定制服务系统 </div>
          </div>

</body>
</html>