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
		<input type="hidden" id="audit-id-text" value="${ub.id} ">
		<div class="row" style="line-height: auto;">
			<div class="col-md-4 col-md-offset-1">标号:</div>
			<div>
				<label class="test" id="tenderidlb">${ub.tenderid}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">债转订单号：</div>
			<div>
				<label id="daordernolb">${ub.daorderno}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">定向债转码：</div>
			<div>
				<label id="udapasslb"> <c:if test="${!empty ub.udapass}">${ub.udapass}</c:if>
					<c:if test="${empty ub.udapass}">-----</c:if>
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">投标订单号：</div>
			<div>
				<label id="tordernolb">${ub.torderno} </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">投标金额：</div>
			<div>
				<label id="amountlb"> <c:if test="${!empty ub.amount}">${df1.format(ub.amount)}</c:if>元
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">债转金额：</div>
			<div>
				<label id="daamountlb"> <c:if test="${!empty ub.daamount}">${df1.format(ub.daamount)}</c:if>元
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">是否全部：</div>
			<div>
				<label id="ispartdalb"> <c:if test="${ub.ispartda eq 0}">全部</c:if>
					<c:if test="${ub.ispartda eq 1}">部分</c:if>
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">债转系数：</div>
			<div>
				<label id="coefficientlb">${ub.coefficient}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">债转次数：</div>
			<div>
				<label id="datimeslb">第${ub.datimes}次</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">债转状态：</div>
			<div>
				<label id="dastatuslb"> <c:if test="${ub.dastatus eq 1}">
						<font style="color: blue">待审核</font>
					</c:if> <c:if test="${ub.dastatus eq 2}">
						<font style="color: green">已上架</font>
					</c:if> <c:if test="${ub.dastatus eq 3}">
						<font style="color: red">已下架</font>
					</c:if> <c:if test="${ub.dastatus eq 4}">
						<font style="color: green">已完成</font>
					</c:if>
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
			<br /> <br /> <br />
			<div class="col-md-10 col-md-offset-1" style="float: right">
				<textarea class="form-control" name="remark" id="audit-remark-text"></textarea>
			</div>
		</div>
	</form>
</body>
</html>
