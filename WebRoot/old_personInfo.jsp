<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>完善个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="add_person_message.do" method="post">
    	姓名:<input type="text" name="person_name" value="${person_login.person_name}"><br>
    	邮箱地址:<input type="text" name="person_email" value="${person_login.person_email}"><br>
    	联系方式:<input type="text" name="person_phone" value="${person_login.person_phone }"><br>
    	年龄:<input type="text" name="person_age" value="${person_login.person_age }"><br>
    	性别:<select name="person_sex">
    			<c:if test="${person_login.person_sex == 0}">
    				<option value="1" selected="selected">男</option>
    				<option value="2">女</option>
    			</c:if>
    			<c:if test="${person_login.person_sex == 1}">
    				<option value="1" selected="selected">男</option>
    				<option value="2">女</option>
    			</c:if>
    			<c:if test="${person_login.person_sex == 2 }">
    				<option value="1">男</option>
    				<option value="2" selected="selected">女</option>
    			</c:if>
    			
    		</select><br>
    	教育经历:<textarea rows="5" cols="30" name="person_education">${person_login.person_education}</textarea><br>
    	工作经历:<textarea rows="5" cols="30" name="person_practice">${person_login.person_practice}</textarea><br>
    	项目经历:<textarea rows="5" cols="30" name="person_project">${person_login.person_project}</textarea><br>
    	<input type="submit" value="提交">
    </form>
  </body>
</html>
