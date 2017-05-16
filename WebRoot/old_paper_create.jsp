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
<title>招聘试题定制服务系统_试题生成</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/index.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/item.css"
	type="text/css">
</head>
<body>
	<div class="header">
		  <a class="about" href="about_Us.jsp">关于我们</a>
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
		<div class="page-header1"></div>
		<div class="main">
			<div class="main_left">
				<ul class="menu">
					<li><a href="p_center.jsp">个人中心</a></li>
					<li><a href="showcart.jsp">我的试题筐</a></li>
					<li><a href="Select_Collect.do">我的收藏</a></li>
				</ul>
			</div>

			<div class="main_right">
				<div class="create">
					<span><a href="other_Choice.do">其他方案</a> </span>&nbsp;&nbsp; <span><a
						href="paper_made.jsp">重新定制</a> </span> <span class="collect_right"><a
						href="home.jsp">返回首页</a> </span>

				</div>
				<hr>
				<form action="add_title_to_cart.do" method="post" id="myForm">
					<div class="paper_create_list">

						<table>
							<th class="paper_title">推荐方案</th>

							<!-- <tr><td class="paper_type">简答题</td><tr> -->
							<!-- <tr><td><input type="radio" id="j1">1、什么是SSH?</td></tr> -->
							<c:forEach var="c" items="${customTitles_choice }">
								<tr>
									<td><input type="checkbox" value="${c.title_id }" name="choice_ids"> 
										<a href="Collect.do?choice_id=${c.title_id }">收藏</a>
										(选择题${c.title_id })
										${c.title_text }
										<a href="see_information.do?title_id=${c.title_id }">查看</a></td>
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
							</c:forEach>
							
							<c:forEach var="c" items="${customTitles_mutilchoice }">
								<tr>
									<td><input type="checkbox" value="${c.title_id }" name="choice_ids">
										<a href="Collect.do?choice_id=${c.title_id }">收藏</a>
										(多选题${c.title_id })
										${c.title_text }
										<a href="see_information.do?title_id=${c.title_id }">查看</a></td>
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
							</c:forEach>
							
							<c:forEach var="c" items="${customTitles_blank }">
								<tr>
									<td><input type="checkbox" value="${c.title_id }" name="choice_ids">
										<a href="Collect.do?choice_id=${c.title_id }">收藏</a>
										(判断题${c.title_id })
										${c.title_text }
										<a href="see_information.do?title_id=${c.title_id }">查看</a></td>
								</tr>
							</c:forEach>
							
							<c:forEach var="c" items="${customTitles_quiz }">
								<tr>
									<td><input type="checkbox" value="${c.title_id }" name="choice_ids">
										<a href="Collect.do?choice_id=${c.title_id }">收藏</a>
										(填空题${c.title_id })
										${c.title_text }
										<a href="see_information.do?title_id=${c.title_id }">查看</a></td>
								</tr>
							</c:forEach>
						</table>

					</div>
					<div class="paper_item_btn">
						<!-- <input type="button" value="添加到我的收藏" id="item_prevent" class="btn">&nbsp;&nbsp;&nbsp; -->
						<input type="submit" value="添加到试题筐" id="item_save" class="btn">
					</div>
				</form>
				<br>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="bt">@2016 招聘试题定制服务系统</div>
	</div>

</body>
</html>