<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<meta charset="utf-8">
<head>
<title>倒计时</title>
</head>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script>
	javascript:window.history.forward(1);  
                $(function(){
                        var timerVal = $("#timer").val();
                        setTimeout("form1.submit()",timerVal*1000);
                        var i = setInterval(function() {
                                timerVal--;
                                $("#timer").val(timerVal);                    
                                if (timerVal < 1)
                                clearInterval(i);
                        }, 1000);
                });
                
                
        </script>
<body>
	<form action="next_job_exam_title.do" name="form1" method="post">
		距离考试结束还有<input id="timer" value="${time }" name="timer" style="border-style: none;"/>秒<br>

		<c:forEach var="c" items="${JobExamTitle }">
			<c:if test="${c.title_type == 0 }"><!--单选题  -->
				<span>${pc.currentPage }、</span>${c.title_text}<br>
				<input type="hidden" name="title_id" value="${c.title_id }">
				<input type="radio" name="answer" value="A">A、${c.title_option_a}<br>
				<input type="radio" name="answer" value="B">B、${c.title_option_b}<br>
				<input type="radio" name="answer" value="C">C、${c.title_option_c}<br>
				<input type="radio" name="answer" value="D">D、${c.title_option_d}<br>
			</c:if>	
			<c:if test="${c.title_type == 1 }"><!-- 多选题 -->
				<span>${pc.currentPage }、</span>${c.title_text}<br>
				<input type="hidden" name="title_id" value="${c.title_id }">
				<input type="checkbox" name="mutil_answer" value="A">A、${c.title_option_a}<br>
				<input type="checkbox" name="mutil_answer" value="B">B、${c.title_option_b}<br>
				<input type="checkbox" name="mutil_answer" value="C">C、${c.title_option_c}<br>
				<input type="checkbox" name="mutil_answer" value="D">D、${c.title_option_d}<br>
			</c:if>	
			<c:if test="${c.title_type == 2 }"><!-- 判断题 -->
				<span>${pc.currentPage }、</span>${c.title_text}<br>
				<input type="hidden" name="title_id" value="${c.title_id }">
				<input type="radio" name="judge_answer" value="T">T<br>
				<input type="radio" name="Judge_answer" value="F">F<br>
			</c:if>	
			<c:if test="${c.title_type == 3 }"><!-- 填空题 -->
				<span>${pc.currentPage }、</span>${c.title_text}<br>
				<input type="hidden" name="title_id" value="${c.title_id }">
				<input type="text" name="blank_answer" ><br>
			</c:if>	
		</c:forEach>
		<c:choose>
			<c:when test="${pc.hasNext }">
				<input type="submit" value="下一题">
			</c:when>
			<c:otherwise>
				<input type="submit" value="完成试卷">
			</c:otherwise>
		</c:choose>	
	</form>


</body>

</html>