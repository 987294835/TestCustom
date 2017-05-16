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

<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/findPassword.css">
<link href="css/upTop.css" rel="stylesheet"/>
<script src="js/modernizr.js"></script> <!-- Modernizr -->
<style>
body{
    width:80%;
    height:95%;
    margin: 15px auto;
    background: #f6f6f6;
    overflow-y: scroll;}
    h1{font-size:30px;} 
  a:hover{color:gray;}
  .padding-20{padding:20px 0;}
  td{padding:2px 0}
</style>
</head>
<body>
	<div class="paper">
	<div class="back"><span><a href=" " onClick="javascript:history.back(-1);">&nbsp;&nbsp;&nbsp;返回上一页 </a></span></div>
	<h1 class="paper_title" style="text-align:center">${exam.exam_name}</h1>
	<div class="paper_list">
		 <table>
                    <th style="line-height: 44px;font-size:28px;"> 题目详情 </th>
                    <tr><td>题目: ${title_infomation.title_text }</td></tr>
                    <c:if test="${title_infomation.title_type==0 or title_infomation.title_type==1}">
                    <tr>
                    
									<td>&nbsp;A、${title_infomation.title_option_a }</td>
								</tr>
								<tr>
									<td>&nbsp;B、${title_infomation.title_option_b }</td>
								</tr>
								<tr>
									<td>&nbsp;C、${title_infomation.title_option_c }</td>
								</tr>
								<tr>
									<td>&nbsp;D、${title_infomation.title_option_d }</td>
								</tr>
						</c:if>		
                    <tr><td class="padding-20">正确答案: ${title_infomation.title_answer }</td></tr> 
                    <tr><td>题目解析: ${title_infomation.title_explain }</td></tr>  
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