<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>积分明细</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	font-family: "微软雅黑";
}

th {
	background: #ccc;
	text-align: center;
}

#myTab li a {
	text-decoration: none;
	font-size: 16px;
}

.lively {
	padding: 4px 8px;
	background: #FF9900;
	border-radius: 4px;
	color: black;
}
</style>
<script type="text/javascript">
$(function() {
	var type = ${type};
	if(type!=0){
		$("#myTab").find("span").each(function() {
			$(this).removeClass("lively");
			var status = $(this).data("status");
			if (status == type) {
				$(this).addClass("lively");
			}
		});
	}
})

/*** 分页查询积分明细  */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#pointForm").submit();
}
</script>
</head>
<body style="padding: 30px">
	<div class="container">
		<form method="post" role="form" id="pointForm"
			action="queryMyBonusPoints.action">
			<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
				type="hidden" id="pageSize" name="pageSize" value="" /> <input
				type="hidden" name="isuse" value="${type}" />
		</form>
		<h4>积分明细</h4>
		<hr>
		<!-- <div>
			<ul class="list-inline" id="myTab">
				<li>筛选：</li>
				<li><a href="queryMyBonusPoints.action"><span
						data-status="">全部</span></a></li>
				<li><a href="queryMyBonusPoints.action?status=2"><span
						data-status="2">可使用</span></a></li>
				<li><a href="queryMyBonusPoints.action?status=3"><span
						data-status="3">已冻结</span></a></li>
				<li><a href="queryMyBonusPoints.action?status=4"><span
						data-status="4">已使用</span></a></li>
				<li><a href="queryMyBonusPoints.action?status=5"><span
						data-status="5">已过期</span></a></li>
				<li><a href="queryMyBonusPoints.action?status=6"><span
						data-status="6">已作废</span></a></li>
			</ul>
		</div> -->
		<table
			class="table table-condensed table-bordered table-hover text-center">
			<thead>
				<tr>
					<th>序号</th>
					<th>积分编号</th>
					<th>积分名称</th>
					<th>获取方式</th>
					<th>积分类型</th>
					<th>发放时间</th>
					<th>发放状态</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty pagehelper.list}">
					<c:forEach items="${pagehelper.list}" var="detail" varStatus="vs">
						<tr>
							<td>${vs.index+1}</td>
							<td>${detail.ubpno}</td>
							<td>${detail.award.aname}</td>
							<td><c:if test="${!empty bptypemap}">
									<c:forEach items="${bptypemap}" var="type">
										<c:choose>
											<c:when test="${detail.bptype==type.key}">${type.value}</c:when>
										</c:choose>
									</c:forEach>
								</c:if></td>
							<td><c:if test="${detail.award.attribute eq 4}">交易积分</c:if>
								<c:if test="${detail.award.attribute eq 5}">系统积分</c:if></td>
							<td><fmt:formatDate value="${detail.bpstime}" type="both" /></td>
							<td><c:if test="${!empty statusmap}">
									<c:forEach items="${statusmap}" var="zt">
										<c:choose>
											<c:when test="${detail.status==zt.key}">${zt.value}</c:when>
										</c:choose>
									</c:forEach>
								</c:if></td>
							<td>${detail.remark}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pagehelper.list}">
					<tr>
						<td colspan="100">没有相关数据</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<c:if test="${!empty pagehelper.list}">
			<div id="page_div">
				<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
			</div>
		</c:if>
	</div>
</body>
</html>