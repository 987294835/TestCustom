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
<title>应聘者-岗位申请</title>
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
	line-height: 24px;
}

.form-group {
	margin-bottom: 2px;
}

.navbar-default .navbar-nav>.active>a,.navbar-default .navbar-nav>.active>a:focus,.navbar-default .navbar-nav>.active>a:hover
	{
	color: #999;
	background-color: #333;
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
						<li><a href="personInfo.jsp">个人中心</a></li>
						<li class="active"><a href="#">岗位申请</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="nav_img"><img src="img/person.jpg"
							class="img-circle" id="head_img">
						</li>
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
						<span class="text-center name">${person_login.person_name}</span><br> <span
							class="btn btn-info ">修改密码</span> <span
							class="btn btn-info margin-left-20 quit">退出系统</span>
					</div>
				</div>
				<div class="col-md-8 content-widget white-bg">
					<label class="msg">已发布的岗位</label>
					<hr>
					<c:if test="${have_job == 0 }">
						<c:forEach var="c" items="${publish_jobs}">
							<div>
								<table class="job">
									<tr>
										<td style="width:4%;margin-top: 20px;"><strong>${c.job_name
												}</strong></td>
										<td style="width: 23%;"></td>
									</tr>
									<tr>
										<td class="text-left">岗位介绍：</td>
										<td><br> ${c.job_message }</td>
									</tr>
									<tr>
										<td>岗位要求：</td>
										<td>${c.job_request }</td>
									</tr>
								</table>
								<!-- 	<button class="btn btn-info btn-sm margin-left-100 job">点击申请</button> -->
								<a href="add_job_to_person.do?job_id=${c.job_id }">点击申请</a>
								<hr />

							</div>
						</c:forEach>
					</c:if>
					<c:if test="${have_job == 1 }">
						<strong>您已申请完岗位，请等候通知!!!</strong>
					</c:if>

				</div>
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
	<script type="text/javascript">
		/*function changeBtn()
		 {
		    var i=document.getElementById("job1");
		    i.style.background="darkgray";
		    i.style.border="darkgray";
		    i.innerHTML="已申请"
		 }*/
		$(":button").click(function() {
			$(this).attr("disabled", "disabled");
			$(this).css("background", "darkgray");
			$(this).css("border-color", "darkgray");
			$(this).val("已申请");
		});
		$(".quit").click(function(){
	  window.location.href="index.jsp";
	});
	</script>
</body>

</html>