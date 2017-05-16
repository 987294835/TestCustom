<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>招聘试题定制服务系统</title>
    <link rel="stylesheet" href="<%= basePath %>css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%= basePath %>css/default.css" type="text/css">
    <link rel="stylesheet" href="<%= basePath %>css/index.css" type="text/css">
    <style>
      a:hover{font-weight:bold;}
 
    </style>
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
          <div class="page-header1">
          </div>
          <div class="main">
              <div class="main_left">
                  <ul class="menu">
                  <li><a href="p_center.jsp">个人中心</a></li>
                  <li><a href="Show_All.do">试题管理</a></li>
                  <li><a href="select_Major.do">试题定制</a></li>
                  <li><a href="showcart.jsp">试题筐</a></li>
                  <li><a href="Select_exam.do">历史记录</a></li>
                  <li><a href="add_job.jsp">添加岗位</a></li>
              </ul></div>

              <div class="main_right">
                  <h1>Welcome !</h1><hr>
                  <div class="brief"><br>
				   <span>试题管理：抓住日常工作的灵感和经验，编制高质量的试题，减少了时间地点的限制，丰富试题库，提高了最终定制试题的效率</span>
                    <br>
					<span>试题定制：灵活根据需要，有机选择一个至多个条件，找到匹配的试题，科学定制试卷，提高试题定制效率。</span>
                    <br>
					<span>试题筐：就像一个购物筐，HR面试官在这里结合自己的经验和喜好，再对已选的试题进行筛选，保存下载试卷。</span>
                    <br>
					<span>历史记录：方便的查看已下载的的试卷，有助于HR总结出卷经验，总结思路，提高工作效率。</span>
					</div>
				<br>
               
              </div>
          </div>
      </div>
      <div class="footer">
          <div class="bt">@2016 招聘试题定制服务系统 </div>
          </div>

</body>
</html>