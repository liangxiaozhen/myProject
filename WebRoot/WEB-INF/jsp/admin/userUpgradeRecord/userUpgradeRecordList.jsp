<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".remark-p").each(function() {
			var num = $(this).html();
			if (num.length > 5) {
				$(this).html(num.substr(0, 5) + "...");
			}
		});
	});
</script>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>用户等级升级记录</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
				</form>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>用户名</td>
							<td>原来级别</td>
							<td>当前级别</td>
							<td>升级方式</td>
							<td>升级类型</td>
							<td>支付金额</td>
							<td>用户系统积分</td>
							<td>购买时间</td>
							<td>到期时间</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.loginname }</td>
								<td>${item.oldugradename }</td>
								<td>${item.ugradename }</td>
								<td><c:if test="${item.paytype eq 1}">
									金额
								</c:if> <c:if test="${item.paytype eq 2}">
									积分
								</c:if> <c:if test="${item.paytype eq 3}">
									系统自动
								</c:if> <c:if test="${item.paytype eq 4}">
									管理员手动
								</c:if></td>
								<td><c:if test="${item.dealmode eq 1}">
									正常升级
								</c:if> <c:if test="${item.dealmode eq 2}">
									体验升级
								</c:if> <c:if test="${item.dealmode eq 3}">
									恢复原级
								</c:if> <c:if test="${item.dealmode eq 4}">
									违规人工降级
								</c:if></td>
								<td>${item.payamount }</td>
								<td>${item.bonuspoints }</td>
								<td><c:if test="${!empty item.dealtime }">
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.dealtime  }" />
									</c:if></td>
								<td><c:if test="${empty item.expirydate }">永久</c:if> <c:if
										test="${!empty item.expirydate }">
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.expirydate }" />
									</c:if></td>
								<td class="remark-p" title="${item.remark }">${item.remark }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>