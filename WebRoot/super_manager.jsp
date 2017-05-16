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
<title>招聘试题定制服务--超级管理员用户管理</title>
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

.form-control {
	margin: 0 2px;
	display: inline;
	width: 100%;
	display: inline;
	height: 30px;
	padding: 2px;
}
.page{
    margin:24px 14px 2px;
}
.margin-left-10{margin-left:10px;}
.page>span{margin-right:10px;}
</style>
</head>

<script type="text/javascript">
		function changeUserType() {

			var obj = document.getElementsByName("u_id");
			/* 	for ( var i = 0; i < obj.length; i++) {
					if (obj[i].checked) {
						alert(document.getElementById(obj[i].value+"_u_type").value);
					}
				} */
			if (window.confirm("确认修改?")) {
				var myform = document.getElementById("userForm");
				myform.action = "change_user_type.do";
				myform.submit();
			   
			} else {
				window.location.href="user_manager.jsp";
			}

		}
		function deleteSelectedItem() {
			if (window.confirm("确认删除?")) {
				var myform = document.getElementById("userForm");
				myform.action = "delete_by_userid.do";
				myform.submit();
				
			} else {
				window.location.href="user_manager.jsp";
			}
		}
	</script>

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
					<li><a href="" class="active"><i class="fa fa-home fa-fw"></i>用户管理</a>
					</li>
					<li><a href="Show_All.do"><i
							class="fa fa-database fa-fw"></i>试题管理</a>
					</li>
					<li><a href="show_all_user_exams.do"><i
							class="fa fa-gears fa-fw"></i>试卷管理</a>
					</li>
					<li><a href="show_user_jobs.do"><i
							class="fa fa-gears fa-fw"></i>岗位管理</a>
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
							<li><a class="active margin-right-25">用户管理</a>
							</li>
						</ul>
						<div class="nav-img-personal nav_img">
		<div class="profile-person-overlay"></div>
	</div>
					</nav>

				</div>
			</div>
			<!--content-->
			<form action="" id="userForm" method="post">
			<div class="tcs-content-container">
				<div class="tcs-content-widget white-bg">
					<div class="panel panel-default table-responsive">
						<table class="table table-striped table-bordered tcs-tests-table">
							<thead>
								<tr>
									<td>选择</td>
									<td>用户名</td>
									<td>用户角色</td>
									<td>电 话</td>
									<td>邮 箱</td>
									<td>地 址</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="u" items="${users }">
									<tr>
										<td><input type="checkbox" value="${u.user_id }"
												name="u_id"></td>
										<td>${u.user_name }</td>
										<td><select class="form-control" name="${u.user_id }_u_type">
												<c:if test="${u.user_type==1 }">
													<option value="1">用户管理员</option>
												</c:if>
												<c:if test="${u.user_type==3 }">
													<option value="3">普通用户</option>
												</c:if>
												<c:if test="${u.user_type!=1 }">
													<option value="1">用户管理员</option>
												</c:if>
												<c:if test="${u.user_type!=3 }">
													<option value="3">普通用户</option>
												</c:if>
										</select>
										</td>
										<td>${u.user_phone }</td>
										<td>${u.user_email }</td>
										<td>${u.user_address }</td>
										<%-- <td><a href="" class="tcs-edit-btn">修改</a>
										</td>
										<td><a href="delete_by_userid.do?u_id=${u.user_id }" class="tcs-edit-btn">删除</a>
										</td> --%>
									</tr>
								</c:forEach>
						</table>
					</div>
					
					<!-- <ul class="pagination">
						<li class="disabled"><a href="#">&laquo;</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul> -->
					<!-- 分页信息 -->

					<div id="PaginationBar_info" class="page"">
						<span>${pc.currentPage}/${pc.totalPages}</span>
						<%-- <span>总记录数：${pc.totalRowsAmount}条 </span> --%>
						<!-- <span>每页显示
				<select id="PaginationBar_PageSizeSelector" onClick="ChangePageSize(document.getElementById('PaginationBar_PageSizeSelector').value)">
					<option value="3">3</option>
					<option value="6">6</option>
					<option value="9">9</option>
				</select>
				条
			</span> -->
						<c:choose>
							<c:when test="${pc.currentPage==1 }">
								<span>首页</span>
							</c:when>
							<c:otherwise>
								<a href="page_change.do?currPage=${pc.firstPage }">首页</a>
							</c:otherwise>
						</c:choose>
						<%-- <c:forEach var="i" begin="1" end="${pc.totalPages}">
				    <td class="PageSelectorNum" onClick="gotoPage(${i})">${i}</td>
			       </c:forEach> --%>
						<c:choose>
							<c:when test="${pc.hasPrevious }">
								<a href="page_change.do?currPage=${pc.currentPage-1 }">上一页</a>
							</c:when>
							<c:otherwise>
								<span>上一页</span>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pc.hasNext }">
								<a href="page_change.do?currPage=${pc.currentPage+1 }">下一页</a>
							</c:when>
							<c:otherwise>
								<span>下一页</span>
							</c:otherwise>
						</c:choose>


						<c:choose>
							<c:when test="${pc.currentPage>=pc.totalPages }">
								<span>尾页</span>
							</c:when>
							<c:otherwise>
								<a href="page_change.do?currPage=${pc.totalPages }">尾页</a>
							</c:otherwise>
						</c:choose>
						<button class="btn btn-info" onclick="deleteSelectedItem()">删除用户</button>
						<button class="btn btn-info margin-left-10" onclick="changeUserType()">修改权限</button>
					</div>
				</div>	
			</div>
			
			<footer class="text-right">
			<p>Copyright &copy; 2016 招聘试题定制服务</p>
			</footer>
		</div>
		
	</div>
		</form>
	<!--Js-->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>

</html>