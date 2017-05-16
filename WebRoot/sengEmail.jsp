<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sengEmail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/findPassword.css">
    <style type="text/css">
     .int{  text-align: left; }
     .high{ color:#ff4900; margin-left:10px;}
     .msg{ font-size: 13px;  margin:2px;}
     .onError{ color:#ff4900; display: block;margin-bottom:10px;}
     .onSuccess{ color:#27AE60;font-weight:bold}
    </style>
  </head>
  
  <body>
  <div>
     <header class="htmleaf-header">
		<h1 style="margin-top:5%">找回密码</h1>
	</header>
	<div class="verify">
      <form action="send_Email.do" method="post" id="msform">
      <div class="int"><input type="text" name="user_name" id="user_name" placeholder=" 请输入用户名:" class="required"></div>
      <div class="int">	<input type="text" name="e_address" id="e_address" placeholder=" 请输入邮箱:" class="required"></div>
      <div class="int"><input type="submit" id="send" value="获取验证码" style="background-color:#27AE60;color:white;"></div>
    	
      </form>
      </div>
   </div>
   
   <script src="js/jquery-2.1.4.min.js" type="text/javascript"></script>
   <script src="js/jquery.easing.min.js" type="text/javascript"></script>
   <script type="text/javascript">
    $(document).ready(function(){
        //为表单的必填文本框添加提示信息（选择form中的所有后代input元素）
        $("form :input.required").each(function(){ 
            //创建元素
            var $required = $("<strong class='high'>*</strong>");
            //将它追加到文档中
            $(this).parent().append($required);
        });
        
        //为表单的必填文本框添加相关事件（blur、focus、keyup）
        $("form :input").blur(function(){
            //注意：这里的this是DOM对象，$(this)才是jQuery对象
            var $parent = $(this).parent();
            //删除之前的错误提醒信息
            $parent.find(".msg").remove();
            //验证“名称”
            if($(this).is("#user_name")){
                //运用jQuery中的$.trim()方法，去掉首位空格
                if($.trim(this.value) == "" || $.trim(this.value).length < 3){
                    var errorMsg = " 请输入至少3位的名称！";
                    //class='msg onError' 中间的空格是层叠样式的格式
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>"); 
                }
                else{
                    var okMsg=" √ ";
                    $parent.find(".high").remove();
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }                
            }
            //验证邮箱
            if($(this).is("#e_address")){
                if($.trim(this.value) == "" || ($.trim(this.value) != "" && !/.+@.+\.[a-zA-Z]{2,4}$/.test($.trim(this.value)))){
                    var errorMsg = "请输入正确的E-Mail地址！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                }
                else{
                    var okMsg=" √ ";
                    $parent.find(".high").remove();
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
        }).keyup(function(){
            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
            $(this).triggerHandler("blur");
        }).focus(function(){
            $(this).triggerHandler("blur");
        });
        
        //点击发送按钮
        $("#send").click(function(){
            //trigger 事件执行完后，浏览器会为submit按钮获得焦点
            $("form .required:input").trigger("blur"); 
            var numError = $("form .onError").length;
            if(numError){
                return false;
            }
        });
    });
    </script>
  </body>
</html>
