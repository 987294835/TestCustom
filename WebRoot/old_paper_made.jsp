<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>招聘试题定制服务系统_试题定制</title>
<link rel="stylesheet" href="<%= basePath %>css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="<%= basePath %>css/default.css"
	type="text/css">
<link rel="stylesheet" href="<%= basePath %>css/index.css"
	type="text/css">
<link rel="stylesheet" href="<%= basePath %>css/item.css"
	type="text/css">
</head>
<body>
<script type="text/javascript">
    function checkText()
    {
        var str1 = document.getElementById('choice_num').value.trim();
        var str2 = document.getElementById('mutilchoice_num').value.trim();
        var str3 = document.getElementById('blank_num').value.trim();
        var str4 = document.getElementById('quiz_num').value.trim();
        var corect = /^(0|([1-9]\d*))$/;
        if(corect.test(str1) && corect.test(str2) && corect.test(str3) && corect.test(str4)){
           var myform = document.getElementById("myForm");
		   myform.action = "query_By_Condition.do";
		   myform.submit();
        }
        else{
            alert("请输入大于等于0的整数");
        }
    }

</script>

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
					<li><a href="Show_All.do">试题管理</a></li>
					 <li><a href="Select_exam.do">历史记录</a></li>
				</ul>
			</div>

			<div class="main_right">
				<div class="made">
					<h3 class="paper_made">试题定制</h3>
				</div>
				<hr>
				<div class="paper_list">
					<form class="paper_right" id="myForm"
						method="post">
						<table>
							<tr>
								<td class="td_right">条件选择：</td>
								<td><select name="major" id="major">
										<option value="">职位</option>
										<c:forEach var="j" items="${major }">
											<option value=${j }>${j }</option>
										</c:forEach>
								</select></td>
								<td><select name="backgroud" id="backgroud">
										<option value="-1">背景</option>
										<option value=0>实习生</option>
										<!-- <option value=0>应届毕业生</option> -->
										<option value=1>1~2年工作经验</option>
										<option value=2>2~4年工作经验</option>
										<option value=3>5年以上工作经验</option>
								</select></td>
							</tr>
							<!--   <tr><td class="td_right">考试名称：</td>
                        <td colspan="3"><input type="text" id="paper_name"></td></tr><br> -->
							<tr>
								<td class="td_right">单选题：</td>
								<td colspan="2">共<input type="number" id="choice_num" name="choice_num"
									class="number_style" value="0">题 &nbsp;&nbsp;<!-- 每道<input type="number" id="single_goal" class="number_style" class="number_style">分</td> -->
									<!--       <td> 难度系数：<input type="radio"name="level" value="易">易 &nbsp;
                                          <input type="radio"name="level" value="中">中 &nbsp;
                                          <input type="radio"name="level" value="难">难</td></tr> -->
							<tr>
								<td class="td_right">多选题：</td>
								<td colspan="2">共<input type="number" id="mutilchoice_num" name="mutilchoice_num"
									class="number_style" value="0">题 &nbsp;&nbsp;<!-- 每道<input type="number" id="gap_goal" class="number_style">分</td> -->
									<!--   <td> 难度系数：<input type="radio"name="level" value="易">易 &nbsp;
                                <input type="radio"name="level" value="中">中 &nbsp;
                                <input type="radio"name="level" value="难">难</td></tr> -->
							<tr>
								<td class="td_right">判断题：</td>
								<td colspan="2">共<input type="number" id="blank_num" name="blank_num"
									class="number_style" value="0">题 &nbsp;&nbsp;<!-- 每道<input type="number" id="judgement_goal" class="number_style">分</td> -->
									<!--    <td> 难度系数：<input type="radio"name="level" value="易">易 &nbsp;
                                <input type="radio"name="level" value="中">中 &nbsp;
                                <input type="radio"name="level" value="难">难</td></tr> -->
							<tr>
								<td class="td_right">填空题：</td>
								<td colspan="2">共<input type="number" id="quiz_num" name="quiz_num"
									class="number_style" value="0">题 &nbsp;&nbsp;<!-- 每道<input type="number" id="question_goal" class="number_style">分</td> -->
									<!--     <td> 难度系数：<input type="radio"name="level" value="易">易 &nbsp;
                                <input type="radio"name="level" value="中">中 &nbsp;
                                <input type="radio"name="level" value="难">难</td></tr> -->
							<tr>
								<td class="td_left" colspan="4"><input class="btn" type="button" onclick="checkText()"
									value="生成试题" id="item_save"></td>
							</tr>
						</table>
					</form>

				</div>
				<div class="item_btn">
					<!--<input type="button" value="生成试题"  id="item_save" class="btn">-->
				</div>
				<br>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="bt">@2016 招聘试题定制服务系统</div>
	</div>

</body>
</html>