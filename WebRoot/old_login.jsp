<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>登陆页</title>
    <link rel="stylesheet" href="<%= basePath %>css/login1.css" type="text/css">
</head>
<body>
<script type="text/javascript">
    function IsString()
    {
        var str1 = document.getElementById('name').value.trim();
        var str2 = document.getElementById('pass').value.trim();
        var name = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
        var pass =/^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;
        if(name.test(str1) && pass.test(str2)){
           var myform = document.getElementById("loginForm");
		   myform.action = "user_login.do";
		   myform.submit();
        }
        else{
            alert("请输入正确的用户名和密码");
        }
    }

</script>


<div class="user_login"><h1>招聘试题定制服务系统</h1></div>
<div id="login">
    <div id="mainbody">
        <div class="putin">
             <form name="loginForm" id="loginForm" method="post">
                <div class="form-group" >
                    <input type="text" required="required" class="form-control" id="name" name="user_name"/>
                    <label class="form-label">用户名</label>
                </div><br>
                <div class="form-group">
                    <input type="password" required="required" class="form-control" id="pass" name="user_password"/>
                    <label class="form-label">密 码</label>
                </div> <br>

               <div class="btu">
              
                    <input type="button"  onclick="IsString()"  value="登  录"> 
                </div>
                <a href="sengEmail.jsp">忘记密码</a>
                <div class="floaa"><span><a href="register.jsp">没账号？注册</a></span></div>
            </form>
        </div>
    </div>

</div>

</body>
</html>