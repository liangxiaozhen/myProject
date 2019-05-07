<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="UTF-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">var basePath="${basePath}";</script>
<style type="text/css">
body {
	font-family: "微软雅黑";
}

th {
	text-align: center;
	background: #ccc;
}

td {
	text-align: center;
}

li:hover {
	cursor: pointer;
}

table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 1px solid #666;
}
</style>
</head>
<body>
	<ul class="list-inline">
		<li>筛选：</li>
		<li><a href="">正常回款（0）</a></li>
		<li><a href="">逾期（0）</a></li>
		<li><a href="">投标中（0）</a></li>
		<li><a href="">流标（0）</a></li>
	</ul>
	<table class="table table-condensed table-bordered table-hover">
		<thead>
			<tr class="text-center">
				<th>投资日期</th>
				<th>投标订单号</th>
				<th>标的属性</th>
				<th>投标金额</th>
				<th>借款期限</th>
				<th>投标方式</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody style="text-align: center;">
			<c:if test="${!empty pagehelper.list}">
				<c:forEach items="${pagehelper.list}" var="tender">
					<tr class="text-center">
						<td>${tender.tbegintimeStr}</td>
						<td>${tender.orderno}</td>
						<td><c:if test="${!empty tpropertymaps}">
								<c:forEach items="${tpropertymaps}" var="ttype">
									<c:choose>
										<c:when test="${tender.tproperty==ttype.key}">${ttype.value}
															</c:when>
									</c:choose>
								</c:forEach>
							</c:if></td>
						<td>${tender.amount}</td>
						<td>${tender.tbegintimeStr}</td>
						<td>${tender.tendertype}</td>
						<td><c:if test="${!empty tstatusmaps}">
								<c:forEach items="${tstatusmaps}" var="status">
									<c:choose>
										<c:when test="${tender.tstatus==status.key}">${status.value}
															</c:when>
									</c:choose>
								</c:forEach>
							</c:if></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div id="page_div"><%@ include
			file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
</body>
</html>