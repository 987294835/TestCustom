<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%= basePath %>css/login1.css" type="text/css">
<style>
  .form-group .form-label {
    top: 5px;
    </style>
  </head>
  <body>
<script type="text/javascript">
    function IsString()
    {
        var str1 = document.getElementById('name').value.trim();
        var str2 = document.getElementById('pass').value.trim();
        var str3 = document.getElementById('pass_again').value.trim();
        var name = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        var pass =/^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;
        if(name.test(str1) && pass.test(str2)){
           if(str3 == str2){ 
             var myform = document.getElementById("RegisterForm");
		     myform.action = "save_user.do";
		     myform.submit();
           }   
         else{ 
             alert("密码不一致");
        } 
          }  
        else{
            alert(str3);
        }
    }

</script>


<div class="user_login"><h1>招聘试题定制服务系统</h1></div>
<div id="login">
    <div id="mainbody">
        <div class="putin">
             <form name="RegisterForm" id="RegisterForm" method="post">
                <div class="form-group" >
                    <input type="text" required="required" class="form-control" id="name" name="user_name"/>
                    <label class="form-label">用户名</label>
                </div>
                <div class="form-group">
                    <input type="password" required="required" class="form-control" id="pass" name="user_password"/>
                    <label class="form-label">密 码</label>
                </div>
                 <div class="form-group">
                    <input type="password" required="required" class="form-control" id="pass_again" name="pass_again"/>
                    <label class="form-label">确认密码</label>
                </div>
       <div class="form-group"> 
          <!-- <input type="radio"  name="user_type" value="2"/> 试题管理员
          <input type="radio"  name="user_type" value="3" checked="checked"/>普通用户  -->
                    
         <!-- 
         <select name="user_type">
         <option value="2">试题管理员</option>
           <option value="3">普通用户</option> </select>-->
        </div>
                <div class="btu">
                    <input type="button"  onclick="IsString()"  value="注册"> 
                </div>
                <div class="floaa"><span><a href="login.jsp">有账号？登陆</a></span></div>
            </form>
        </div>
    </div>

</div>

</body>
</html>
