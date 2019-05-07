<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">姓名：</label><label class="col-md-4">${carddetail.username}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">银行：</label><label class="col-md-4">${carddetail.bankname}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">账户：</label><label class="col-md-4">${carddetail.cardno}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">开户行省：</label><label
			class="col-md-4">${carddetail.province}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">开户行市：</label><label
			class="col-md-4">${carddetail.city}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">分支行：</label><label class="col-md-4">${carddetail.subbranch}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">卡类型：</label><label class="col-md-4">${cardtype}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">绑定银行卡时间：</label><label
			class="col-md-4">${carddetail.bindtimeStr}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">绑定方式：</label><label
			class="col-md-4">${bindmode}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">是否快捷绑卡：</label><label
			class="col-md-4">${isfastbindcard}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">是否默认取现卡：</label><label
			class="col-md-4">${isdefaultcard}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">绑定状态：</label><label
			class="col-md-4">${bindstatus}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">绑定通道：</label><label
			class="col-md-4">${carddetail.paycompany}</label>
	</div>
	<div class="row" style="margin-top: auto">
		<label class="col-md-4 text-right">备注：</label><label class="col-md-4">${carddetail.remark}</label>
	</div>
</body>
</html>