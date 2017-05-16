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
<head lang="en">
<meta charset="UTF-8">
<title>招聘试题定制服务系统_我的收藏</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/index.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/item.css"
	type="text/css">
	  

	
</head>
<body>
	<div class="header">
		<a class="about">关于我们 </a>
		<ul class="breadcrumb">
			<li>您好,</li>
			<li><a href="p_center.jsp">${user_login.user_name }</a></li>
			<li><a href="login.jsp">&nbsp;[退出]</a></li>
		</ul>

	</div>
	<div class="content">
		<div class="page-header">
			<h1>招聘试题定制服务系统</h1>
		</div>
		<div class="page-header1"></div>
		<div class="main">
			<div class="main_left">
				<ul class="menu">
					<li><a href="Select_exam.do">我的试卷</a>
					</li>
					<li><a href="Select_Collect.do">我的收藏</a>
					</li>
				</ul>
			</div>

			<div class="main_right">
				<div class="create">
					<span>我的收藏</span> <span class="collect_right"><a
						href="home.jsp">返回首页</a> </span>

				</div>
				<hr>
				<form action="add_title_to_cart.do" method="post" id="myForm">
					<div class="collect_create_list">

						<table>
							<c:forEach var="c" items="${titles}">
								<tr>
									<td><input type="checkbox" value="${c.title_id }"
										name="choice_ids"> ${c.title_id }、 ${c.title_text
										}&nbsp;&nbsp;&nbsp; <a
										href="see_information.do?title_id=${c.title_id }">查看</a> / <a
										href="Delect_Collect.do?title_id=${c.title_id }">删除</a>
									</td>
								</tr>
</c:forEach>
						</table>
					</div>
<!-- 分页信息 -->
		<div id="PaginationBar" align="center">
			<div id="PaginationBar_info">
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
						<a href="collect_page_change.do?currPage=${pc.currentPage+1 }">下一页</a>
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
						<a href="collect_page_change.do?currPage=${pc.totalPages }">尾页</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br>
					<input type="submit" value="添加到试题筐" id="item_save"
						class="collect_item_btn btn">
				</form>
			</div>
		</div>
		<div class="footer">
		<div class="bt">@2016 招聘试题定制服务系统</div>
	</div>

</body>
</html>