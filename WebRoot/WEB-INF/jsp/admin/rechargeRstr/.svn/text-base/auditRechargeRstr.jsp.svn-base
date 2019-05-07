<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>itemList</title>
<style>
.test {
	width: 21em;
	word-wrap: break-word;
}
</style>
</head>

<body style="font-family: 微软雅黑">

	<form id="rateadd-form" method="post">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: auto;">
			<div class="col-md-4 col-md-offset-1">
				<input type="hidden" id="audit-id-text" value="${rechargeRstr.id} ">
				会员等级：
			</div>
			<div>
				<label class="test">${rechargeRstr.ugradeStr}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">充值方式：</div>
			<div>
				<label>
					<c:if test="${rechargeRstr.rechartype eq 0}">网银</c:if>
					<c:if test="${rechargeRstr.rechartype eq 1}">代扣充值</c:if>
					<c:if test="${rechargeRstr.rechartype eq 2}">快捷支付</c:if>
					<c:if test="${rechargeRstr.rechartype eq 3}">汇款充值</c:if>
					<c:if test="${rechargeRstr.rechartype eq 4}">企业网银</c:if>
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">支付公司：</div>
			<div>
				<label>${rechargeRstr.paycompany}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>金额</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">日充值金额：</div>
			<div>
				<label>${df1.format(rechargeRstr.daymoneyrest)} 元 </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">单笔最低充值金额：</div>
			<div>
				<label>${df1.format(rechargeRstr.lowestmoney)} 元 </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">单笔最高充值金额：</div>
			<div>
				<label>${df1.format(rechargeRstr.hightestmoney)} 元</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>排除人名单</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">排除人员名单：</div>
			<div>
				<label>${rechargeRstr.removenamenoStr}</label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
			<br /> <br /> <br />
			<div class="col-md-10 col-md-offset-1" style="float: right">
				<textarea class="form-control" name="remark" id="audit-remark-text">${rechargeRstr.remark}</textarea>
			</div>
		</div>


	</form>
</body>
</html>
