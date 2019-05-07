<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>详情</title>

<script type="text/javascript" charset="utf-8"
	src="./jquery/1.11.3/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" src="calendar/WdatePicker.js"></script>
<script type="text/javascript" src="calendar/skin/WdatePicker.css"></script>
<script type="text/javascript">
	
</script>

</head>
<body style="font-family: 微软雅黑">

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
			<font color="red"><b>充值账户</b></font>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			充值用户名：<label class="detailslb" id="applyman">${userRecharge.applyman}</label>
			<%-- 充值人：<label class="detailslb" id="applyman">${userRecharge.ubai.realname}</label> --%>
		</div>
		<div class="col-md-4 col-md-offset-1">
			充值银行：<label class="detailslb" id="bankName">${userRecharge.bankname}</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			充值卡号：<label class="detailslb" id="applyman">${userRecharge.cardno}</label>
			<%-- 充值人：<label class="detailslb" id="applyman">${userRecharge.ubai.realname}</label> --%>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
			<font color="red"><b>充值信息</b></font>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			充值金额 ：<label class="detailslb" id="amountlb">
			<c:if test="${!empty userRecharge.amount}">
				${df.format(userRecharge.amount)}
			</c:if>
			<c:if test="${empty userRecharge.amount}">
				0.00
			</c:if>
			元
			</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			充值方式 ：<label class="detailslb"> <c:choose>
					<c:when test="${userRecharge.rechargetype eq 1}">个人网银</c:when>
					<c:when test="${userRecharge.rechargetype eq 3}">企业网银</c:when>
					<c:when test="${userRecharge.rechargetype eq 2}">快捷支付</c:when>
					<c:when test="${userRecharge.rechargetype eq 4}">汇款充值</c:when>
				</c:choose>
			</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			充值手续费 ：<label class="detailslb" id="feelb">
			<c:if test="${!empty userRecharge.recharfee}">
				${df.format(userRecharge.recharfee)}
			</c:if>
			<c:if test="${empty userRecharge.recharfee}">
				0.00
			</c:if>
			元</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			充值来源 ：<label class="detailslb" id="feelb">${userRecharge.originclientStr}</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			支付公司 ：<label class="detailslb" id="feelb">${userRecharge.paycompany}</label>
		</div>

		<div class="col-md-6 col-md-offset-1">
			银行返回订单号 ：<label class="detailslb" id="feelb">${userRecharge.bankreturnno}</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			备注 ：<label class="detailslb" id="feelb">${userRecharge.remark}</label>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
			<font color="red"><b>对账信息</b></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1 ">
			是否异常： <label class="detailslb"> <c:choose>
					<c:when test="${userRecharge.isexceptions eq 0}">正常</c:when>
					<c:when test="${userRecharge.isexceptions eq 1}">
						<font color='red'>异常</font>
					</c:when>
				</c:choose>
			</label>
		</div>
		<hr />
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			是否系统勾兑：<label class="detailslb"> <c:choose>
					<c:when test="${userRecharge.isblending eq 0 }">未勾兑</c:when>
					<c:when test="${userRecharge.isblending eq 1 }">已勾兑</c:when>
				</c:choose>
			</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			系统勾兑时间： <label class="detailslb">${userRecharge.syschktimeStr}</label>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			系统勾兑首次接收数据时间：<label class="detailslb" id="checkmanlb">${userRecharge.sysrectimeStr}</label>
		</div>

		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			是否人工勾兑：<label class="detailslb"> <c:choose>
					<c:when test="${userRecharge.ismanblending eq 0 }">未勾兑</c:when>
					<c:when test="${userRecharge.ismanblending eq 1 }">已勾兑</c:when>
				</c:choose>
			</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			勾兑人员：<label class="detailslb" id="checkmanlb">${userRecharge.checkman}</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			手工勾兑时间： <label class="detailslb">${userRecharge.checktimeStr}</label>
		</div>
		<hr>
	</div>


	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			人工勾兑首次接收数据时间： <label class="detailslb">${userRecharge.receivetimeStr}</label>
		</div>
		<hr>
	</div>
	<div class="row">
		<div class="col-md-10 col-md-offset-1" style="word-wrap: break-word;">
			请求数据包： <label class="detailslb">${userRecharge.reqquerydata}</label>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-10 col-md-offset-1" style="word-wrap: break-word;"><!-- recresultdata -->
			接收数据包： <label class="detailslb">${userRecharge.recresultdata}</label>
		</div>
	</div>

</body>
</html>