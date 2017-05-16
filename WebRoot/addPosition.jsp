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
<title>招聘试题定制服务--添加岗位</title>
<!-- IE浏览器运行最新的渲染模式，建议将此 <meta> 标签加入到你的页面中 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 方式一:确保适当的绘制和触屏缩放 -->
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- Bootstrap核心css -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/tcs-style.css" rel="stylesheet">
<link href="css/left-column.css" rel="stylesheet">
<link href="css/main-content.css" rel="stylesheet" />
<link href="css/tests_manage.css" rel="stylesheet" />
<link href="css/media.css" rel="stylesheet" />
<style>
body {
	overflow-y: hidden;
}
</style>
</head>

<body>
	<div class="tcs-flex-row">
		<!--left sidebar-->
		<div class="tcs-sidebar">
			<header class="tcs-site-header">
				<h1 style="line-height:28px;">Recruitment CS</h1>
			</header>
			<div class="profile-photo-container">
				<img src="img/profile-photo.jpg" alt="Profile Photo"
					class="img-responsive">
				<div class="profile-photo-overlay"></div>
			</div>
			<div class="mobile-menu-icon">
				<i class="fa fa-bars"></i>
			</div>
			<div class="tcs-left-nav">
				<ul>
					<li><a href="home.jsp" ><i
							class="fa fa-home fa-fw"></i>首 页</a>
					</li>
					<li><a href="Show_All.do"><i class="fa fa-database fa-fw"></i>题库</a>
					</li>
					<li><a href="select_Major.do"><i class="fa fa-gears fa-fw"></i>试题定制</a>
					</li>
					<li><a href="addPosition.jsp" class="active"><i class="fa fa-gears fa-fw"></i>添加岗位</a>
					</li>
					<li><a href="shopCart.jsp"><i
							class="fa fa-cart-plus fa-fw"></i>试题筐</a>
					</li>
					<li><a href="Select_exam.do"><i
							class="fa fa-folder-open fa-fw"></i>历史记录</a>
					</li>

				</ul>
			</div>
		</div>
		<!--Main content-->
		<div class="tcs-content col-1 light-gray-bg">
			<!--content nav-->
			<div class="tcs-top-nav-container">
				<div class="row line-height-50">
					<nav class="tcs-top-nav col-lg-12 col-md-12">
						<ul>
							<li><a class="active margin-right-25">添加岗位</a></li>
						</ul>
						<div class="nav-img-personal nav_img">
		<div class="profile-person-overlay"></div>
	</div>
					</nav>

				</div>
			</div>
			<!--content-->
			<div class="tcs-content-container">
				<div class="tcs-content-widget white-bg">
					<div>
						<form action="add_job.do" method="post">
							<div class="form-group">
								岗位名称：<input type="text" class="form-control" name="job_name" required="required" /><br>
								岗位信息：<textarea class="form-control" rows="3" name="job_message"></textarea>
								<br> 岗位要求：<textarea class="form-control" rows="3" name="job_request"></textarea>
								<br>
								<!-- <button type="button" class="btn btn-info width-25 margin-10">确定</button> -->
								<input type="submit" value="确定"  class="btn btn-info width-25 margin-10">
							</div>
						</form>
					</div>
				</div>
			</div>
			<footer class="text-right">
				<p>Copyright &copy; 2016 招聘试题定制服务</p>
			</footer>
		</div>
	</div>
	<!--Js-->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>

</html>