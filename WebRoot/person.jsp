<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>应聘人员简历查看</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
body{
    font-family: "Microsoft YaHei",微软雅黑;
    width:70%;
    margin: 10px auto;
    background: #fdfdfd;
   }
   div{margin:10px;}
</style>
  </head>
  
  <body>
   	<div>姓名:${person.person_name}</div> 
   	<div>年龄:${person.person_age }</div> 
   	<div>联系电话:${person.person_phone }</div> 
   	<div>邮箱地址:${person.person_email }</div> 
   	<div>教育经历:${person.person_education }</div> 
   	<div>工作经历:${person.person_practice }</div> 
   	<div>项目经历:${person.person_project }</div> 
  </body>
</html>
