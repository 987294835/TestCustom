<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>参加该job的应聘人情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="css/tcs-style.css" rel="stylesheet">
		<link href="css/left-column.css" rel="stylesheet">
<style>
body{
    font-family: "Microsoft YaHei",微软雅黑;
    width:70%;
    margin: 10px auto;
    background: #fdfdfd;
   }
  a{ text-decoration: none;
 color: #0d96ce;}
  a:hover{color:gray;}
  .bg{  background: #f6f6f6;}
  td{border-color:#f6f6f6;}
</style>
  </head>
  
  <body>
  <div class="tcs-content-container">
				<div class="tcs-content-widget ">
					<div class="table-responsive">
						<table class="table">
							<tbody>
								<tr class="bg">
								    <td style="width: 18%;"></td>
									<td class="text-left" style="width: 30%;">应聘者</td>
									<td style="width: 25%;">考试情况</td>
								</tr>
								<c:forEach var="c" items="${job_persons}">
    	                         <tr style="line-height:28px;">
    	                            <td><div class="circle green-bg"></div></td>
    	                            <td><a href="see_person.do?person_id=${c.person_id}">${c.person_name}</a>&nbsp;&nbsp;</td>
    	                            <td><c:if test="${c.person_state == 0 }">
    			<span>还没参加考试</span>
    		</c:if>
    		<c:if test="${c.person_state >0 }">
    			<span>${c.person_score } 分</span>
    		</c:if></td>
    	                         </tr>
    	
    		<br>
    	
    	</c:forEach>
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


						<div class="btn_right"></div>
					</div>
				</div>
			</div>
     
  </body>
</html>
