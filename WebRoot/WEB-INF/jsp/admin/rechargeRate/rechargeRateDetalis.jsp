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

<!-- 	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
			<font color="red"><b>详细信息</b></font>
		</div>
		<hr>
	</div> -->
	<c:if test="${!empty recharge.rechargeSNLLinkList}">
		<c:forEach items="${recharge.rechargeSNLLinkList}" var="item" varStatus="status">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-12 col-md-offset-1">
					定向名单：<span class="detailslb" id="snlid">${item.code}</span>-(<span class="detailslb" id="codename">${item.codename}</span>)
				</div>
				<hr>
			</div>
		
		</c:forEach>
	</c:if>
	<c:if test="${empty recharge.rechargeSNLLinkList}">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-12 col-md-offset-1">
					定向名单：<span class="detailslb" id="snlid">${item.code}</span>
				</div>
				
				<hr>
			</div>
		
	</c:if>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<c:if test="${!empty recharge.addman}">
				设置人：<span class="detailslb" id="addman">${recharge.addman}</span>
			</c:if>
			<c:if test="${empty recharge.addman}">
				设置人：<span class="detailslb" id="addman"></span>
			</c:if>
		</div>
		<hr>
	</div>
	
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			<c:if test="${!empty recharge.addtime}">
				设置时间：<span class="detailslb" id="addtime">${sf.format(recharge.addtime)}</span>
			</c:if>
			<c:if test="${empty recharge.addtime}">
				设置时间：<span class="detailslb" id="addtime"></span>
			</c:if>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<c:if test="${!empty recharge.paycompany}">
				支付公司：<span class="detailslb" id="paycompany">${recharge.paycompany}</span>
			</c:if>
			<c:if test="${empty recharge.paycompany}">
				支付公司：<span class="detailslb" id="paycompany"></span>
			</c:if>
		</div>
		<hr/>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<c:if test="${!empty recharge.remark}">
				备注：<span class="detailslb" id="remark">${recharge.remark}</span>
			</c:if>
			<c:if test="${empty recharge.remark}">
				备注：<span class="detailslb" id="remark"></span>
			</c:if>
		</div>
	</div>
</body>
</html>