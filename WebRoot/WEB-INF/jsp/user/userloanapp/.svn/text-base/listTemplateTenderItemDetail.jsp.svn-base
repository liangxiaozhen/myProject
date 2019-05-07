<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr>
			<td>标号</td>
			<td>标的名称</td>
			<td>标的金额</td>
			<td>已完成投标金额</td>
			<td>标的状态</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tenderItems" items="${tenderItems}">
			<tr>
				<td>${tenderItems.tno}</td>
				<td>${tenderItems.tname}</td>
				<td>${tenderItems.tamount}</td>
				<td>${tenderItems.finishtamount}</td>
				<td><c:choose>
						<c:when test="${tenderItems.tstatus == 0}">标的设置未完成</c:when>
						<c:when test="${tenderItems.tstatus == 1}">审核中</c:when>
						<c:when test="${tenderItems.tstatus == 2}">审核不通过</c:when>
						<c:when test="${tenderItems.tstatus == 3}">待发布</c:when>
						<c:when test="${tenderItems.tstatus == 4}">发布中</c:when>
						<c:when test="${tenderItems.tstatus == 5}">已满标</c:when>
						<c:when test="${tenderItems.tstatus == 6}">已下线</c:when>
						<c:when test="${tenderItems.tstatus == 7}">已流标</c:when>
						<c:when test="${tenderItems.tstatus == 8}">已结标</c:when>
						<c:when test="${tenderItems.tstatus == 9}">还款中</c:when>
						<c:when test="${tenderItems.tstatus == 10}">已还款</c:when>
						<c:when test="${tenderItems.tstatus == 11}">逾期中</c:when>
						<c:when test="${tenderItems.tstatus == 12}">已清盘</c:when>
					</c:choose></td>
				<td>
					<button type="button"
						onclick="userloanApp.low_showRepaymentPlanDateil(this)" data-opid="${tenderItems.id}"
						class="btn btn-primary" id="showRepaymentPlanDateil">还款计划详情</button> <c:if
						test="${tenderItems.tstatus == 8}">
						<button type="button"
							onclick="userloanApp.low_showRepaymentPlanDateil(this)" data-opid="${tenderItems.id}"
							class="btn btn-primary" id="showRepaymentPlanDateil">还款计划详情</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
