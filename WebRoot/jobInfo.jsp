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
				<div class="square"></div>
				<h1>Visual TCS</h1>
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
	
					<li><a href="home.jsp"><i class="fa fa-home fa-fw"></i>首 页</a>
					</li>
					<li><a href="Show_All.do"><i class="fa fa-database fa-fw"></i>题库</a>
					</li>
					<li><a href="select_Major.do"><i class="fa fa-gears fa-fw"></i>试题定制</a>
					</li>
					<li><a href="addPosition.jsp"><i class="fa fa-gears fa-fw"></i>添加岗位</a>
					</li>
					<li><a href="shopCart.jsp"><i
							class="fa fa-cart-plus fa-fw"></i>试题筐</a>
					</li>
					<li><a href="Select_exam.do" class="active"><i
							class="fa fa-folder-open fa-fw"></i>历史记录</a>
					</li>
				</ul>
			</div>
		</div>
		<!--Main content-->
		<div class="tcs-content col-1 light-gray-bg">
			<!--content nav-->
			<div class="tcs-top-nav-container">
				<div class="row">
					<nav class="tcs-top-nav col-lg-12 col-md-12">
						<ul>
							<li><a class="active margin-right-25">编辑岗位</a></li>
						</ul>
						<img class="img-circle tcs-img-bordered nav-img"
							src="img/person.jpg" alt="Sunset">
						<div class="profile-person-overlay"></div>
					</nav>

				</div>
			</div>
			<!--content-->
			<div class="tcs-content-container">
				<div class="tcs-content-widget white-bg">
					<div>
						<form role="form" action="reset_job.do" method="post">
							<div class="form-group">
						    	<div>岗位名称：<input type="text" class="form-control" name="job_name" value="${jobinfo.job_name }"/></div>
								<div>岗位信息：<textarea class="form-control" rows="3" name="job_message">${jobinfo.job_message }</textarea></div>
								<div>岗位要求：<textarea class="form-control" rows="3" name="job_request">${jobinfo.job_request }</textarea></div>
								<div>考试时间：<input type="datetime-local" class="form-control"  name="dateTime" /></div>
								<div> 添加试卷：<select class="form-control"
									name="exam_id">
									<c:if test="${jobinfo.exam_id > 0 }">
										<option value="${jobinfo.exam_id }">${jobinfo.exam_id}</option>
									</c:if>
									<option value="0">空</option>
									<c:forEach var="j" items="${exams }">
										<option value=${j.exam_id }>${j.exam_name }</option>
									</c:forEach>
								</select> 
								<a href="select_Major.do" class="btn btn-default  margin-10">定制试卷</a></div>
							   <div><input type="submit" value="确定" class="btn btn-info width-25 margin-10"></div>
								<!-- <button type="button" class="btn btn-info width-25 margin-10">确定</button> -->
								
							</div>
						</form>
					</div>
				</div>
			</div>
			<footer class="text-right">
				<p>
					Copyright &copy; 2084 Company Name | Designed by <a
						href="http://www.tcs.com" target="_parent">tcs</a>
				</p>
			</footer>
		</div>
	</div>
	<!--Js-->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>

</html>