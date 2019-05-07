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
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			订单编号：<font color="red"><label id="auditcashnolb">${uwc.cashno }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			银行名称：<font color="red"><label>${uwc.bankname }</label></font>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			开户名称：<font color="red"><label>${uwc.ubai.realname }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			银行卡号：<font color="red"><label>${uwc.cardno }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			用 户 名 ：<font color="red"><label>${uwc.ubai.loginname }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			取现金额：<font color="red"><label>${df.format(uwc.amount) }</label></font>
		</div>
		<hr>
	</div>
	<div class="form-group">
		<label for="message-text" class="control-label">备注:</label>
		<textarea rows="2" data-toggle="popover" data-placement="bottom"
			data-trigger="fous" data-content="注：审核时，请填写备注。" class="form-control"
			id="auditremark-text">${uwc.remark }</textarea>
	</div>
</body>
</html>