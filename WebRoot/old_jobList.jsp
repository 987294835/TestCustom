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
    
    <title>岗位信息</title>
    
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
    <form action="" method="post">
    	<c:if test="${have_job == 0 }">
    		<c:forEach var="c" items="${publish_jobs}">
    			<span>${c.job_name }</span><br>
    			岗位介绍:<span>${c.job_message }</span><br>
    			岗位要求:<span>${c.job_request }</span><br>
    			<a href="add_job_to_person.do?job_id=${c.job_id }">点击申请</a><br>
    		</c:forEach>
    	</c:if>		
    	<c:if test="${have_job == 1 }">
    		<span>您已申请完岗位，请等候通知!!!</span>
    	</c:if>
    </form>
  </body>
</html>
