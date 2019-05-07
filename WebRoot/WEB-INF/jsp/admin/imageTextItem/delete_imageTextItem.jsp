<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<div align="center">
		是否删除项目名称 <span style="color:red;"><label>${imageTextItem.itiname }</label></span> 的设置？ <br>
		<span style="color:red;"><label>图文内容</label></span>中项目名称为<span style="color:red;"><label>${imageTextItem.itiname }</label></span>的设置会一并删除!
		<input name="id" type="hidden" id="del-id" value="${imageTextItem.id }">
	</div>
</body>
</html>