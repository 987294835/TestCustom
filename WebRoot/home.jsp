<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>招聘试题定制服务</title>
		<!-- IE浏览器运行最新的渲染模式，建议将此 <meta> 标签加入到你的页面中 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 方式一:确保适当的绘制和触屏缩放 -->
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
		<!-- Bootstrap核心css -->
		<link href="css/font-awesome.min.css" rel="stylesheet">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/tcs-style.css" rel="stylesheet">
		<link href="css/left-column.css" rel="stylesheet">
		<link href="css/main-content.css" rel="stylesheet" />

		<link href="css/media.css" rel="stylesheet" />
		<style>
			body{overflow-y: hidden;}
			.nav-img-personal{
				height: 50px;
			}
             p{line-height:24px;}
             i{margin-right:15px;}
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
					<img src="img/profile-photo.jpg" alt="Profile Photo" class="img-responsive">
					<div class="profile-photo-overlay"></div>
				</div>
				<div class="mobile-menu-icon">
					<i class="fa fa-bars"></i>
				</div>
				<div class="tcs-left-nav">
					<ul>
						<li>
							<a href="home.jsp" class="active"><i class="fa fa-home fa-fw"></i>首 页</a>
						</li>
						<li>
							<a href="Show_All.do"><i class="fa fa-database fa-fw"></i>题库</a>
						</li>
						<li>
							<a href="select_Major.do"><i class="fa fa-gears fa-fw"></i>试题定制</a>
						</li>
						<li>
							<a href="addPosition.jsp"><i class="fa fa-gears fa-fw"></i>添加岗位</a>
						</li>
						<li>
							<a href="shopCart.jsp"><i class="fa fa-cart-plus fa-fw"></i>试题筐</a>
						</li>
						<li>
							<a href="Select_exam.do" ><i class="fa fa-folder-open fa-fw"></i>历史记录</a>
						</li>

					</ul>
				</div>
			</div>
			<!--Main content-->
			<div class="tcs-content col-1 light-gray-bg">
				<!--content nav-->
				<div class="tcs-top-nav-container">
					<div class="row ">
					 	<h2 class="margin-left-10 line-height-50" style="display: inline;font-size:32px;">招聘试题定制服务</h2>
						<div class="nav-img-personal nav_img" >
					    <div class="profile-person-overlay">
					    	<img class="img-circle tcs-img-bordered person-img" src="img/person.jpg" alt="Sunset">
					        <span class="margin-left-10" style="color: whitesmoke;">用户名</span>
					        <div>
					        	<a href="p_center.jsp" class="float-left">个人中心</a>
					        	<a href="index.jsp" class="float-right">退出</a>
					        </div>
					    </div>
					    </div>
					</div>
				</div>
				<!--content-->
				<div class="tcs-content-container">
					<div class="tcs-flex-row flex-content-row">
						<div class="tcs-content-widget white-bg col-2">
							<!--<div class="square"></div>
							--><h2 class="tcs-inline-block">系统简介</h2>
							<hr>
							<p>针对目前企业招聘时由于时间不足等问题造成的招聘试题针对性不足与时效性较低的现象，
							同时为了解决题库的臃肿和淘汰过时的题目，方便出题者平时对于理想题目的管理和收藏。同时，给应聘该公司岗位的应聘者提供一个在线考试平台。
</p>
						</div>
						<div class="tcs-content-widget white-bg col-1">
						<i class="fa fa-database fa-fw"></i>
						<h2 class="tcs-inline-block">题 库</h2>
							<hr>
							<p>招聘者可按照题型，职位，背景进行关键字查询试题和添加试题</p>
						</div>
						<div class="tcs-content-widget white-bg col-1">
						<i class="fa fa-gears fa-fw"></i>
							<h2 class="tcs-inline-block">试题定制</h2>
							<hr>
							<p>根据用户输入的条件生成推荐的试题推荐方案</p>
						</div>
					</div>
					<div class="tcs-flex-row flex-content-row">
						<div class="tcs-content-widget white-bg col-4">
						<i class="fa fa-gears fa-fw"></i>
							<h2 class="tcs-inline-block">添加岗位</h2>
							<hr>
							<p>招聘者可自定义所要发布的岗位名称，信息和要求</p>
						</div>
						<div class="tcs-content-widget white-bg col-4">
						<i class="fa fa-cart-plus fa-fw"></i>
						<h2 class="tcs-inline-block">试题筐</h2>
							<hr>
							<p>负责暂存招聘者预选的试题，并可通过增删试题进一步保存为卷</p>
						</div>
						
						<div class="tcs-content-widget white-bg col-4">
							<i class="fa fa-folder-open fa-fw"></i>
							<h2 class="tcs-inline-block">历史纪录</h2>
							<hr>
							<p>查看已保存的试卷，添加的岗位，我的收藏</p>
						</div>
					</div>
					<footer class="text-right">
						<p>Copyright &copy; 2016 招聘试题定制服务</p>
					</footer>
				</div>
			</div>
		</div>

		<!--Js-->
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
	</body>

</html>