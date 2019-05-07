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
	function buy(daorderno,totalamount,totalfee){
		var action =basePath+"/user/undertakedebtattorn/buy.action";
		var params = {
				"daorderno":daorderno,
				"totalamount":totalamount,
				"totalfee":totalfee
		}
		$.post(action,params,function(data){
			$("#mymodalbuy").html(data);
		});
	}
	function dstails(daorderno){
		var action =basePath+"/user/undertakedebtattorn/dstails.action";
		var params = {
				"daorderno":daorderno
		}
		$.post(action,params,function(data){
			$("#myModaldstail").html(data);
		});
	}


</script>
</head>

<body>
	<table class="table">
		<thead>
			<tr class="text-center2" style="background: #CCCCCC">
				<td>序号</td>
				<td>借款标题</td>
				<td>债转标号</td>
				<td>发布时间</td>
				<!-- <td>标类型</td> -->
				<!-- <td>剩余期限</td> -->
				<td>汇总金额</td>
				<!-- 对应债转金额 -->
				<td>汇总系数</td>
				<!-- 对应几倍出售 -->
				<!-- <td>剩余金额</td> -->
				<!-- 对应剩余份数 -->
				<td>年利率</td>
				<td>收益率</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody id="text">
			<!-- 这里面${item.id }是点的model里面的属性 -->
			<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

				<tr class="text-center2">
					<td>${status.count}</td>
					<td>投资理财</td>
					<td>${item.daorderno}</td>
					<td><c:if test="${item.isaudit eq 1}">
							<!-- 需要审核 -->
										${sf.format(item.audittime)}
									</c:if> <c:if test="${item.isaudit eq 0}">
							<!-- 不需要审核 -->
										${sf.format(item.settime)}
									</c:if></td>
					<!-- <td>年标</td> -->
					<%-- <td>${item.repayment.periods}</td> --%>
					<td>${item.totalamount}</td>
					<td>${item.totalfee}</td>
					<c:if test="${item.daproperty eq 1}">
						<td>${item.yearProfitStr}%</td>
						<td>-----</td>
					</c:if>
					<c:if test="${item.daproperty eq 2}">
						<td>-----</td>
						<td>${item.profitStr}%</td>
					</c:if>
					<!-- <td>债券转让</td> -->
					<td>
						<button class="btn" data-toggle="modal" data-dismiss="modal"
							data-target="#myModalBuy"
							onclick="buy('${item.daorderno}','${item.totalamount}','${item.totalfee}')">购买</button>
						<button class="btn" data-toggle="modal" data-dismiss="modal"
							data-target="#myModaldstails"
							onclick="dstails('${item.daorderno}')">详情</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div id="page_div"><%@ include
			file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
</body>
</html>