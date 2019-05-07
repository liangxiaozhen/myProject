<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			<input type="hidden" id="cashNos" value="${cashNos }"> 总笔数 ：<font
				color="red"><label>${count}</label></font> 笔
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			总金额 ：<font color="red"><label>${df.format(totalMoney) }</label></font>
			元
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			总手续费：<font color="red"><label>${df.format(totalFee) }</label></font>
			元
		</div>
		<hr>
	</div>
	<div class="form-group">
		<label for="message-text" class="control-label">备注:</label>
		<textarea rows="2" data-toggle="popover" data-placement="bottom"
			data-trigger="fous" data-content="注：审核未通过时，请填写备注。"
			class="form-control" id="batchAudit-remark"></textarea>
	</div>
</body>
</html>