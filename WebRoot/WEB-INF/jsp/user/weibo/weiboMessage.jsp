<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${basePath}/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${basePath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${basePath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">var basePath = "${basePath}"</script>
<title>莫邪网-用户绑定页面</title>
<script type="text/javascript">
	function ok(){
  		var action=basePath + "/sian/confirm.action";
		var callback=function(data){
 			if(data=="success"){
 				alert("注册成功！");
 				window.location.href = basePath + "/sian/toweibo.action";	
			}
			if(data=="close"){
				alert("提示：第三方注册已关闭！");
			}
			if(data=="tologin"){
				location.href=basePath + "/user/tologin.action";
			}
		};
		$.post(action,null,callback,'json');
	}
</script>
</head>
<body style="padding: 20px">
	<div class="container">
		<div class="col-md-12 column" align="center">
			<button class="btn btn-default" onclick="ok()">确定注册</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-default" onclick="window.close()">关闭返回</button>
		</div>
	</div>
</body>
</html>