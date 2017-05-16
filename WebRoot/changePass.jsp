<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'changePass.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/findPassword.css">
		<style type="text/css">
.int {
	text-align: left;
}

.high {
	color: #ff4900;
	margin-left: 10px;
}

.msg {
	font-size: 13px;
	margin: 2px;
}

.onError {
	color: #ff4900;
	display: block;
	margin-bottom: 10px;
}

.onSuccess {
	color: #27AE60;
	font-weight: bold
}
</style>

	</head>

	<body>
		<div>
			<header class="htmleaf-header">
			<h1 style="margin-top: 5%">
				设置新密码
			</h1>
			</header>
			<div class="verify">
				<form action="set_new_pass.do" method="post" id="msform">
					<div class="int">
						<input type="text" name="user_name" id="user_name"
							placeholder=" 请输入用户名:" class="required">
					</div>
					<div class="int">
						<input type="text" name="id_code" id="id_code"
							placeholder=" 请输入验证码:" class="required">
					</div>
					<div class="int">
						<input type="text" name="new_pass" id="new_pass"
							placeholder=" 请输入新密码:" class="required">
					</div>
					<div class="int">
						<input type="submit" id="send" value="提交"
							style="background-color: #27AE60; color: white;">
					</div>

				</form>
			</div>
		</div>

	</body>
</html>
