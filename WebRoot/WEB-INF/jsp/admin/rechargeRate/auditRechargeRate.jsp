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
	width: 25em;
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
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">充值方式&nbsp;：</div>
			<div>
				<label>${rechargeRate.rechartypeStr}</label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">金额范围&nbsp;：</div>
			<div>
				<label>${df1.format(rechargeRate.minmoney)} 元 -
					${df1.format(rechargeRate.maxmoney)} 元</label>
			</div>
		</div>

		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">支付公司&nbsp;：</div>
			<div>
				<label>${rechargeRate.paycompany}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">
				收费类型&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div>
				<label>${rechargeRate.chargetypeStr} </label>
			</div>
		</div>
		<hr>
		<c:if test="${rechargeRate.chargetype==1}">
			<!-- 定额 -->
			<div id="showquota-div">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
						<font color="red"><b>定额</b></font>
					</div>
				</div>
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-3 col-md-offset-1">固定金额&nbsp;：</div>
					<div>
						<label>${df1.format(rechargeRate.quota)} 元</label>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${rechargeRate.chargetype==2}">
			<!-- 比例 -->
			<div id="showrate-div">
				<div class="row">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
						<font color="red"><b>比例</b></font>
					</div>
				</div>
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-3 col-md-offset-1">充值费率&nbsp;：</div>
					<div>
						<label id="showwdcrate-lb">${rechargeRate.charrate}%</label>
					</div>
				</div>
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-5 col-md-offset-1">
						最低收费金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>${df1.format(rechargeRate.minfee)}
							元</label>
					</div>
					<div class="col-md-6 ">
						最高收费金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>${df1.format(rechargeRate.maxfee)}
							元</label>
					</div>
				</div>
			</div>
		</c:if>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
			<br /> <br /> <br />
			<div class="col-md-10 col-md-offset-1" style="float: right">
				<textarea class="form-control" name="remark" id="audit-remark-text">${rechargeRate.remark}</textarea>
			</div>
		</div>


	</form>
</body>
</html>
