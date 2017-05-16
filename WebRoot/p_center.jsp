<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>招聘试题定制服务系统_个人中心</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/index.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/item.css"
	type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/css/other.css"
	type="text/css">
</head>
<body>
<script type="text/javascript">
    function checkText()
    {
        var str1 = document.getElementById('user_name').value.trim();
        if(str1!=""){
           var myform = document.getElementById("myForm");
		   myform.action = "update_user.do";
		   myform.submit();
        }
        else{
            alert("请输入用户名");
        }
    }

</script>
	<div class="header">
		<a class="about" href="about_Us.jsp">关于我们</a>
		<ul class="breadcrumb">
			<li>您好,</li>
			<li><a href="#">${user_login.user_name }</a>
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
				<div class="pic">
					<img src="<%= basePath %>css/images/f8.png" width="80" height="80">
					<br> 
			       <span><a href="change_password.jsp">修改密码</a></span>
				</div>
				
				<br>
				<ul class="menu">
					<li><a href="select_Major.do">试题定制</a></li>
					<li><a href="showcart.jsp">试题筐</a></li>
					<li><a href="Select_exam.do">历史记录</a></li>

				</ul>
			</div>

			<div class="main_right">
			<div class="create">
			        <span>个人信息 </span>
					<span class="collect_right"><a href="home.jsp">返回首页</a> </span><hr>
               </div>
				<form class="p_center" id="myForm"
					method="post">
					<table>
						<tr>
							<td class="td_right">用户名：</td>
							<td><input type="text"
								value="${user_login.user_name }" name="user_name" id="user_name">
							</td>
						</tr>
						<tr>
							<td class="td_right">联系方式：</td>
							<td><input type="text"
								value="${user_login.user_phone }" name="user_phone" id="user_phone">
							</td>
						</tr>
						<tr>
							<td class="td_right">邮箱：</td>
							<td><input type="text" value="${user_login.user_email }"
								name="user_email" id="user_email">
							</td>
						</tr>
						<tr>
							<td class="td_right">住址：</td>
							<td><input type="text"
								value="${user_login.user_address }" name="user_address" id="user_address">
							</td>
						</tr>
						<tr>
							<td></td><td><input style="width:70px" class="btn"
							type="button" value="保存" id="item_save" onclick="checkText()">
							</td>
						</tr>
					</table>
				</form>
				<br>
			</div>
		</div>
	</div>
	<footer class="text-right">
						<p>Copyright &copy; 2016 招聘试题定制服务</p>
					</footer>

</body>
</html>