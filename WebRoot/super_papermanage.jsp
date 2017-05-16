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
<title>招聘试题定制服务--超级管理员试题管理</title>
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
.page{
    margin:24px 14px 2px;
}

.page>span{margin-right:10px;}
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
					<li><a href="select_all_user.do"><i
							class="fa fa-home fa-fw"></i>用户管理</a></li>
					<li><a href="#" class="active"><i
							class="fa fa-database fa-fw"></i>试题管理</a></li>
					<li><a href="show_all_user_exams.do"><i
							class="fa fa-gears fa-fw"></i>试卷管理</a></li>
					<li><a href="show_user_jobs.do"><i
							class="fa fa-gears fa-fw"></i>岗位管理</a></li>

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
							<li><a class="active margin-right-25" style="border: none;">题目管理</a>
							</li>
							<li><a href="toAdd.do">+题目</a></li>
							<li><a href="delete_title_log.do">-智能清题</a></li>
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
						<form role="form" action="major_condition.do" method="post">
							<div class="form-group">
								<select class="form-control" name="type">
									<option>题型</option>
									<option value="0">单选题</option>
									<option value="1">多选题</option>
									<option value="2">判断题</option>
									<option value="3">填空题</option>
								</select> <select class="form-control" name="major">
									<option>职位</option>
									
									<c:forEach var="j" items="${major }">
										<option value=${j }>${j }</option>
									</c:forEach>
								</select> <select class="form-control" name="backgroud">
									<option value=0>背景</option>
									<option value=0>应届毕业生</option>
									<option value=1>1~2年工作经验</option>
									<option value=2>2~4年工作经验</option>
									<option value=3>5年以上工作经验</option>
								</select>
								<!-- <button type="button" class="btn btn-info">确定</button> -->
								<input type="submit" value="确定"  class="btn btn-info"/>
							</div>
						</form>
					</div>
					<hr />
					<div class="panel panel-default table-responsive">
						<table class="table table-striped table-bordered tcs-tests-table">
							<thead>
								<tr>
									<td><a href="" class="white-text tcs-sort-by">题目<span
											class="caret"></span> </a></td>
									<td><a href="" class="white-text tcs-sort-by">类型<span
											class="caret"></span> </a></td>
									<td><a href="" class="white-text tcs-sort-by">从属<span
											class="caret"></span> </a></td>
									<td><a href="" class="white-text tcs-sort-by">背景<span
											class="caret"></span> </a></td>
									<td>Edit</td>
									<td>Delete</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="t" items="${titles }">
									<tr>

										<td>
											<div
												style="width:250px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"
												title="${t.title_text}">
												<a href="see_information.do?title_id=${t.title_id }">${t.title_text}</a>
											</div></td>

										<c:choose>
											<c:when test="${t.title_type=='0'}">
												<TD height="30">单选题</TD>
											</c:when>

											<c:when test="${t.title_type=='1'}">
												<TD height="30">多选题</TD>
											</c:when>

											<c:when test="${t.title_type=='2'}">
												<TD height="30">判断题</TD>
											</c:when>

											<c:when test="${t.title_type=='3'}">
												<TD height="30">填空题</TD>
											</c:when>

											<%--  <c:otherwise>
                 <TD height="30">填空题</TD>	                 
                </c:otherwise>  --%>
										</c:choose>

										<td>${t.title_major}</td>

										<c:choose>
											<c:when test="${t.title_backgroud=='0'}">
												<td>应届生</td>
											</c:when>

											<c:when test="${t.title_backgroud=='1'}">
												<td>1~2年经验</td>
											</c:when>

											<c:when test="${t.title_backgroud=='2'}">
												<td>2~4年经验</td>
											</c:when>

											<c:when test="${t.title_backgroud=='3'}">
												<td>5年以上经验</td>
											</c:when>
										</c:choose>

										<%-- <c:forEach var="u" items="${user_login }">  --%>
										<%-- <c:choose>
											<c:when test="${user_login.user_type=='2'}">
												<td><a href="toModify.do?id=${t.title_id} "
													onclick="return window.confirm('确认要修改试题吗？')"> 改</a>/ <a
													href="delete.do?id=${t.title_id}"
													onclick="return window.confirm('确认要删除试题吗？')">删</a>
												</td>
											</c:when>

											<c:when test="${user_login.user_type=='1'}">
												<td><a href="toModify.do?id=${t.title_id}"
													onclick="return window.confirm('确认要修改试题吗？')"> 改</a>/ <a
													href="toModify.do?id=${t.title_id}"
													onclick="return window.confirm('确认要删除试题吗？')">删</a>
												</td>
											</c:when>

											<c:otherwise>
												<td><a href="#"
													onclick="return window.confirm('用户，您并没有这个权限')"> 改</a>/ <a
													href="#" onclick="return window.confirm('用户，您并没有这个权限')">删</a>
												</td>
											</c:otherwise>
										</c:choose> --%>
										<td><a href="toModify.do?id=${t.title_id}" class="tcs-edit-btn">Edit</a>
										</td>
										<td><a href="delete.do?id=${t.title_id}" class="tcs-link">Delete</a>
										</td></c:forEach>

							</tbody>
						</table>
					</div>
					<!-- <ul class="pagination">
						<li class="disabled"><a href="#">&laquo;</a>
						</li>
						<li class="active"><a href="#">1</a>
						</li>
						<li><a href="#">2</a>
						</li>
						<li><a href="#">3</a>
						</li>
						<li><a href="#">4</a>
						</li>
						<li><a href="#">5</a>
						</li>
						<li><a href="#">&raquo;</a>
						</li>
					</ul> -->
					<p class="page">
						<span>${pc.currentPage }/${pc.totalPages}</span>
						<c:choose>
							<c:when test="${pc.currentPage==1 }">
								<span>首页</span>
								<span>上一页</span>
							</c:when>
							<c:otherwise>
								<a href="mqforpage.do?currPage=1">首页</a>
								<a href="mqforpage.do?currPage=${pc.previousPage }">上一页</a>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${pc.currentPage>=pc.totalPages }">
								<span>下一页</span>
								<span>尾页</span>
							</c:when>
							<c:otherwise>
								<span><a href="mqforpage.do?currPage=${pc.nextPage }">下一页</a></span>
								<span><a href="mqforpage.do?currPage=${pc.totalPages }">尾页</a></span>
							</c:otherwise>
						</c:choose>
						<!--    选择框做法 -->
						<span> <select id="toPage" onchange="toPage(this.value)">
								<c:forEach var="index" begin="1" end="${pc.totalPages }">
									<c:choose>
										<c:when test="${pc.currentPage==index }">
											<option value="${index }" selected="selected">${index
												}</option>
										</c:when>
										<c:otherwise>
											<option value="${index }">${ index}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select> </span>
					</p>
					<script type="text/javascript">     
            function toPage(index){
                  window.location.href="mqforpage.do?currPage="+index;
            }      
       </script>
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