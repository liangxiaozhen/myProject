<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开户成功页面</title>
<script type="text/javascript">var basePath = "${basePath}"</script>
</head>
<body>
	您好！开户成功 ! 如有问题请联系客服
	<span id="timer"></span>
	<a href="${basePath}/user/tologin.action">返回首页</a>
	<script type="text/javascript">
   		 timer(5);
   		 var timerParams = null;
   		 function timer(timer){
 	   		 var timerDom = document.getElementById("timer");
 	   		timerParams = setInterval(function(){
   				 if(timer <= 0){
   					 window.location.href=basePath+"/user/tologin.action";
    			 }else{
    				 timerDom.innerHTML = (timer--)+"秒后";
    			 }
   			 }, 1000);
   		 }
   	</script>
</body>
</html>