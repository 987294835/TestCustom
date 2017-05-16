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
<title>招聘试题定制服务系统_岗位管理</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/index.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/item.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath%>/css/other.css"
	type="text/css">
 <style>
        
        .usermanager_item_btn {
            position: absolute;
            bottom:17%;
        }
    </style> 
</head>
<body>

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

	<div class="header">
		<a class="about">关于我们</a>
		<ul class="breadcrumb">
			<li>您好,</li>
			<li><a href="p_center.jsp">${user_login.user_name }</a>
			</li>
			<li><a href="login.jsp">&nbsp;[退出]</a>
			</li>
		</ul>

	</div>
	<div class="content">
		<div class="page-header">
			<h1>招聘试题定制服务系统</h1>
		</div>
		<div class="page-header1"></div>
		<div class="main">
			<div class="main_left">
				<div class="pic">
					<img src="<%=basePath%>/css/images/f8.png" width="80" height="80">
				</div>
				<br>
				<br>
				<ul class="menu">
				<li><a href="select_all_user.do">用户管理</a></li>
				  <li><a href="Show_All.do">试题管理</a></li>
                  <li><a href="show_all_user_exams.do">试卷管理</a></li>
                  <li><a href="show_user_jobs.do">岗位管理</a></li>
					
				</ul>
			</div>

			<div class="main_right">
			
				<form id="userForm" method="post">
				<div class="usermanager_create_list">
					<span>岗位管理</span>
					<hr>
					<table class="user_table">
						<tr class="table_head">

							<td>岗位ID</td>
							
							<td>岗位名称</td>
							<td>添加时间</td>
							<td>发布状态</td>
							<td>试卷ID</td>
							<td>操作</td>
							
						</tr>
						<c:forEach var="u" items="${allJobsByPage }">
							<tr>
								<td>${u.job_id }</td>
								<td>${u.job_name}</td>
								<td>${u.job_time}</td>
								<td>
									<c:choose>
										<c:when test="${u.job_state == 0 }">
											未发布
										</c:when>
										<c:otherwise>
											已发布
										</c:otherwise>
									</c:choose>
								</td>
								<td>${u.exam_id}</td>
								<td><a href="see_persons.do?job_id=${u.job_id }">查看</a>/
								<a href="delete_user_job.do?job_id=${u.job_id }">删除</a></td>
							</tr>
						</c:forEach>
					</table>
					<br>
				 </div>
					<!-- 分页信息 -->

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
								<a href="all_user_job_change.do?currPage=${pc.firstPage }">首页</a>
							</c:otherwise>
						</c:choose>
						<%-- <c:forEach var="i" begin="1" end="${pc.totalPages}">
				    <td class="PageSelectorNum" onClick="gotoPage(${i})">${i}</td>
			       </c:forEach> --%>
						<c:choose>
							<c:when test="${pc.hasPrevious }">
								<a href="all_user_job_change.do?currPage=${pc.currentPage-1 }">上一页</a>
							</c:when>
							<c:otherwise>
								<span>上一页</span>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${pc.hasNext }">
								<a href="all_user_job_change.do?currPage=${pc.currentPage+1 }">下一页</a>
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
								<a href="all_user_job_change.do?currPage=${pc.totalPages }">尾页</a>
							</c:otherwise>
						</c:choose>
                      
				
                     <div class="btn_right">
						
                      </div>
   	</div>
				</form>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="bt">@2016 招聘试题定制服务系统</div>
	</div>

</body>
</html>