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
<script type="text/javascript">var basePath="${basePath}";</script>
<style type="text/css">
body {
	font-family: "微软雅黑";
}

th {
	text-align: center;
	background: #ccc;
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
	<table class="table table-condensed">
		<thead>
			<tr style="background-color: #CCCCCC;" class="text-center2">
				<td>序号</td>
				<!-- <td>id</td> -->
				<td>债转标号</td>
				<td>标号</td>
				<td>投标订单号</td>
				<td>定向债转码</td>
				<td>投标金额</td>
				<td>债转金额</td>
				<td>是否全部</td>
				<td>金额债转系数</td>
				<td>债转状态</td>
				<td>可债转次数/层数</td>
				<td>债券属性</td>
				<td>债转利息</td>
				<td>债转利息系数</td>
				<td>挂牌ID</td>
				<td>详情</td>
			</tr>
		</thead>
		<tbody id="text" style="text-align: center;">
			<!-- 这里面${item.id }是点的model里面的属性 -->
			<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

				<tr id="item_tr_${item.id }" class="text-center2">
					<td>${status.count}</td>
					<%-- <td>${item.id}</td> --%>
					<td>${item.daorderno}</td>
					<td>${item.tenderid}</td>
					<td>${item.torderno}</td>
					<td><c:if test="${not empty item.udapass}">${item.udapass}</c:if>
						<c:if test="${empty item.udapass}">------</c:if></td>
					<td><c:if test="${not empty item.amount}">${df.format(item.amount)}</c:if>
					</td>
					<td><c:if test="${not empty item.daamount}">${df.format(item.daamount)}</c:if>
					</td>
					<td><c:if test="${item.ispartda eq 1}">部分</c:if> <c:if
							test="${item.ispartda eq 0}">全部</c:if></td>
					<td>${item.coefficient}</td>
					<td><c:if test="${item.dastatus eq 1}">待审核</c:if> <c:if
							test="${item.dastatus eq 2}">已上架</c:if> <c:if
							test="${item.dastatus eq 3}">已下架</c:if> <c:if
							test="${item.dastatus eq 4}">已完成</c:if></td>
					<td>${item.datimes}</td>
					<td><c:if test="${item.daproperty eq 1}">正常标</c:if> <c:if
							test="${item.daproperty eq 2}">逾期标</c:if></td>
					<td>${item.intamount}</td>
					<td>${item.intcoefficient}</td>
					<td>${item.lcid}</td>
					<td>
						<button class="btn" data-toggle="modal" data-dismiss="modal"
							data-target="#myModaldstails"
							onclick="details('${item.daorderno}')">查看</button>

					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<div id="page_div"><%@ include
			file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
</body>
</html>