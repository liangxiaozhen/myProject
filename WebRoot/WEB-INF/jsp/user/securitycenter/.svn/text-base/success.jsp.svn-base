<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示页面</title>
<script type="text/javascript">var basePath = "${basePath}"</script>
<script type="text/javascript"
	src="${basePath}/jquery/1.11.3/jquery-1.11.3.js"></script>
<style>
* {
	padding: 0;
	margin: 0;
}

.banner {
	width: 100%;
	height: 100%;
}

.banner .box {
	width: 280px;
	height: 200px;
	margin: 200px auto;
}
</style>
</head>
<body>
	<div class="banner">
		<div class="box">
			<h2>邮箱设置成功</h2>
			<span id="box1"></span><a href="${basePath}/user/tologin.action">返回首页</a>
		</div>
	</div>
	<script type="text/javascript">
 	 $(function(){
 		timer(5);
 	 });
 var timers = null
 function timer(time){
	 timers = setInterval(function(){
 		 if(time <= 0){
 			 window.location.href=basePath+"/user/tologin.action";
		 }else{
 			 $("#box1").text((time--)+"秒后");
 		 }
	 },1000);
 };
 </script>
</body>
</html>