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
<title>应聘者-在线考试</title>
<!-- IE浏览器运行最新的渲染模式，建议将此 <meta> 标签加入到你的页面中 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 方式一:确保适当的绘制和触屏缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap核心css -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/employee_public_part.css" />
<link rel="stylesheet" href="css/employee.css">

<style>
body {
	overflow-y: auto;
	line-height: 20px;
}

.navbar-default .navbar-nav>.active>a,.navbar-default .navbar-nav>.active>a:focus,.navbar-default .navbar-nav>.active>a:hover
	{
	color: #999;
	background-color: #333;
}

ul.nav-list li.active a,ul.nav-list li.active a:hover {
	background: lavender;
	border-radius: 10px;
	padding: 6px 30px;
}

ul.nav-stacked {
	top: 70px;
	left: 110px;
	position: fixed;
	padding: 26px;
}
</style>
</head>

<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script>
	javascript: window.history.forward(1);
	$(function() {
		var timerVal = $("#timer").val();
		setTimeout("form1.submit()", timerVal * 1000);
		var i = setInterval(function() {
			timerVal--;
			$("#timer").val(timerVal);
			if (timerVal < 1)
				clearInterval(i);
		}, 1000);
	});
</script>

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
						<li class="active">在线考试</li>

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

	<form action="next_job_exam_title.do" name="form1" method="post">
		<div class="bolg_body">
			<div class="container" id="doc_content">
				<div class="row">
					<div class="col-md-11 content-widget white-bg">
						<div>
							<h2 class="text-center">
								距离考试结束还有<span class="margin-left-10"> <input id="timer" readonly="readonly" 
									value="${time }" name="timer" style="border-style: none; width: 100px" />秒
								</span>
							</h2>
							<hr>
						</div>
						<div class="margin-left-100">
							<c:forEach var="c" items="${JobExamTitle }">
								<c:if test="${c.title_type == 0 }">
									<!--单选题  -->
									<div class="margin-top-10">
										<label class="padding-bottom-5"> ${pc.currentPage
											}、${c.title_text} </label>
										<div>
											<input type="hidden" name="title_id" value="${c.title_id }">
											<input type="radio" name="answer" value="A">A、${c.title_option_a}<br>
											<input type="radio" name="answer" value="B">B、${c.title_option_b}<br>
											<input type="radio" name="answer" value="C">C、${c.title_option_c}<br>
											<input type="radio" name="answer" value="D">D、${c.title_option_d}<br>
										</div>
									</div>
								</c:if>

								<c:if test="${c.title_type == 1 }">
									<!--多选题  -->
									<div class="margin-top-10">
										<label class="padding-bottom-5"> ${pc.currentPage
											}、${c.title_text} </label>
										<div>
											<input type="hidden" name="title_id" value="${c.title_id }">
											<input type="checkbox" name="mutil_answer" value="A">A、${c.title_option_a}<br>
											<input type="checkbox" name="mutil_answer" value="B">B、${c.title_option_b}<br>
											<input type="checkbox" name="mutil_answer" value="C">C、${c.title_option_c}<br>
											<input type="checkbox" name="mutil_answer" value="D">D、${c.title_option_d}<br>
										</div>
									</div>
								</c:if>

								<c:if test="${c.title_type == 2 }">
									<!--判断题  -->
									<div class="margin-top-10">
										<label class="padding-bottom-5"> ${pc.currentPage
											}、${c.title_text} </label>
										<div>
											<input type="hidden" name="title_id" value="${c.title_id }">
											<input type="radio" name="judge_answer" value="T">T<br>
											<input type="radio" name="Judge_answer" value="F">F<br>
										</div>
									</div>
								</c:if>

								<c:if test="${c.title_type == 3 }">
									<!--填空题  -->
									<div class="margin-top-10">
										<label class="padding-bottom-5"> ${pc.currentPage
											}、${c.title_text} </label>
										<div>
											<input type="hidden" name="title_id" value="${c.title_id }">
											<input type="text" name="blank_answer">
										</div>
									</div>
								</c:if>


							</c:forEach>
							<c:choose>
								<c:when test="${pc.hasNext }">
									<input type="submit" value="下一题">
								</c:when>
								<c:otherwise>
									<input type="submit" value="完成试卷">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
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
		$(document).ready(function() {
			$("#myNav").affix({
				offset : {
					top : 225
				}
			});
			$("#myNav").css("background", "white");
			$("#myNav").css("border-radius", "6px");
		});
	</script>
</body>

</html>