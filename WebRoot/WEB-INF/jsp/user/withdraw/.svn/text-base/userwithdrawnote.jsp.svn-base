<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="account_notice">
		<h2>尊敬的XX用户，XX为您记录和保存了您的提现记录，敬请审阅</h2>
		<div class="tab-content">
			<table id="tab2" class="table table-hover table-striped">
				<tr align="center" style="background: #ccc;">
					<th>提现流水号</th>
					<th>申请时间</th>
					<th>提现金额</th>
					<th>手续费类型</th>
					<th>手续费</th>
					<th>提现审核</th>
					<th>提现状态</th>
					<th>操作</th>
				</tr>
				<tbody>
					<c:forEach items="${pagehelper.list }" var="item"
						varStatus="status">
						<tr align="center">
							<td>${item.cashno }</td>
							<td>${item.applydateStr }</td>
							<td><c:if test="${empty item.amount }">0.00</c:if> <c:if
									test="${!empty item.amount }">${df.format(item.amount) }</c:if></td>
							<td><c:if test="${item.feeobjflag eq 'M' }">代扣</c:if> <c:if
									test="${item.feeobjflag eq 'U' }">自扣</c:if></td>
							<td><c:if test="${empty item.fee }">0.00</c:if> <c:if
									test="${!empty item.fee }">${df.format(item.fee) }</c:if></td>
							<td><c:choose>
									<c:when test="${item.isaudit eq 0 }">
										<span style="color: blue;">待审核</span>
									</c:when>
									<c:when test="${item.isaudit eq 1 }">
										<span style="color: green;">审核成功</span>
									</c:when>
									<c:when test="${item.isaudit eq 2 }">
										<span style="color: red;">审核失败</span>
									</c:when>
									<c:when test="${item.isaudit eq 3 }">
										<span>无需审核</span>
									</c:when>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${item.status eq 0 }">
										<span style="color: blue;">待处理</span>
									</c:when>
									<c:when test="${item.status eq 1 }">
										<span style="color: green;">提现成功</span>
									</c:when>
									<c:when test="${item.status eq 2 }">
										<span style="color: red;">提现失败</span>
									</c:when>
									<c:when test="${item.status eq 3 }">
										<span>提现取消</span>
									</c:when>
									<c:when test="${item.status eq 4 }">
										<span style="color: blue;">审核中</span>
									</c:when>
									<c:when test="${item.status eq 5 }">
										<span style="color: blue;">待取消</span>
									</c:when>
								</c:choose></td>
							<td><c:if test="${item.status eq 0 }">
									<c:if test="${item.iscancel eq 1 }">
										<a href="javascript:void(0)"
											onclick="cancelWithdraw('${item.cashno}')">取消提现</a>
									</c:if>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!--  	<c:if test="${listSize>9 and pagehelper.pageNum!=1 }">  </c:if>-->
			<div class="page_div">
				<%@ include file="../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>