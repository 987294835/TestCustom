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
<link rel="stylesheet" href="<%=basePath%>/css/item.css" type="text/css">
<style>
h3{display:inline;text-align:center;}          
hr{
    margin-top: 15px;
    margin-bottom: 15px;
    border: 0;
    border-top: 1px solid #eee
}
     
</style>
</head>
<body>
<script type="text/javascript">
    function checkText()
    {
        var str1 = document.getElementById('exam_name').value.trim();
        if(str1!=""){
           var myform = document.getElementById("myForm");
		   myform.action = "cart_input_history.do";
		   myform.submit();
        }
        else{
            alert("请输入试卷名");
        }
    }

</script>
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
					<li><a href="p_center.jsp">个人中心</a>
					</li>
					<li><a href="Select_exam.do">我的试卷</a>
					</li>
					<li><a href="Select_Collect.do">我的收藏</a>
					</li>
				</ul>
			</div>

			<div class="main_right">
				<div class="create">
				    <h3>试题筐</h3>
					<span class="collect_right"><a href="paper_create.jsp">返回推荐</a>
					</span>

				</div>
				<hr>
				<form  method="post" id="myForm">
					<div class="cart_create_list">

						<table>

						

							<!-- <tr><td class="paper_type">简答题</td><tr> -->
							<!-- <tr><td><input type="radio" id="j1">1、什么是SSH?</td></tr> -->
							<tr>
								<!-- <td>试卷名称:<input type="text" name="exam_name">
								</td> -->
							</tr>
							<c:forEach var="c" items="${cart_choice_titles }">
								<c:if test="${c.title_type ==0 }">
									<tr>
										<td>(选择题${c.title_id }) ${c.title_text } <a
											href="cart_remove_tilte.do?choice_id=${c.title_id }">删除</a></td>
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
							<c:forEach var="c" items="${cart_choice_titles }">
								<c:if test="${c.title_type ==1 }">
									<tr>
										<td>(多选题${c.title_id }) ${c.title_text } <a
											href="cart_remove_tilte.do?choice_id=${c.title_id }">删除</a></td>
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
							<c:forEach var="c" items="${cart_choice_titles }">
								<c:if test="${c.title_type ==2 }">
									<tr>
										<td>(填空题${c.title_id }) ${c.title_text } <a
											href="cart_remove_tilte.do?choice_id=${c.title_id }">删除</a></td>
									</tr>
								</c:if>
							</c:forEach>
							<c:forEach var="c" items="${cart_choice_titles }">
								<c:if test="${c.title_type ==3 }">
									<tr>
										<td>(问答题${c.title_id }) ${c.title_text } <a
											href="cart_remove_tilte.do?choice_id=${c.title_id }">删除</a></td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</div>
					<div class="exem_name">
						<span>试卷名称:&nbsp;</span><input type="text"  id="exam_name" name="exam_name">
						<input type="button" value="保存" id="item_save" class="btn" onclick="checkText()">
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