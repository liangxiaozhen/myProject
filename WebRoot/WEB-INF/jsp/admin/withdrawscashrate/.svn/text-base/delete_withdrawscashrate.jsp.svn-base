<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div style="margin-left: 180px;">
		<input type="hidden" id="del-id" value="${wdcRate.id }">
		会员等级：<span style="color: red;"><label>${wdcRate.ugradeStr}</label></span><br>
		金额区间：<span style="color: red;"><label>${wdcRate.minmoney }</label></span> ~ <span
			style="color: red;"><label>${wdcRate.maxmoney }</label></span> 元<br>
		收费类型：<span style="color: red;"><label><c:if
					test="${wdcRate.wdcmode eq 1 }">定额</c:if> <c:if
					test="${wdcRate.wdcmode eq 2 }">比例</c:if></label></span> <br>
		<c:if test="${wdcRate.wdcmode eq 1 }">
		定额费用：<span style="color: red;"><label>${wdcRate.quota}</label></span>元
		</c:if>
		<c:if test="${wdcRate.wdcmode eq 2 }">
		提现费率：<span style="color: red;"><label>${wdcRate.wdcrate}</label></span><br>
		手  续  费 ：<span style="color: red;"><label>${wdcRate.minfee}</label></span> ~ <span
				style="color: red;"><label>${wdcRate.maxfee}</label></span>元
		</c:if>
	</div>
</body>
</html>