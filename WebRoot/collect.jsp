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
<title>招聘试题定制服务--我的试卷</title>
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

.table>tbody>tr>td,.table>tbody>tr>th,.table>tfoot>tr>td,.table>tfoot>tr>th,.table>thead>tr>td,.table>thead>tr>th
	{
	border-top: none;
}

.page{
   /* margin:24px 2px;*/
    text-align: left;
    margin-left: 8%;
    
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
					<li><a href="home.jsp"><i class="fa fa-home fa-fw"></i>首 页</a>
					</li>
					<li><a href="Show_All.do"><i class="fa fa-database fa-fw"></i>题库</a>
					</li>
					<li><a href="select_Major.do"><i class="fa fa-gears fa-fw"></i>试题定制</a>
					</li>
					<li><a href="addPosition.jsp"><i class="fa fa-gears fa-fw"></i>添加岗位</a>
					</li>
					<li><a href="shopCart.jsp"><i
							class="fa fa-cart-plus fa-fw"></i>试题筐</a></li>
					<li><a href="Select_exam.do" class="active"><i
							class="fa fa-folder-open fa-fw"></i>历史记录</a></li>

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
							<li><a class="margin-right-25" href="Select_exam.do">我的试卷</a>
							</li>
							<li><a href="show_jobs.do">已添加的岗位</a>
							</li>
							<li><a href="#" class="active ">我的收藏</a>
							</li>
						</ul>
						<div class="nav-img-personal nav_img">
		<div class="profile-person-overlay"></div>
	</div>
					</nav>

				</div>
			</div>
			<!--content-->
			<form action="add_title_to_cart.do" method="post">
			<div class="tcs-content-container">
				<div class="tcs-content-widget white-bg">
					<div class="table-responsive">
						<table class="table">
							<tbody>
								<c:forEach var="c" items="${titles}">
									<tr>
										<!--<td style="width:5%;">
											<div class="circle green-bg"></div>
										</td>-->
										<td class="text-left" style="width: 25%;"><input
											type="checkbox" value="${c.title_id }" name="choice_ids">
											(${c.title_id }) ${c.title_text}</td>

										<td style="width: 10%;"><a
											href="see_information.do?title_id=${c.title_id }"
											class="tcs-link">查看</a></td>
										<td style="width: 10%;"><a
											href="Delect_Collect.do?title_id=${c.title_id }"
											class="tcs-link">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="PaginationBar" align="center">
			<div id="PaginationBar_info" class="page">
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
						<a href="collect_page_change.do?currPage=${pc.firstPage }">首页</a>
					</c:otherwise>
				</c:choose>
				<%-- <c:forEach var="i" begin="1" end="${pc.totalPages}">
				    <td class="PageSelectorNum" onClick="gotoPage(${i})">${i}</td>
			       </c:forEach> --%>
				<c:choose>
					<c:when test="${pc.hasPrevious }">
						<a href="collect_page_change.do?currPage=${pc.currentPage-1 }">上一页</a>
					</c:when>
					<c:otherwise>
						<span>上一页</span>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pc.hasNext }">
						<span><a href="collect_page_change.do?currPage=${pc.currentPage+1 }">下一页</a></span>
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
						<span><a href="collect_page_change.do?currPage=${pc.totalPages }">尾页</a></span>
					</c:otherwise>
				</c:choose>
				<input type="submit" value="添加到试题筐" style="margin-left: 50px" class="btn btn-info"> 
			</div>
			
		</div>
			
			</form>
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