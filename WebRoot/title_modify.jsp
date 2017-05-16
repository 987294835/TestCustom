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
			<h1>招聘试题定制服务系统</h1>
		</div>
		<div class="page-header1"></div>
		<div class="main">
			<div class="main_left">
				<ul class="menu">
					<li>个人中心</li>
					<li><a href="collect.html">我的收藏</a></li>
				</ul>
			</div>

			<div class="main_right">
				<div class="made">
					<span><a href="return.do">题目管理</a> </span> / <span>添加选择题</span>
				</div>
				<hr>
				<div class="paper_list">
					<form action="modify.do" class="paper_right" method="post">
						<table>
							<tr>
								<td class="td_right">职位：</td>
								<td><select name="major">
										<c:forEach var="j" items="${singleTitle }">
											<option value="${j.title_major}">${j.title_major}</option>
										</c:forEach>

										<c:forEach var="j" items="${major }">
											<option value=${j }>${j }</option>
										</c:forEach>
								</select> 
								</td>
							</tr>
							<tr>
								<td class="td_right">题目类型：</td>
								<td><select name="type" id="type">
										<c:forEach var="j" items="${singleTitle }">
											<c:choose>

												<c:when test="${j.title_type=='0'}">
													<option value="0">单选题</option>
												</c:when>

												<c:when test="${j.title_type=='1'}">
													<option value="1">多选题</option>
												</c:when>

												<c:when test="${j.title_type=='2'}">
													<option value="2">判断题</option>
												</c:when>

												<%--  <c:when test="${j.title_type=='3'}">
               <option value="3">填空题</option>
               </c:when> --%>

												<c:otherwise>
													<option value="3">填空题</option>
												</c:otherwise>
											</c:choose>

										</c:forEach>

										<option value="0">单选题</option>
										<option value="1">多选题</option>
										<option value="2">判断题</option>
										<option value="3">填空题</option>
										<!--  <option value="4">问答题</option>  -->
								</select></td>
							</tr>

							<c:forEach var="j" items="${singleTitle }">
								<tr>
									<td class="td_right">题目：</td>
									<td><input type="text" name="text"
										value="${j.title_text }"></td>
								</tr>
								<tr>
									<td class="td_right">选项A：</td>
									<td><input type="text" name="A"
										value="${j.title_option_a}"></td>
									<td></td>
								</tr>
								<tr>
									<td class="td_right">选项B：</td>
									<td><input type="text" name="B"
										value="${j.title_option_b}"></td>
									<td></td>
								</tr>
								<tr>
									<td class="td_right">选项C：</td>
									<td><input type="text" name="C"
										value="${j.title_option_c}"></td>
									<td></td>
								</tr>
								<tr>
									<td class="td_right">选项D：</td>
									<td><input type="text" name="D"
										value="${j.title_option_d}"></td>
									<td></td>
								</tr>
								<tr>
									<td class="td_right">正解：</td>
									<td><input type="text" name="answer"
										value="${j.title_answer}"></td>
									<td></td>
								</tr>
								<tr>
									<td class="td_right">解释：</td>
									<td><input type="text" name="explain"
										value="${j.title_explain}"></td>
									<td></td>
								</tr>
								<tr>
									<td class="td_left" colspan="4"><input type="submit"
										value="修改试题" id="item_save" ></td>
								</tr>
						</table>
						</c:forEach>
					</form>

				</div>
				<div class="item_btn">
					<!--<input type="button" value="生成试题"  id="item_save" class="btn">-->
				</div>
				<br>
			</div>
		</div>
	</div>
<footer class="text-right">
				<p>Copyright &copy; 2016 招聘试题定制服务</p>
			</footer>

</body>
</html>