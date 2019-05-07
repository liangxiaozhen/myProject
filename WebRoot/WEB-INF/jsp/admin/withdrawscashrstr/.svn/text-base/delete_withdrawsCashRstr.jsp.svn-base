<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
		<div style="margin-left: 180px;">
		<input type="hidden" id="del-id" value="${wdcRstr.id }">
		会 员 等 级：<span style="color: red;"><label>${wdcRstr.ugradeStr}</label></span><br>
		金 额 区 间：<span style="color: red;"><label>${wdcRstr.lowestmoney }</label></span> ~ <span
			style="color: red;"><label>${wdcRstr.highestmoney }</label></span> 元<br>
		日提现金额：<span style="color: red;"><label>${wdcRstr.daymoneyrest }
					</label></span> 元<br>
		日提现次数：<span style="color: red;"><label>${wdcRstr.daytimesrest}</label></span>  次
	</div>
</body>
</html>