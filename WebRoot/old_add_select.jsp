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
<title>招聘试题定制服务系统_试题定制</title>
<link rel="stylesheet" href="<%= basePath %>/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%= basePath %>/css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%= basePath %>/css/index.css" type="text/css">
<link rel="stylesheet" href="<%= basePath %>/css/item.css" type="text/css">
</head>
<body>
	<div class="header">
		<a class="about">关于我们</a>
		<ul class="breadcrumb">
			<li>您好,</li>
			<li><a href="p_center.jsp">${user_login.user_name }</a>
			</li>
			<li><a href="login.jsp">&nbsp;[退出]</a>
			</li>
		</ul>

	</div>
	<div class="content">
		<div class="page-header">
			<h1>招聘试题定制服务系统-添加试题</h1>
		</div>
		<div class="page-header1"></div>
		<div class="main">
			<div class="main_left">
				<ul class="menu">
					<li><a href="p_center.jsp">个人中心</a></li>
					<li><a href="select_Major.do">试题定制</a></li>
					<li><a href="showcart.jsp">我的试题筐</a></li>
				</ul>
			</div>

			<div class="main_right">
				<div class="made">
					<span><a href="return.do">题目管理</a>
					</span> / <span>添加选择题</span>
				</div>
				<hr>
				<div class="paper_list">
					<form action="Add.do" class="paper_right" method="post">
						<table>
							<tr>
								<td class="td_right">职位/背景：</td>
								<td><select name="major">

										<c:forEach var="j" items="${major }">
											<option value=${j }>${j }</option>
										</c:forEach>                                                             
								</select> <select name="backgroud">
										<option value="0">应届毕业生</option>
										<option value="1">1~2年工作经验</option>
										<option value="2">2~4年工作经验</option>
										<option value="3">工作经验5年以上</option>
								</select></td>
							<!-- </tr>

							<tr> -->
								<td class="td_right">题目类型：</td>
								<td><select name="type" id="type">
										<option value="0">单选题</option>
										<option value="1">多选题</option>
										<option value="2">判断题</option>
										<option value="3">填空题</option>
										<!-- <option value="4">问答题</option>   -->
								</select>
								</td>
							</tr>

							<%--  <c:forEach var="j" items="${singleTitle }"> --%>
							<tr>
								<td class="td_right">题目：</td>
								<td><input type="text" name="text">
								</td>
							</tr>
							<tr>
								<td class="td_right">选项A：</td>
								<td><input type="text" name="A">
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="td_right">选项B：</td>
								<td><input type="text" name="B">
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="td_right">选项C：</td>
								<td><input type="text" name="C">
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="td_right">选项D：</td>
								<td><input type="text" name="D">
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="td_right">正解：</td>
								<td><input type="text" name="answer">
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="td_right">解释：</td>
								<td><input type="text" name="explain">
								</td>
							
								<td class="td_right" colspan="2"><input type="submit"
									value="生成试题" id="item_save" class="btn">
								</td>
							</tr>
						
						</table>
						<%-- </c:forEach> --%>
					</form>

				</div>
				<br>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="bt">@2016 招聘试题定制服务系统</div>
	</div>

</body>
</html>