<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" style="color:#1E88C7;">
		<tr>
			<td>ID：</td>
			<td>${aheadRepayAward.id}</td>
		</tr>
		<tr>
			<td>标号ID：</td>
			<td>${aheadRepayAward.tid}</td>
		</tr>
		<tr>
			<td>提前还款奖品奖励编号：</td>
			<td>${aheadRepayAward.aheadrepayano}</td>
		</tr>
		<tr>
			<td>单个投资人累计增益欠收最小利息：</td>
			<td>${aheadRepayAward.minplusnoreceiveint}</td>
		</tr>
		<tr>
			<td>单个投资人累计增益欠收最高利息：</td>
			<td>${aheadRepayAward.maxplusnoreceiveint}</td>
		</tr>
		<tr>
			<td>增益奖励方式：</td>
			<td><c:if test="${aheadRepayAward.plusawardtype==1}">平台罚金</c:if>
				<c:if test="${aheadRepayAward.plusawardtype==2}">平台奖励</c:if> <c:if
					test="${aheadRepayAward.plusawardtype==3}">平台罚金+平台奖励</c:if></td>

		</tr>
		<tr>
			<td>增益平台罚金奖励名称：</td>
			<td>${aheadRepayAward.pluspenaltyname}</td>
		</tr>
		<tr>
			<td>增益平台罚金定额：</td>
			<td>${aheadRepayAward.pluspenaltyquota}</td>
		</tr>
		<tr>
			<td>增益平台罚金百分比：</td>
			<td>${aheadRepayAward.pluspenaltyrate}</td>
		</tr>
		<tr>
			<td>增益平台罚金最大值：</td>
			<td>${aheadRepayAward.plusmaxpenalty}</td>
		</tr>
		<tr>
			<td>增益平台奖励奖品名称：</td>
			<td>${aheadRepayAward.pluspawardname}</td>
		</tr>
		<tr>
			<td>增益平台奖励奖品编号：</td>
			<td>${aheadRepayAward.pluspawardno}</td>
		</tr>
		<tr>
			<td>增益平台奖励奖品份数：</td>
			<td>${aheadRepayAward.pluspawardcount}</td>
		</tr>
		<tr>
			<td>资金清算是否需要审核：</td>
			<td><c:if test="${aheadRepayAward.isaudit==1}">是</c:if> <c:if
					test="${aheadRepayAward.isaudit==0}">否</c:if></td>
		</tr>
		<tr>
			<td>是否为模板：</td>
			<td><c:if test="${aheadRepayAward.istemplet==1}">是</c:if>
				<c:if test="${aheadRepayAward.istemplet==0}">否</c:if></td>
		<tr>
			<td>添加人：</td>
			<td>${aheadRepayAward.addman}</td>
		</tr>
		<tr>
			<td>设置时间：</td>
			<td><fmt:formatDate value="${aheadRepayAward.addtime}" /></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>${aheadRepayAward.remark}</td>
		</tr>
	</table>
</body>
</html>