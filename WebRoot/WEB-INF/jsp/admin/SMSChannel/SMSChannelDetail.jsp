<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>短信通道设置详情</b></font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			登 录 名 ：&nbsp;&nbsp;<label><c:if
					test="${!empty smsChannel.pusername }">${smsChannel.pusername }</c:if>
					<c:if
					test="${empty smsChannel.pusername }">无</c:if></label>
		</div>
		<div class="col-md-6">
			登录密码：&nbsp;&nbsp;<label><c:if
					test="${!empty smsChannel.ppassword }">${smsChannel.ppassword }</c:if>
					<c:if
					test="${empty smsChannel.ppassword }">无</c:if></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			扩 展 号 ：&nbsp;&nbsp;<label><c:if
					test="${!empty smsChannel.extno }">${smsChannel.extno }</c:if>
					<c:if
					test="${empty smsChannel.extno }">无</c:if></label>
		</div>
		 <!--  
			<div class="col-md-5 ">
			标识信息：&nbsp;&nbsp;<label><c:if
					test="${!empty smsChannel.action }">${smsChannel.action }</c:if>
					<c:if
					test="${empty smsChannel.action }">无</c:if></label>
		</div>-->
		
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			设 置 人 ：&nbsp;&nbsp;<label>${smsChannel.addman }</label>
		</div>
		<div class="col-md-6">
			设置时间：&nbsp;&nbsp;<label><fmt:formatDate value="${smsChannel.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-11 col-md-offset-1">
			通道URL ：&nbsp;&nbsp;<label>${smsChannel.smsurl }</label>
		</div>
	</div>
</body>
</html>