<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div style="margin-left: 150px;">
		<input type="hidden" value="${sTime.id}" id="del-sTime-id"> <label>时间包类型：<font
			color="red">${sTime.timetype}</font></label><br> <label>时间包名称：<font
			color="red">${sTime.timename}</font></label><br> <label>优先级： <font
			color="red"> <c:if test="${empty sTime.wlevel }">默认</c:if> <c:if
					test="${!empty sTime.wlevel }">${sTime.wlevel}</c:if>
		</font></label><br> <label>提现金额：<font color="red">${sTime.minmoney}</font></label>
		- <label><font color="red">${sTime.maxmoney}</font></label><br> <label>开始时间：
			<font color="red">${sTime.btimeStr}</font>
		</label><br> <label>结束时间： <font color="red">${sTime.etimeStr}</font></label><br>

	</div>
</body>
</html>