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

<head>
<meta charset="UTF-8">
<title>应聘者首页</title>
<!-- IE浏览器运行最新的渲染模式，建议将此 <meta> 标签加入到你的页面中 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 方式一:确保适当的绘制和触屏缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap核心css -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/employee_public_part.css" />
<link rel="stylesheet" href="css/employee.css">

<style>
tr {
	line-height: 40px;
}

.form-group {
	margin-bottom: 2px;
}

.navbar-default .navbar-nav>.active>a,.navbar-default .navbar-nav>.active>a:focus,.navbar-default .navbar-nav>.active>a:hover
	{
	color: #999;
	background-color: #333;
}
}
</style>
</head>

<body>
	<!-- Fixed navbar -->
	<header>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<img src="img/backgrounds/1.jpg" class="navbar-brand">
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">个人中心</a>
						</li>
						<li><a href="get_all_jobs.do">岗位申请</a>
						</li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="nav_img"><img src="img/person.jpg"
							class="img-circle" id="head_img"></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>
	</header>

	<div class="bolg_body">
		<div class="container">
			<div class="row">
				<div class="col-md-3 content-widget white-bg">
					<img src="img/person.jpg" class="img-circle img-person">
					<div class="changePass text-center">
						<span class="text-center name">${person_login.person_name}</span><br>
						<button class="btn btn-info ">修改密码</button>
						<button class="btn btn-info margin-left-20 quit">退出系统</button>
					</div>
				</div>
				<div class="col-md-8 content-widget white-bg">
					<label class="msg">基本信息</label>
					<button type="button" class="btn btn-info btn-sm float-right"
						data-toggle="modal" data-target="#myModal">修 改</button>
					<hr>
					<table>
						<tr>
							<td class="text-right">姓 名：</td>
							<td>${person_login.person_name}</td>
						</tr>
						<tr>
							<td class="text-right">年 龄：</td>
							<td>${person_login.person_age }</td>
						</tr>
						<tr>
							<td class="text-right">性 别：</td>
							<td><c:if test="${person_login.person_sex == 0}">男</c:if> <c:if
									test="${person_login.person_sex == 1}">女</c:if>
							</td>
						</tr>
						<tr>
							<td class="text-right">邮 箱：</td>
							<td>${person_login.person_email}</td>
						</tr>
						<tr>
							<td>联系方式：</td>
							<td>${person_login.person_phone }</td>
						</tr>
						<tr>
							<td>教育经历：</td>
							<td>${person_login.person_education}</td>
						</tr>
						<tr>
							<td>工作经历：</td>
							<td>${person_login.person_practice}</td>
						</tr>
						<tr>
							<td>项目经历：</td>
							<td>${person_login.person_project}</td>
						</tr>
					</table>

				</div>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<span class="modal-title msg" id="myModalLabel"> 修改个人信息 </span>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" method="post"
									id="myForm">
									<div class="form-group">
										<label for="username" class="col-sm-2 control-label">姓名：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="username"
												name="person_name" value="${person_login.person_name}">
										</div>
									</div>
									<div class="form-group">
										<label for="age" class="col-sm-2 control-label">年龄：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="age"
												name="person_age" value="${person_login.person_age }">
										</div>
									</div>
									<div class="form-group">
										<label for="sex" class="col-sm-2 control-label">性别：</label>
										<div class="col-sm-10 radio">
											<select name="person_sex">
												<c:if test="${person_login.person_sex == 0}">
													<option value="1" selected="selected">男</option>
													<option value="2">女</option>
												</c:if>
												<c:if test="${person_login.person_sex == 1}">
													<option value="1" selected="selected">男</option>
													<option value="2">女</option>
												</c:if>
												<c:if test="${person_login.person_sex == 2 }">
													<option value="1">男</option>
													<option value="2" selected="selected">女</option>
												</c:if>

											</select><br>
										</div>
									</div>
									<div class="form-group">
										<label for="email" class="col-sm-2 control-label">邮箱：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="email"
												name="person_email" value="${person_login.person_email}">
										</div>
									</div>
									<div class="form-group">
										<label for="phone" class="col-sm-2 control-label">联系方式：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="phone"
												name="person_phone" value="${person_login.person_phone }">
										</div>
									</div>

									<div class="form-group">
										<label for="education" class="col-sm-2 control-label">教育经历：</label>
										<div class="col-sm-10">
											<textarea name="person_education">${person_login.person_education}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="work" class="col-sm-2 control-label">工作经历：</label>
										<div class="col-sm-10">
											<textarea name="person_practice">${person_login.person_practice}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="project" class="col-sm-2 control-label">项目经历：</label>
										<div class="col-sm-10">
											<textarea name="person_project">${person_login.person_project}</textarea>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary" onclick="goNext()">提交更改</button>

							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
	</div>
	<!-- footer -->
	<!--<footer>
			<nav class="navbar navbar-default ">
				<div id="fix_footer" class="container">
					<p>Bootstrap Themes</p>
				</div>
				<div id="null_div"></div>
			</nav>
		</footer>-->
	<!-- Bootstrap core JavaScript Placed at the end of the document so the pages load faster -->
	<script src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
	function goNext() {
		var myform = document.getElementById("myForm");
		myform.action = "add_person_message.do";
		myform.submit();
	}
	$(".quit").click(function(){
	  window.location.href="index.jsp";
	});
</script>

</html>