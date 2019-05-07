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
	<table class="table">
		<thead>
			<tr class="text-center2" style="background: #CCCCCC">
				<td>序号</td>
				<td>投标订单号</td>
				<td>投标金额</td>
				<td>待收本金</td>
				<td>标类型</td>
				<td>还款方式</td>
				<td>持有天数</td>
				<td>下一个还款日</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody id="text">
			<!-- 这里面${item.id }是点的model里面的属性 -->
			<tr class="text-center2">
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>
				<td>散标投资</td>

			</tr>
		</tbody>
	</table>
	<div id="page_div"><%@ include
			file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
</body>
</html>