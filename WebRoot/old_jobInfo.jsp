<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jobInfo.jsp' starting page</title>
    
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
    <form action="reset_job.do" method="post">
    	岗位名称:<input type="text" name="job_name" value="${jobinfo.job_name }"><br>
    	岗位信息:<textarea rows="2" cols="20" name="job_message">${jobinfo.job_message }</textarea><br> 
    	岗位要求:<textarea rows="2" cols="20" name="job_request">${jobinfo.job_request }</textarea><br>
    	考试时间:<input type="datetime-local" name="dateTime"><br>
    	添加试卷: <select name="exam_id" >
    				<c:if test="${jobinfo.exam_id > 0 }">
    					<option value="${jobinfo.exam_id }">${jobinfo.exam_id}</option>
    				</c:if>
    				<option value="0">空</option>
    				<c:forEach var="j" items="${exams }">
						<option value=${j.exam_id }>${j.exam_name }</option>
					</c:forEach>
    			</select>
    	<a href="select_Major.do">定制试卷</a><br>
    	<input type="submit" value="确定">
    </form>
  </body>
</html>
