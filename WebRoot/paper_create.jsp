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

<head>
<meta charset="UTF-8">
<title>招聘试题定制服务--生成试卷</title>
<!-- IE浏览器运行最新的渲染模式，建议将此 <meta> 标签加入到你的页面中 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 方式一:确保适当的绘制和触屏缩放 -->
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- Bootstrap核心css -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/tcs-style.css" rel="stylesheet">
<link href="css/left-column.css" rel="stylesheet">
<link href="css/main-content.css" rel="stylesheet" />
<link href="css/tests_manage.css" rel="stylesheet" />
<link href="css/media.css" rel="stylesheet" />
<link href="css/upTop.css" rel="stylesheet"/>
<script src="js/modernizr.js"></script> <!-- Modernizr -->
<style>
body { /*overflow-y: hidden;*/
}
.margin-top-10{margin-top:10px;}

.fa-heart-o:before {
	content: "\f08a";
}
</style>
</head>

<body>
	<div class="tcs-flex-row">
		<!--left sidebar-->
		<div class="tcs-sidebar">
			<header class="tcs-site-header">
				<h1 style="line-height:28px;">Recruitment CS</h1>
			</header>
			<div class="profile-photo-container">
				<img src="img/profile-photo.jpg" alt="Profile Photo"
					class="img-responsive">
				<div class="profile-photo-overlay"></div>
			</div>
			<div class="mobile-menu-icon">
				<i class="fa fa-bars"></i>
			</div>
			<div class="tcs-left-nav">
				<ul>
					<li><a href="home.jsp" ><i
							class="fa fa-home fa-fw"></i>首 页</a>
					</li>
					<li><a href="Show_All.do"><i class="fa fa-database fa-fw"></i>题库</a>
					</li>
					<li><a href="select_Major.do" class="active"><i class="fa fa-gears fa-fw"></i>试题定制</a>
					</li>
					<li><a href="addPosition.jsp"><i class="fa fa-gears fa-fw"></i>添加岗位</a>
					</li>
					<li><a href="shopCart.jsp"><i
							class="fa fa-cart-plus fa-fw"></i>试题筐</a>
					</li>
					<li><a href="Select_exam.do"><i
							class="fa fa-folder-open fa-fw"></i>历史记录</a>
					</li>


				</ul>
			</div>
		</div>
		<!--Main content-->
		<div class="tcs-content col-1 light-gray-bg">
			<!--content nav-->
			<div class="tcs-top-nav-container">
				<div class="row line-height-50">
					<nav class="tcs-top-nav col-lg-12 col-md-12">
						<ul>
							<li><a href="other_Choice.do" class="active margin-right-25">其他方案</a>
							</li>
							<li><a href="paper_made.jsp" class="active margin-right-25">重新定制</a>
							</li>
						</ul>
						<div class="nav-img-personal nav_img">
							<div class="profile-person-overlay"></div>
						</div>
					</nav>

				</div>
			</div>
			<!--content-->
			<div class="tcs-content-container">
				<div class="tcs-content-widget white-bg">
					<div class=""></div>
					<div>
					
						<form role="form" action="add_title_to_cart.do" method="post">
							<div class="form-group">
								<h2>推荐方案</h2>
								<hr>
								<div class="checkbox">
									<c:forEach var="c" items="${customTitles_choice }" varStatus="loop">
											<label class="padding-bottom-5  col_id"> <input type="checkbox" value="${c.title_id }" name="choice_ids">(选择题${c.title_id })
											${c.title_text } </label>
											<a href="see_information.do?title_id=${c.title_id }">查看</a>
											<a id="${c.title_id }" class="dan margin-left-10 fa fa-heart-o fa-fw tcs-link"  name="${isColl[loop.count-1]}">
											</a>
											<div class="margin-left-10">
												<p>A、${c.title_option_a }</p>
												<p>B、${c.title_option_b }</p>
												<p>C、${c.title_option_c }</p>
												<p>D、${c.title_option_d }</p>
										</div>
									</c:forEach>
								</div>
								<div class="checkbox">
									<c:forEach var="c" items="${customTitles_mutilchoice }">
										<label class="padding-bottom-5"> <input type="checkbox" value="${c.title_id }" name="choice_ids">(多选题${c.title_id })
										${c.title_text } </label>
										
										<a href="see_information.do?title_id=${c.title_id }">查看</a>
											<a id="${c.title_id }" class="dan margin-left-10 fa fa-heart-o fa-fw tcs-link"  name="${isColl[loop.count-1]}">
											</a>
										<div class="margin-left-10">
											<p>A、${c.title_option_a }</p>
											<p>B、${c.title_option_b }</p>
											<p>C、${c.title_option_c }</p>
											<p>D、${c.title_option_d }</p>
										</div>
									</c:forEach>
								</div>
								<div class="checkbox">
									<c:forEach var="c" items="${customTitles_blank }">
										<label class="padding-bottom-5"> <input type="checkbox" value="${c.title_id }" name="choice_ids">(判断题${c.title_id })
										${c.title_text } </label>
										<a href="see_information.do?title_id=${c.title_id }">查看</a>
											<a id="${c.title_id }" class="dan margin-left-10 fa fa-heart-o fa-fw tcs-link"  name="${isColl[loop.count-1]}">
											</a>
										<div class="margin-left-10">
											
										</div>
									</c:forEach>
								</div>
								<div class="checkbox">
									<c:forEach var="c" items="${customTitles_quiz }">
										<label class="padding-bottom-5"> <input type="checkbox" value="${c.title_id }" name="choice_ids">(填空题${c.title_id })
										${c.title_text } </label>
										<a href="see_information.do?title_id=${c.title_id }">查看</a>
											<a id="${c.title_id }" class="dan margin-left-10 fa fa-heart-o fa-fw tcs-link"  name="${isColl[loop.count-1]}">
											</a>
										<div class="margin-left-10">
										
										</div>
									</c:forEach>
								</div>
                    
 
								<!-- <button type="button" class="btn btn-info">添加到试题筐</button> -->
								<input type="submit" value="添加到试题筐" class="btn btn-info margin-top-10">
							</div> 
						</form>
					</div>
				</div>
			</div>
			<footer class="text-right">
						<p>Copyright &copy; 2016 招聘试题定制服务</p>
					</footer>
		</div>
	</div>
		<a href="#0" class="cd-top" style=" color: #e86256;
  text-decoration: none;">Top</a>
	<!--Js-->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script src="js/upTop.js"></script> <!-- Gem jQuery -->
	<!--收藏的效果-->
	<script type="text/javascript">
		$(function() {
		   $('.dan').each(function(){
		   var i=$(this).attr("name");
		    if(i==1){
		       $(this).css("color", "red");
		    }
		   });
		$(".dan").click(function(){
		    $(this).css("color", "red");
		    var id=$(this).attr("id");
		   
		    var url='Collect.do';
		    $.post(url,{
		    		choice_id:id
		    	},function(data){
		    		if(data=="success"){
		    		   alert("收藏成功");
		    		}else{
		    		  alert("你已收藏，收藏失败");
		    		}
		    	}
		    );
		    
		});
    });  
    
	</script>
</body>

</html>