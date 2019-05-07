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
<script type="text/javascript">
function details(daorderno) {
	var action = basePath+"/user/userdebtattorn/details.action";
	var params = {
			"daorderno" : daorderno,
	};
	var callback = function(data) {
		$("#myModaldstail").html(data);
	}
	$.post(action, params, callback);
} 

</script>
</head>
<body>
	<table class="table table-condensed">
		<thead>
			<tr style="background-color: #CCCCCC;" class="text-center2">
				<td>序号</td>
				<td>投标订单号</td>
				<td>投标金额</td>
				<td>待收本金</td>
				<!-- <td>标类型</td> -->
				<td>还款方式</td>
				<td>持有天数</td>
				<!-- <td>下一个还款日</td> -->
				<td>操作</td>
			</tr>
		</thead>
		<tbody id="text" style="text-align: center;">
			<!-- 这里面${item.id }是点的model里面的属性 -->
			<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

				<tr id="item_tr_${item.id }" class="text-center2">
					<td>${status.count}</td>
					<td>${item.orderno}</td>
					<td>
						<c:if test="${item.utproperty eq 1}">
							<!-- 正常标 -->
							<c:if test="${!empty item.amount}">${df.format(item.amount)}</c:if>
						</c:if> 
						<c:if test="${item.utproperty eq 2}">
							<!-- 债转标 -->
							<c:if test="${!empty item.utprinamount}">${df.format(item.utprinamount)}</c:if>
						</c:if>
					</td>
					<td>
						<c:if test="${!empty item.surplusamount}">${df.format(item.surplusamount)}</c:if>
						<c:if test="${empty item.surplusamount}">100</c:if>
					</td>
					<%-- <td>${item.tenderitem.tpro}</td> --%>
					<td>
						<c:if test="${item.tenderitem.repaymentpro eq 1}">一次性还本付息</c:if>
						<c:if test="${item.tenderitem.repaymentpro eq 2}">等额本金</c:if> 
						<c:if test="${item.tenderitem.repaymentpro eq 3}">等额本息</c:if> 
						<c:if test="${item.tenderitem.repaymentpro eq 4}">先息后本</c:if>
					</td>
					<td>${item.days}</td>
					<%-- <td>${item.repayment.rtime}</td> --%>
					<td>
						<c:if test="${item.identifying eq 1}">
						<button class="btn" data-toggle="modal" data-dismiss="modal"
								data-target="#myModal" onclick="install('${item.orderno}')">发布</button>
							<%--  <button class="btn"  onclick="overdue('${item.orderno}')">发布</button> --%>
						</c:if> 
						<c:if test="${item.identifying eq 2}">
							<%-- 	<c:if test="${item.tenderitem.repaymentpro eq 1}"><button class="btn"  data-toggle="modal"
										data-dismiss="modal" data-target="#myModal" onclick="install('${item.orderno}','${item.identifying}')">逾期发布</button>
								    	</c:if>
								    	<c:if test="${item.tenderitem.repaymentpro eq 2 or item.tenderitem.repaymentpro eq 3 or item.tenderitem.repaymentpro eq 4}"> --%>
							<button class="btn" onclick="overdue('${item.orderno}')">逾期债转</button>
							<%-- </c:if> --%>
						</c:if></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<div id="page_div"><%@ include
			file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
</body>
</html>