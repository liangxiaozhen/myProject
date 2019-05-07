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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#uwdstat").val("${uwc.status }");
		$("#isexceptions").val("${uwc.isexceptions}");
	})
</script>
</head>
<body>
	<form id="update-form" method="post" action="update.action">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
			<hr>
		</div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				<input type="hidden" name="cashno" value="${uwc.cashno }">
				订单编号：<label>${uwc.cashno }</label>
			</div>
			<hr>
		</div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				提现金额 ： <input name="amount" style="text-align: center;" type="text"
					value='<fmt:formatNumber pattern="0.00" value="${uwc.amount }"/>'>

			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				提现手续费：<input name="fee" style="text-align: center;" type="text"
					value="<fmt:formatNumber pattern="0.00" value="${uwc.fee }"/>">
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				平台服务费：<input name="servfee" style="text-align: center;" type="text"
					value="<fmt:formatNumber pattern="0.00" value="${uwc.servfee }"/>">
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				提现状态 ： <select name="status" id="uwdstat">
					<option value=0>待审核</option>
					<option value=1>成功</option>
					<option value=2>拒绝</option>
					<option value=3>失败</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">备注 ：</div>
			<div class="row" style="line-height: 0px; margin-top: 12px;">
				<div class="col-md-10 col-md-offset-1">
					<textarea class="form-control" name="remark" id="remark-text">${uwc.remark }</textarea>
				</div>
			</div>
		</div>
	</form>
</body>
</html>