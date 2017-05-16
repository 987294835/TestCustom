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
<title>招聘试题定制服务系统_试题管理</title>
<link rel="stylesheet" href="<%=basePath %>/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath %>/css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%=basePath %>/css/index.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>/css/item.css" type="text/css">

</head>
<body>




	<div class="header">
		  <a class="about" href="about_Us.jsp">关于我们</a>
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
				  <li><a href="p_center.jsp">个人中心</a></li>
                  <li><a href="showcart.jsp">试题筐</a></li>
                  <li><a href="Select_exam.do">历史记录</a></li>
				</ul>
			</div>

			<div class="main_right">
				<div class="paper_manager">
					<span>题目管理</span>
					<div class="paper_variety">
						<span><a href="toAdd.do">+题目</a></span>
						

						<%-- <c:forEach var="u" items="${user_login }">  --%>
						<c:choose>
							<c:when test="${user_login.user_type== 2}">
								<span><a href="delete_title_log.do"
									>-智能清题</a>
								</span>
							</c:when>

							<c:when test="${user_login.user_type==1}">
								<span><a href="delete_title_log.do"
									>-智能清题</a>
								</span>
							</c:when>

							<c:otherwise>
								<span><a href="#"
									onclick="return window.confirm('用户，您并没有这个权限')">-智能清题</a>
								</span>
							</c:otherwise>
						</c:choose>
						<%--    </c:forEach>    --%>
                  <span><a href="home.jsp">返回首页</a></span>
					</div>

				</div>
				<hr>
				<div class="item_list">
					<form action="major_condition.do" method="post">
						<select name="type">
							<option>题型</option>
							<option value="0">单选题</option>
							<option value="1">多选题</option>
							<option value="2">判断题</option>
							<option value="3">填空题</option>
							<!--  <option value="4">填空题</option> -->
						</select> <select name="major">
							<option>职位</option>
							<c:forEach var="j" items="${major }">
								<option value=${j }>${j }</option>
							</c:forEach>
							<!-- <option value="JAVA工程师">JAVA工程师</option>
                        <option value="数据库管理员">数据库管理员</option>
                        <option value="C语言工程师">C语言工程师</option>
                        <option value="UI设计师">UI设计师</option> -->
						</select> <select name="backgroud" >
							
							<option value=0>背景</option>
							<option value=0>应届毕业生</option>
							<option value=1>1~2年工作经验</option>
							<option value=2>2~4年工作经验</option>
							<option value=3>5年以上工作经验</option>
						</select>

						<!--  <input style="width: 150px;" type="text" id="search" value="关键字："/> -->
						<input type="submit" value="click" class="btn"/>
					</form>

					<hr>
					<table border="1" width="90%">
						<tr>
							<td>题目</td>
							<td>类型</td>
							<td>从属</td>
							<td>背景</td>
							<td>操作</td>
						</tr>
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
								<c:choose>
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
											href="delete.do?id=${t.title_id}"
											onclick="return window.confirm('确认要删除试题吗？')">删</a>
										</td>
									</c:when>

									<c:otherwise>
										<td><a href="#"
											onclick="return window.confirm('用户，您并没有这个权限')"> 改</a>/ <a
											href="#" onclick="return window.confirm('用户，您并没有这个权限')">删</a>
										</td>
									</c:otherwise>
								</c:choose>
						</c:forEach>

					
						<%--   </c:forEach> --%>
					</table>
				</div>
			<br>
				<p>
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
							<a href="mqforpage.do?currPage=${pc.nextPage }">下一页</a>
							<a href="mqforpage.do?currPage=${pc.totalPages }">尾页</a>
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
				<br>
			</div>
		</div>
	</div>




	<div class="footer">
		<div class="bt">@2016 招聘试题定制服务系统</div>
	</div>

</body>
</html>