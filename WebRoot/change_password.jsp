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
        var str1 = document.getElementById('new_user_password').value.trim();
        var str2 = document.getElementById('new_user_password1').value.trim();
        if(str1 == str2){
           var myform = document.getElementById("myForm");
		   myform.action = "change_password.do";
		   myform.submit();
        }
        else{
            alert("密码不一致");
        }
    }

</script>
	<div class="header">
		<a class="about">关于我们</a>
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
			<a href=" " onClick="javascript:history.back(-1);">&nbsp;&nbsp;&nbsp;返回上一页 </a>
		
					<span class="collect_right"><a 
						href="home.jsp">返回首页</a> </span><hr>

				</div>
				<form class="p_center" id="myForm"
					method="post">
					<table>
						
						<tr>
							<td class="td_right">旧密码：</td>
							<td><input type="password"
								name="user_password" id="user_password">
							</td>
						</tr>
						<tr>
							<td class="td_right">新密码：</td>
							<td><input type="password" name="new_user_password" id="new_user_password">
							</td>
						</tr>
						<tr>
							<td class="td_right">密码确认：</td>
							<td><input type="password" 
								name="new_user_password1" id="new_user_password1">
							</td>
						</tr>
						
						<tr>
							<td></td><td><input type="button" value="保存" id="item_save" class="btn" onclick="checkText()">
							</td>
						</tr>
					</table>
				</form>
				<br>
			</div>
		</div>
	</div>
	<div class="footer">
		<p>Copyright &copy; 2016 招聘试题定制服务</p>
	</div>

</body>
</html>