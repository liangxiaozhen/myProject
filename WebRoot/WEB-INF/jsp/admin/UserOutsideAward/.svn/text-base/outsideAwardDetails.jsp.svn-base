<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">ID：</font>
		<font>${award.id }</font>
	</div>
	<hr> --%>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">用户名：</font> <font>${award.userBaseAccountInfo.loginname }</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">奖品编号：</font> <font>${award.uoawardno }</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">奖品名称：</font> <font>${award.uoawardname }</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">奖品来源：</font> <font>${uoatype}</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">发放时间：</font>
		<fmt:formatDate value="${award.uoatime}" type="date"
			pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">发放状态：</font> <font>${status}</font>

	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">发放编号/快递编号：</font> <font>${award.sendno }</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">是否使用：</font> <font> <c:choose>
				<c:when test="${!empty isuse}">
					${isuse}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">是否审核：</font> <font>${isaudit}</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">审核人：</font> <font>${award.auditman }</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">审核时间：</font> <font><fmt:formatDate
				value="${award.audittime }" type="date"
				pattern="yyyy-MM-dd HH:mm:ss" /></font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-4 text-right">备注：</font> <font>${award.remark }</font>
	</div>
</body>
</html>