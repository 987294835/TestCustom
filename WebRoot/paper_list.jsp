<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>招聘试题定制服务--试卷</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/findPassword.css">
<link href="css/upTop.css" rel="stylesheet"/>
<script src="js/modernizr.js"></script> <!-- Modernizr -->
<style>
body{
    width:80%;
    margin: 15px auto;
    background: #f6f6f6;
    overflow-y: scroll;}
    h1{font-size:30px;} 
  a:hover{color:gray;}

</style>
</head>
<body>
	<div class="paper">
	<div class="back"><span><a href=" " onClick="javascript:history.back(-1);">&nbsp;&nbsp;&nbsp;返回上一页 </a></span><hr/></div>
	<h1 class="paper_title" style="text-align:center">${exam.exam_name}</h1>
	<div class="paper_list">
		<table>
			<c:forEach var="c" items="${exam_titles }">
				<c:if test="${c.title_type ==0 }">
					<tr >
						<td style="padding-top:20px;" >(选择题${c.title_id }) ${c.title_text }</td>
					</tr>
					<tr>
						<td>A、${c.title_option_a }</td>
					</tr>
					<tr>
						<td>B、${c.title_option_b }</td>
					</tr>
					<tr>
						<td>C、${c.title_option_c }</td>
					</tr>
					<tr>
						<td>D、${c.title_option_d }</td>
					</tr>
				</c:if>
			</c:forEach>
			<c:forEach var="c" items="${exam_titles }">
			<c:if test="${c.title_type ==1 }">
				<tr>
					<td style="padding-top:20px;">(多选题${c.title_id }) ${c.title_text } 
					</td>
				</tr>
				<tr>
					<td>A、${c.title_option_a }</td>
				</tr>
				<tr>
					<td>B、${c.title_option_b }</td>
				</tr>
				<tr>
					<td>C、${c.title_option_c }</td>
				</tr>
				<tr>
					<td>D、${c.title_option_d }</td>
				</tr>
			</c:if>
			</c:forEach>
			<c:forEach var="c" items="${exam_titles }">
			<c:if test="${c.title_type ==2 }">
				<tr>
					<td style="padding-top:20px;">(判断题${c.title_id }) ${c.title_text } 
					</td>
				</tr>
			</c:if>
			</c:forEach>
			<c:forEach var="c" items="${exam_titles }">
			<c:if test="${c.title_type ==3 }">
				<tr>
					<td style="padding-top:20px;">(填空题${c.title_id }) ${c.title_text } 
					</td>
				</tr>
			</c:if>
			</c:forEach>

		</table>
</div>
	</div>
	<a href="#0" class="cd-top" style=" color: #e86256;
  text-decoration: none;">Top</a>
  <!--Js-->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script src="js/upTop.js"></script> <!-- Gem jQuery -->
</body>
</html>