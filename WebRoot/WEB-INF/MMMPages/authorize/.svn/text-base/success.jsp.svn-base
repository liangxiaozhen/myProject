<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授权成功页面</title>
<script type="text/javascript">var basePath = "${basePath}"</script>
</head>
<body>
	<c:if test="${empty Message}">
		您好！授权操作成功 ! 如有问题请联系客服
 	</c:if>
 	
 	<c:if test="${not empty Message}">
		您好！${Message}! 如有问题请联系客服
 	</c:if>
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