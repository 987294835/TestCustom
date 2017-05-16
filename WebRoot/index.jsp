<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login in</title>

        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/iziModal.css">
		<link rel="stylesheet" href="css/demo.css">
		<link rel="stylesheet" href="css/form-elements.css">
        <link rel="stylesheet" href="css/loginstyle.css">
        <style>
           #modal-employer .iziModal-content section .modol_btn input:not([type="checkbox"]), #modal-employer .iziModal-content section button {
    width: 50%;
}
 
          #modal-custom .iziModal-content section .modol_btn input:not([type="checkbox"]), #modal-custom .iziModal-content section button {
    width: 50%;
}
        </style>
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>招聘试题定制服务系统</strong></h1>
                        </div>
                    </div>
                    <div class="choose">
                        <button type="button" class="btn trigger-employer">招聘者入口</button>
                        <button type="button" class="btn trigger-custom">应聘者入口</button>
                    </div>
                </div>
            </div>
		</div>
		<!--招聘者入口模态窗口-->
          <div id="modal-employer" class="iziModal">
          	 <button data-iziModal-close class="icon-close"></button>
        <header>
            <a href="" id="signin">登&nbsp;录</a>
        </header>
        <section>
        <form id="loginForm" role="form" action="user_login.do" method="post" class="login-form">
        <!--<form id="loginForm" class="form-horizontal"  method="post">-->
            <input type="text" placeholder="账 号：" name="user_name" required="required">
            <input type="password" placeholder="密 码：" name="user_password" required="required">
            <footer   class="modol_btn" >
                <button class="button">忘记密码</button>
                <input type="submit" value="登录"> 
            </footer>
        </form>
        </section>
    </div>
		<!--应聘者模态窗口-->
         <div id="modal-custom" class="iziModal">
        <button data-iziModal-close class="icon-close"></button>
        <header>
            <a href="" id="signin">登&nbsp;录</a>
            <a href="" class="active">注&nbsp;册</a>
        </header>
        <section class="hide">
        <form id="person_login" class="form-horizontal" action="person_login.do" method="post">
            <input type="text" placeholder="注册邮箱：" name="person_email" required="required">
            <input type="password" placeholder="密 码：" name="person_password" required="required">
            <footer class="modol_btn">
                <button data-iziModal-close>取消</button>
                <input type="submit" value="登录">          
            </footer>
        </form>
        </section>
        <section>
        <form id="register" class="form-horizontal" action="add_person.do" method="post">
            <input type="text" placeholder="请输入姓名" name="person_name" required="required">
            <input type="email" placeholder="请输入邮箱" name="person_email" required="required">
            <input type="text" placeholder="请输入联系方式：" name="person_phone" required="required">
            <input type="password" placeholder="请输入密码" name="person_password" required="required">
            <input type="password" placeholder="请确认密码" name="check_person_password" required="required">
            
            <!-- <label for="check"><input type="checkbox" name="checkbox" id="check" value="1"> I agree to the <u>Terms</u>.</label> -->
            <footer class="modol_btn">
                <button data-iziModal-close>取消</button>
                <input type="submit" value="注册" id="register">    
            </footer>
        </form>
        </section>
    </div>

        <!-- Javascript -->
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.backstretch.min.js"></script>
        <script src="js/iziModal.min.js" type="text/javascript"></script>
        <script src="js/scripts.js"></script>
        
        <script type="text/javascript">

        $("#modal-employer").iziModal({
	        overlayClose: false,
	        width: 600,
	        autoOpen: false,
	        overlayColor: 'rgba(0, 0, 0, 0.6)',
	        onOpened: function() {
	            console.log('onOpened');
	        },
	        onClosed: function() {
	            console.log('onClosed');  
	        }
	    });
	    $(document).on('click', '.trigger-employer', function (event) {
	        event.preventDefault();
	        $('#modal-employer').iziModal('open');
	         $("#modal-employer .iziModal-content .icon-close").css('background', '#ddd');
	    });
        $("#modal-employer").on('click', '.submit', function(event) {
	        event.preventDefault();
	        var myform = document.getElementById("loginForm");
	        myform.action = "user_login.do";
		    myform.submit();
	    });
	    $("#modal-employer").on('click', '.button', function(event) {
	        event.preventDefault();
	        window.location.href='sengEmail.jsp';
	    });



	    $("#modal-custom").iziModal({
	        overlayClose: false,
	        width: 600,
	        autoOpen: false,
	        overlayColor: 'rgba(0, 0, 0, 0.6)',
	        onOpened: function() {
	            console.log('onOpened');
	        },
	        onClosed: function() {
	            console.log('onClosed');  
	        }
	    });
	    $(document).on('click', '.trigger-custom', function (event) {
	        event.preventDefault();
	        $('#modal-custom').iziModal('open');
	    });
	    $("#modal-custom").on('click', 'header a', function(event) {
	        event.preventDefault();
	        var index = $(this).index();
	        $(this).addClass('active').siblings('a').removeClass('active');
	        $(this).parents("div").find("section").eq(index).removeClass('hide').siblings('section').addClass('hide');

	        if( $(this).index() === 0 ){
	            $("#modal-custom .iziModal-content .icon-close").css('background', '#ddd');
	        } else {
	            $("#modal-custom .iziModal-content .icon-close").attr('style', '');
	        }
	    });
	    $("#modal-custom").on('click', '.submit', function(event) {
	        event.preventDefault();
	        var myform = document.getElementById("person_login");
	        myform.action = "person_login.do";
		    myform.submit();
	        $('#modal-custom').iziModal('close');
	       /* var fx = "wobble",  //wobble shake
	            $modal = $(this).closest('.iziModal');
            
	        if( !$modal.hasClass(fx) ){
	            $modal.addClass(fx);
	            setTimeout(function(){
	                $modal.removeClass(fx);
	            }, 1500);
	        }*/
	    });
	    $("#modal-custom").on('click', 'register', function(event) {
	        event.preventDefault();
	        var myform = document.getElementById("register");
	        myform.action = "add_person.do";
		    myform.submit();
	        $('#modal-custom').iziModal('close');
	    });
	</script>
    </body>

</html>