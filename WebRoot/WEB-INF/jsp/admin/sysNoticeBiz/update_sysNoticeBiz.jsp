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
<script type="text/javascript">
	$(function() {
		$("#update-smscid-select").val("${sysNoticeBiz.smscid }");
		$("#update-snlid-select").val("${sysNoticeBiz.snlid }");
	});
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>系统通知业务编辑</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				<input type="hidden" name="id" value="${sysNoticeBiz.id }">
				业务名称：<label>${sysNoticeBiz.bizname }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				短信号码：<select id="update-smscid-select" name="smscid">
					<option value="">---请选择---</option>
					<c:forEach items="${list }" var="item">
						<option value="${item.id }"><c:if
								test="${empty item.key }">默认----${item.smsccompany }</c:if>
							<c:if test="${!empty item.key }">${item.key }----${item.smsccompany }</c:if></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				定向名单：<select id="update-snlid-select" name="snlid">
					<option value="">---请选择---</option>
					<c:forEach items="${specialNameList }" var="item">
						<option value="${item.id }">${item.businessName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="update-remark-text">${sysNoticeBiz.remark}</textarea>
			</div>
		</div>
	</form>
</body>
</html>